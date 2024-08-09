package com.example.lily.Repository;

import com.example.lily.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(Long userId);
    List<Cart> findByUserId(long userId);
    Optional<Cart> findByProductIdAndUserId(long productId, long userId);
}
