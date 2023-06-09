package com.spring.memo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.memo.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
