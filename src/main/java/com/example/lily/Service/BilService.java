package com.example.lily.Service;

import com.example.lily.Model.Bill;
import com.example.lily.Repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BilService implements IBillService {
    @Autowired
    private BillRepository billRepository;

    @Override
    public Page<Bill> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Bill> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public void remove(long id) {

    }
}
