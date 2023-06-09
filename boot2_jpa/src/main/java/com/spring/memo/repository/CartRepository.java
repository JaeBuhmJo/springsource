package com.spring.memo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.memo.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
