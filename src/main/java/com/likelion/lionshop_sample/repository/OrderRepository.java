package com.likelion.lionshop_sample.repository;

import com.likelion.lionshop_sample.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByNameIsContaining(String keyword);

    List<Order> findAllByUser_Email(String email);
}
