package com.example.lily.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BillItem> items;

    private double totalPayableAmount;
    private String selectedCountry;
    private String selectedProvince;
    private String selectedDistrict;
    private String deliveryTime;
    private Date orderDate;
    private double totalAmount;
    private String recipientName;
    private String recipientPhone;
    private String recipientEmail;
    private String recipientAddress;
    private String paymentMethod;
    private String shippingMethod;
    private String description;

    public Bill() {
        this.orderDate = new Date();
    }

    public String getFormattedTotalAmount() {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(totalAmount);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void addItem(BillItem item) {
        items.add(item);
        item.setBill(this);
    }

    public void removeItem(BillItem item) {
        items.remove(item);
        item.setBill(null);
    }

    public List<BillItem> getItems() {
        return items;
    }

    public void setItems(List<BillItem> items) {
        this.items = items;
    }
}
