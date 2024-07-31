package com.example.lily.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String goldType; // Loại vàng

    private Double goldPurity; // Độ tinh khiết của vàng
    private Double goldWeight; // Trọng lượng vàng

    private String goldColor; // Màu sắc vàng

    private String stoneType; // Loại đá chủ

    private Integer stoneQuantity; // Số lượng đá chủ

    private Double stoneWeight; // Trọng lượng đá chủ

    private String stoneColor; // Màu sắc đá chủ

    private String stoneShape; // Hình dạng đá chủ

    private String stoneClarity; // Độ tinh khiết của đá chủ

    private Double pendantLength; // Chiều dài mặt dây chuyền

    private Double pendantWidth; // Chiều rộng mặt dây chuyền

    private Double pendantHeight; // Chiều cao mặt dây chuyền

    private String warrantyPeriod; // Thời gian bảo hành

    private String origin; // Xuất xứ

    private Boolean buybackOption; // Có thu mua lại hay không

    private String gemstoneCertification; // Giấy kiểm định đá quý

    private Boolean lifetimeService; // Dịch vụ miễn phí trọn đời (đánh bóng, làm sạch sản phẩm, gắn đá Cz dưới 3 mm)
}
