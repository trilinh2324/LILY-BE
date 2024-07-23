package com.example.lily.Repository;

import com.example.lily.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long>{
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);// tim kiếm theo tên
    Page<Product> findByPriceLessThanEqual(Double price, Pageable pageable);//tìm kiếm theo giá
    List<Product> findByCategoryId(long id);
}
