package com.example.lily.Controller.api;


import com.example.lily.Model.Cart;
import com.example.lily.Model.Product;
import com.example.lily.Model.User;

import com.example.lily.Repository.ICartRepository;
import com.example.lily.Repository.IProductRepository;
import com.example.lily.Repository.UserRepository;
import com.example.lily.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:3000") // Cấu hình CORS
public class CartControllerAPI {

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private CartService cartService;




    @PostMapping("/addToCart/{productId}/{name}")
    public ResponseEntity<String> addProductToCart(@PathVariable String name, @PathVariable long productId, @RequestParam int quantity) {
        return cartService.addProductToCart(name, productId, quantity);
    }


    @GetMapping("/cart/{username}")
    public ResponseEntity<?> getCartByUser(@PathVariable String username) {
        return cartService.getCartByUser(username);
    }
    @DeleteMapping("/removeFromCart/{id}")
    public ResponseEntity<?> removeFromCart(@PathVariable long id) {
        Optional<Cart> cartItemOptional = cartRepository.findById(id);
        if (cartItemOptional.isPresent()) {
            cartRepository.delete(cartItemOptional.get());
            return ResponseEntity.ok("Sản phẩm đã được xóa khỏi giỏ hàng thành công.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Cart>> getCartByUserId(@PathVariable Long userId) {
        List<Cart> cartItems = cartRepository.findByUserId(userId);
        return ResponseEntity.ok(cartItems);
    }


    @PatchMapping("/updateQuantity/{cartItemId}")
    public ResponseEntity<String> updateCartItemQuantity(
            @PathVariable("cartItemId") Long cartItemId,
            @RequestBody Cart updatedCart) {

        try {
            cartService.updateCartItemQuantity(cartItemId, updatedCart.getQuantity());
            return ResponseEntity.ok("Cập nhật số lượng sản phẩm thành công.");
        } catch (Exception e) {
            // Trả về thông báo lỗi cụ thể
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


}

