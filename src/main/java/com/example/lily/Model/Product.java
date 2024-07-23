package com.example.lily.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name; // tên sp
    private double price; // giá sp
    private String image; // ảnh sp
    private String description; // miêu tả

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_detail_id", referencedColumnName = "id")
    private ProductDetail productDetail;

    @Transient
    private String formattedPrice;

    public String getFormattedPrice() {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(price);
    }

    // Ensure the image path is relative to the static directory
    public String getImagePath() {
        return "/Image/" + image;
    }
}
