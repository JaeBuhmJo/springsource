package com.spring.memo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.memo.entity.Item;

public interface itemRepository extends JpaRepository<Item, Long> {

	// findBy + 필드명

	// 상품 이름으로 검색
	List<Item> findByItemNm(String itemNm);

	// 다중 열 검색
	// findBy + 필드명 + or + 필드명
	List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

	// 특정 가격 이하인 상품 조회
	List<Item> findByPriceLessThan(int price);
	
	// 특정 가격 이하인 상품 조회 후 내림차순 정렬
	List<Item> findByPriceLessThanOrderByPriceDesc(int price);
}
