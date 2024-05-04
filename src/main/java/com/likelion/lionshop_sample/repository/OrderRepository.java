package com.likelion.lionshop_sample.repository;

import com.likelion.lionshop_sample.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
