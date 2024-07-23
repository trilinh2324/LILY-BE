package com.example.lily.Repository;


import com.example.lily.Model.Product;
import com.example.lily.Model.ProductDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductDetailRepository extends JpaRepository<ProductDetail,Long> {
}
