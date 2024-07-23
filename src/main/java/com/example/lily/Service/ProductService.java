package com.example.lily.Service;

import com.example.lily.Model.Category;
import com.example.lily.Model.Product;
import com.example.lily.Model.ProductDTO;
import com.example.lily.Model.ProductDetail;

import com.example.lily.Repository.ICategoryRepository;
import com.example.lily.Repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<ProductDTO> getProductsGroupedByCategory() {
        return List.of();
    }

    public Product addProduct(String name, double price, MultipartFile imageFile, String description, long categoryId,
                              String color, String type, String material, String stone,
                              String degreeOfPerfection, String genderProduct) throws IOException {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        String imagePath = "C:\\Users\\Admin\\Desktop\\LiLy\\src\\main\\resources\\static\\Image\\" + imageFile.getOriginalFilename();
        imageFile.transferTo(new java.io.File(imagePath));

        ProductDetail productDetall = new ProductDetail();
        productDetall.setColor(color);
        productDetall.setType(type);
        productDetall.setMaterial(material);
        productDetall.setStone(stone);
        productDetall.setDegreeOfPerfection(degreeOfPerfection);
        productDetall.setGenderProduct(genderProduct);

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setImage(imagePath);
        product.setDescription(description);
        product.setCategory(category);
        product.setProductDetail(productDetall);

        return productRepository.save(product);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public void remove(long id) {

    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product getProductById(long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }
}
