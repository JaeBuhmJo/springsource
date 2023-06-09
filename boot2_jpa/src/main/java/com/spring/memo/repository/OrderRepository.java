package com.spring.memo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.memo.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {

}
