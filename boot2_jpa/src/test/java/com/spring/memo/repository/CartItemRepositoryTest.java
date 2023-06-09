package com.spring.memo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.memo.entity.Cart;
import com.spring.memo.entity.CartItem;
import com.spring.memo.entity.Item;

@SpringBootTest
public class CartItemRepositoryTest {

	@Autowired
	private CartItemRepository repository;
	
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private itemRepository itemRepository;
	
	@Test
	public void createCartItem() {
		
		Cart cart = cartRepository.findById(2L).orElseThrow();
		Item item = itemRepository.findById(4L).orElseThrow();
		
		CartItem cartItem = CartItem.builder()
									.cart(cart)
									.item(item)
									.count(5)
									.build();
		
		repository.save(cartItem);
		System.out.println(cartItem);
	}
}
