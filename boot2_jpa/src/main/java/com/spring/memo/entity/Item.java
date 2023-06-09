package com.spring.memo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Builder
@Entity
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class Item {
	
	@Id
	@Column(name = "item_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 상품코드
	
	@Column(nullable = false, length = 50)
	private String itemNm; // 상품명
	
	@Column(nullable = false)
	private int price; // 상품가격
	
	@Column(nullable = false)
	private int stockNumber; // 재고수량
	
	@Column(nullable = false)
	private String itemDetail; // 상품상세설명
	
	@Enumerated(EnumType.STRING)
	private ItemSellStatus itemSellStatus; // 상품 판매상태
	
	private LocalDateTime regTime; // 상품 등록일시
	private LocalDateTime updateTime; // 상품정보 수정일시
}
