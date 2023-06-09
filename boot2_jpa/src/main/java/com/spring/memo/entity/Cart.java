package com.spring.memo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Builder
@Data @AllArgsConstructor @NoArgsConstructor
public class Cart {
	//물건 담는 카트가 아니고, 카트 자체의 정보를 저장하는 클래스
	
	@Id
	@Column(name="cart_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	//관계 설정
	@OneToOne
	@JoinColumn(name="member_id")
	private Member member;
	
	
	
}
