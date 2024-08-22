package com.example.lily.Repository;

import com.example.lily.Model.Bill;
import com.example.lily.Model.BillItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  BillItemRepository extends JpaRepository<BillItem, Long> {
}
