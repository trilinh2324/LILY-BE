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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGoldType() {
        return goldType;
    }

    public void setGoldType(String goldType) {
        this.goldType = goldType;
    }

    public Double getGoldPurity() {
        return goldPurity;
    }

    public void setGoldPurity(Double goldPurity) {
        this.goldPurity = goldPurity;
    }

    public Double getGoldWeight() {
        return goldWeight;
    }

    public void setGoldWeight(Double goldWeight) {
        this.goldWeight = goldWeight;
    }

    public String getGoldColor() {
        return goldColor;
    }

    public void setGoldColor(String goldColor) {
        this.goldColor = goldColor;
    }

    public String getStoneType() {
        return stoneType;
    }

    public void setStoneType(String stoneType) {
        this.stoneType = stoneType;
    }

    public Integer getStoneQuantity() {
        return stoneQuantity;
    }

    public void setStoneQuantity(Integer stoneQuantity) {
        this.stoneQuantity = stoneQuantity;
    }

    public Double getStoneWeight() {
        return stoneWeight;
    }

    public void setStoneWeight(Double stoneWeight) {
        this.stoneWeight = stoneWeight;
    }

    public String getStoneColor() {
        return stoneColor;
    }

    public void setStoneColor(String stoneColor) {
        this.stoneColor = stoneColor;
    }

    public String getStoneShape() {
        return stoneShape;
    }

    public void setStoneShape(String stoneShape) {
        this.stoneShape = stoneShape;
    }

    public String getStoneClarity() {
        return stoneClarity;
    }

    public void setStoneClarity(String stoneClarity) {
        this.stoneClarity = stoneClarity;
    }

    public Double getPendantLength() {
        return pendantLength;
    }

    public void setPendantLength(Double pendantLength) {
        this.pendantLength = pendantLength;
    }

    public Double getPendantWidth() {
        return pendantWidth;
    }

    public void setPendantWidth(Double pendantWidth) {
        this.pendantWidth = pendantWidth;
    }

    public Double getPendantHeight() {
        return pendantHeight;
    }

    public void setPendantHeight(Double pendantHeight) {
        this.pendantHeight = pendantHeight;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Boolean getBuybackOption() {
        return buybackOption;
    }

    public void setBuybackOption(Boolean buybackOption) {
        this.buybackOption = buybackOption;
    }

    public String getGemstoneCertification() {
        return gemstoneCertification;
    }

    public void setGemstoneCertification(String gemstoneCertification) {
        this.gemstoneCertification = gemstoneCertification;
    }

    public Boolean getLifetimeService() {
        return lifetimeService;
    }

    public void setLifetimeService(Boolean lifetimeService) {
        this.lifetimeService = lifetimeService;
    }
}
