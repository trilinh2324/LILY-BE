package com.example.lily.Service;

import com.example.lily.Model.Cart;
import com.example.lily.Model.Product;
import com.example.lily.Model.User;
import com.example.lily.Repository.ICartRepository;
import com.example.lily.Repository.IProductRepository;
import com.example.lily.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ICartRepository cartRepository;

    public ResponseEntity<String> addProductToCart(String name, long productId, int quantity) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            User user = userRepository.findByUserName(name);
            if (user != null) {
                Optional<Cart> existingCartItemOptional = cartRepository.findByProductIdAndUserId(productId, user.getId());
                if (existingCartItemOptional.isPresent()) {
                    Cart existingCartItem = existingCartItemOptional.get();
                    int newQuantity = existingCartItem.getQuantity() + quantity;

                    // Kiểm tra số lượng trong giỏ hàng với số lượng muốn thêm
                    if (newQuantity <= product.getQuantity()) {
                        existingCartItem.setQuantity(newQuantity);
                        cartRepository.save(existingCartItem);
                        return ResponseEntity.ok("Số lượng đã được cập nhật trong giỏ hàng.");
                    } else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Số lượng trong giỏ hàng không thể vượt quá số lượng sản phẩm có sẵn.");
                    }
                } else {
                    // Kiểm tra số lượng muốn thêm vào giỏ hàng có vượt quá số lượng sản phẩm không
                    if (quantity <= product.getQuantity()) {
                        Cart cartItem = new Cart(product, user, quantity);
                        cartRepository.save(cartItem);
                        return ResponseEntity.ok("Sản phẩm đã được thêm vào giỏ hàng thành công.");
                    } else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Số lượng sản phẩm muốn thêm vào giỏ hàng vượt quá số lượng sản phẩm có sẵn.");
                    }
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Không tìm thấy người dùng.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy sản phẩm.");
        }
    }


    public ResponseEntity<?> getCartByUser(String username) {
        User user = userRepository.findByUserName(username);
        if (user != null) {
            List<Cart> cartItems = cartRepository.findByUserId(user.getId());
            return ResponseEntity.ok(cartItems);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy người dùng.");
        }
    }

    public boolean removeFromCart(long id) {
        Optional<Cart> cartItemOptional = cartRepository.findById(id);
        if (cartItemOptional.isPresent()) {
            cartRepository.delete(cartItemOptional.get());
            return true;
        } else {
            return false;
        }
    }
    public void updateCartItemQuantity(Long cartItemId, int newQuantity) throws Exception {
        Cart cartItem = cartRepository.findById(cartItemId)
                .orElseThrow(() -> new Exception("Giỏ hàng không tồn tại"));

        Product product = cartItem.getProduct();

        // Kiểm tra số lượng yêu cầu có vượt quá số lượng sản phẩm không
        if (newQuantity > product.getQuantity()) {
            throw new Exception("Số lượng yêu cầu vượt quá số lượng sản phẩm có sẵn");
        }

        // Cập nhật số lượng trong giỏ hàng
        cartItem.setQuantity(newQuantity);
        cartRepository.save(cartItem);
    }
}
