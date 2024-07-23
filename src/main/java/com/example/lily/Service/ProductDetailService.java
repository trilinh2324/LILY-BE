package com.example.lily.Service;

import com.example.lily.Model.ProductDetail;
import com.example.lily.Repository.IProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductDetailService implements IProductDetailService{
    @Autowired
    private IProductDetailRepository iProductDetailRepository;

    @Override
    public Page<ProductDetail> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ProductDetail> findById(Long id) {
        return iProductDetailRepository.findById(id);
    }

    @Override
    public ProductDetail save(ProductDetail productDetail) {
        return null;
    }

    @Override
    public void remove(long id) {

    }
    public Optional<ProductDetail> getProductDetailById(long id) {
        return iProductDetailRepository.findById(id);
    }
}
