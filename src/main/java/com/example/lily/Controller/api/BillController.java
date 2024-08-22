package com.example.lily.Controller.api;

import com.example.lily.Model.Bill;
import com.example.lily.Model.BillItem;
import com.example.lily.Model.Product;
import com.example.lily.Repository.BillItemRepository;
import com.example.lily.Repository.BillRepository;
import com.example.lily.Repository.IProductRepository;

import com.example.lily.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bill")
@CrossOrigin(origins = "http://localhost:3000")
public class BillController {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillItemRepository billItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IProductRepository productRepository;
    @PostMapping("/create")
    public ResponseEntity<String> createBill(@RequestBody Bill bill) {
        try {
            Bill savedBill = billRepository.save(bill);
            for (BillItem item : bill.getItems()) {
                // Gán sản phẩm từ productId
                Product product = productRepository.findById(item.getProductId()).orElse(null);
                if (product != null) {
                    item.setProduct(product);
                    item.setPrice(product.getPrice()); // Cập nhật giá sản phẩm
                }
                item.setBill(savedBill);
                billItemRepository.save(item);
            }
            return ResponseEntity.ok("Bill created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error creating bill.");
        }
    }




}
