package com.example.lily.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDTO {
    private long id;
    private long idCategory;
    private Category category;
    private List<Product> products;
    private ProductDetail productDetail;

    public ProductDTO(long id, long idCategory, Category category, List<Product> products, ProductDetail productDetail) {
        this.id = id;
        this.idCategory = idCategory;
        this.category = category;
        this.products = products;
        this.productDetail = productDetail;
    }

    public ProductDTO(Category category, List<Product> products, ProductDetail productDetail) {
        this.idCategory = category.getId();
        this.category = category;
        this.products = products;
        this.productDetail = productDetail;
    }
}
