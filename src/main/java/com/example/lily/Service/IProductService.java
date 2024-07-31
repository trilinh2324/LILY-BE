package com.example.lily.Service;

import com.example.lily.Model.Product;
import com.example.lily.Model.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IProductService extends IGenerateService<Product> {

    List<ProductDTO> getProductsGroupedByCategory();

    Product addProduct( String name, Double price, MultipartFile imageFile, String description, long categoryId,
                        String goldType, Double goldPurity, Double goldWeight, String goldColor,
                        String stoneType, int stoneQuantity, Double stoneWeight, String stoneColor,
                        String stoneShape, String stoneClarity, Double pendantLength, Double pendantWidth,
                        Double pendantHeight, String warrantyPeriod, String origin, boolean buybackOption,
                        String gemstoneCertification, boolean lifetimeService, String fate, int quantity) throws IOException;
}

