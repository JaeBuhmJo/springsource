package com.spring.memo.repository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.memo.entity.Cart;
import com.spring.memo.entity.Member;

@SpringBootTest
public class CartRepositoryTest {

	@Autowired
	private CartRepository repository;

	@Autowired
	private MemberRepository memberRepository;

//	@Test
//	public void createCart() {
//
//		Optional<Member> member = memberRepository.findById(1L);
//
//		member.ifPresent(k -> {
//			Cart cart = Cart.builder().member(k).build();
//			repository.save(cart);
//		});
//		if(member.isPresent()) {
//			Cart cart = Cart.builder()
//					.member(member.get())
//					.build();
//			repository.save(cart);
//		}
//	}
	
	@Test
	public void getCart() {
		Optional<Cart> cart = repository.findById(2L);
		
		cart.ifPresent(k->System.out.println(k));
	}
}
