package com.spring.memo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.memo.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
