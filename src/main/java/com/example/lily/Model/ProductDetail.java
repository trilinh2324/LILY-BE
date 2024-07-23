package com.example.lily.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String color; // màu sắc
    private String type; // loại
    private String material; // chất liệu
    private String stone; // đá
    private String degreeOfPerfection; // độ hoàn thiện
    private String genderProduct; // giới tính
}
