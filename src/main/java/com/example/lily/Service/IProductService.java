package com.example.lily.Service;

import com.example.lily.Model.Product;
import com.example.lily.Model.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IProductService extends IGenerateService<Product> {

    List<ProductDTO> getProductsGroupedByCategory();

    Product addProduct(String name, double price, MultipartFile imageFile, String description, long categoryId, String color, String type, String material, String stone, String degreeOfPerfection, String genderProduct) throws IOException;
}

