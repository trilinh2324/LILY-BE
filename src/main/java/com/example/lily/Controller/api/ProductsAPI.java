package com.example.lily.Controller.api;

import com.example.lily.Model.Category;
import com.example.lily.Model.Product;
import com.example.lily.Model.ProductDTO;
import com.example.lily.Model.ProductDetail;
import com.example.lily.Service.CategoryService;
import com.example.lily.Service.ProductDetailService;
import com.example.lily.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductsAPI {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") long id) {
        return productService.getProductById(id);
    }
}
