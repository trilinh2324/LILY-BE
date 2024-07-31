package com.example.lily.Controller;

import com.example.lily.Model.Category;
import com.example.lily.Model.Product;
import com.example.lily.Model.ProductDetail;
import com.example.lily.Model.User;
import com.example.lily.Repository.ICategoryRepository;
import com.example.lily.Repository.IProductDetailRepository;
import com.example.lily.Repository.IProductRepository;
import com.example.lily.Service.ICategoryService;
import com.example.lily.Service.IProductService;
import com.example.lily.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Products")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IProductDetailRepository iProductDetailRepository;

    public static final String UPLOAD_DIRECTORY = "C:\\Users\\Admin\\Desktop\\LiLy\\src\\main\\resources\\static\\Image\\";

    private String uploadImage(MultipartFile imageFile) throws IOException {
        String fileName = imageFile.getOriginalFilename();
        Path uploadPath = Paths.get(UPLOAD_DIRECTORY);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }




    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(
            @RequestParam("name") String name,
            @RequestParam("price") Double price,
            @RequestParam("image") MultipartFile imageFile,
            @RequestParam("description") String description,
            @RequestParam("categoryId") long categoryId,
            @RequestParam("goldType") String goldType,
            @RequestParam("goldPurity") Double goldPurity,
            @RequestParam("goldWeight") Double goldWeight,
            @RequestParam("goldColor") String goldColor,
            @RequestParam("stoneType") String stoneType,
            @RequestParam("stoneQuantity") int stoneQuantity,
            @RequestParam("stoneWeight") Double stoneWeight,
            @RequestParam("stoneColor") String stoneColor,
            @RequestParam("stoneShape") String stoneShape,
            @RequestParam("stoneClarity") String stoneClarity,
            @RequestParam("pendantLength") Double pendantLength,
            @RequestParam("pendantWidth") Double pendantWidth,
            @RequestParam("pendantHeight") Double pendantHeight,
            @RequestParam("warrantyPeriod") String warrantyPeriod,
            @RequestParam("origin") String origin,
            @RequestParam("buybackOption") boolean buybackOption,
            @RequestParam("gemstoneCertification") String gemstoneCertification,
            @RequestParam("lifetimeService") boolean lifetimeService,
            @RequestParam("fate") String fate,
            @RequestParam("quantity") int quantity) throws IOException {

        // Upload image and get the relative path
        String fileName = uploadImage(imageFile);

        // Create ProductDetail
        ProductDetail productDetail = new ProductDetail();
        productDetail.setGoldType(goldType);
        productDetail.setGoldPurity(goldPurity);
        productDetail.setGoldWeight(goldWeight);
        productDetail.setGoldColor(goldColor);
        productDetail.setStoneType(stoneType);
        productDetail.setStoneQuantity(stoneQuantity);
        productDetail.setStoneWeight(stoneWeight);
        productDetail.setStoneColor(stoneColor);
        productDetail.setStoneShape(stoneShape);
        productDetail.setStoneClarity(stoneClarity);
        productDetail.setPendantLength(pendantLength);
        productDetail.setPendantWidth(pendantWidth);
        productDetail.setPendantHeight(pendantHeight);
        productDetail.setWarrantyPeriod(warrantyPeriod);
        productDetail.setOrigin(origin);
        productDetail.setBuybackOption(buybackOption);
        productDetail.setGemstoneCertification(gemstoneCertification);
        productDetail.setLifetimeService(lifetimeService);

        // Create Product
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setImage(fileName); // Set the relative path
        product.setDescription(description);
        product.setFate(fate);
        product.setQuantity(quantity);

        // Set category
        Category category = categoryService.getCategoryById(categoryId);
        product.setCategory(category);

        // Set product detail
        product.setProductDetail(productDetail);

        // Save product
        productRepository.save(product);

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/Products/HomeAdmin")).build();
    }


    @GetMapping("/HomeAdmin")
    public ModelAndView showAllProducts(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("HomeAdmin"); // Ensure the view name matches the template file name
        Page<Product> productPage = productRepository.findAll(pageable);

        DecimalFormat df = new DecimalFormat("#,###.00");

        List<Product> formattedProducts = new ArrayList<>();
        for (Product product : productPage.getContent()) {
            product.setFormattedPrice(df.format(product.getPrice()));
            formattedProducts.add(product);
        }

        Page<Product> formattedProductPage = new PageImpl<>(formattedProducts, pageable, productPage.getTotalElements());
        modelAndView.addObject("listProduct", formattedProductPage);
        modelAndView.addObject("product", new Product());
        List<Category> categories = categoryService.getAllCategories();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView showInfo(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("view1");
        modelAndView.addObject("product", productRepository.findById(id).get());
        modelAndView.addObject("productDetail", iProductDetailRepository.findById(id).get());
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/Products/HomeAdmin");
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            productRepository.deleteById(id);
            modelAndView.addObject("message", "Sản phẩm đã được xóa thành công.");
        } else {
            modelAndView.addObject("error", "Sản phẩm không tồn tại.");
        }
        return modelAndView;
    }

    @GetMapping("/login")
    public String FromLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, HttpSession session, RedirectAttributes redirect) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        User user = userService.loginUser(userName, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/Products/HomeAdmin";
        } else {
            redirect.addFlashAttribute("message", "Tên người dùng hoặc mật khẩu không đúng. Vui lòng thử lại.");
            return "redirect:/Products/login";
        }
    }
}
