package com.spring.memo.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.memo.entity.Item;
import com.spring.memo.entity.ItemSellStatus;

@SpringBootTest
public class ItemRepositoryTest {

	// ItemRepository가 잘 만들어졌는지 확인용
	@Autowired
	private itemRepository repository;

//	@Test
//	public void itemCreateTest() {
//		Item item = new Item();
//		item.setItemNm("순수 프리미엄");
//		item.setPrice(29500);
//		item.setStockNumber(55);
//		item.setItemDetail("깨끗한 나라");
//		item.setItemSellStatus(ItemSellStatus.SELL);
//		item.setRegTime(LocalDateTime.now());
//		item.setUpdateTime(LocalDateTime.now());
//		repository.save(item);
//
//		item = Item.builder()
//						.itemNm("프롬비")
//						.price(45800)
//						.stockNumber(70)
//						.itemDetail("휴대용 선풍기")
//						.itemSellStatus(ItemSellStatus.SELL)
//						.regTime(LocalDateTime.now())
//						.updateTime(LocalDateTime.now())
//						.build();
//		
//		repository.save(item);
//	}

//
//	@Test
//	public void getItem() {
//		Optional<Item> item = repository.findById(1L);
//		item.ifPresent(ele -> System.out.println(ele));
//		Item item2 = repository.findById(2L).orElseThrow(EntityNotFoundException::new);
//		System.out.println(item2);
//	}
//
//	@Test
//	public void getItems() {
//		List<Item> list = repository.findAll();
//		list.forEach(k -> System.out.println(k));
//	}

//	@Test
//	public void getItems() {
//		List<Item> list = repository.findByItemNm("블루투스 스피커");
//		list.forEach(k -> System.out.println(k));
//	}
//	@Test
//	public void getNameOrDetail() {
//		List<Item> list = repository.findByItemNmOrItemDetail("블루투스 스피커","카라티");
//		list.forEach(k -> System.out.println(k));
//	}

//	@Test
//	public void getPriceLessThan() {
//		repository.findByPriceLessThan(60000).forEach(k->System.out.println(k));
//		
//	}

	@Test
	public void getPriceLessThanOrder() {
		repository.findByPriceLessThanOrderByPriceDesc(70000)
				  .forEach(k -> System.out.println(k));
	}
}
