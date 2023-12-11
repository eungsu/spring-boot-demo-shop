package com.shop.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.shop.constant.ItemStatus;
import com.shop.entity.Item;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class ItemRepositoryTest {

	@Autowired
	ItemRepository itemRepository;
	
	private void createItemList() {
		for (int i=1; i<=10; i++) {
			Item item = new Item();
			item.setName("테스트 상품" + i);
			item.setPrice(10000);
			item.setDescription("테스트 상품 상세 설명");
			item.setStatus(ItemStatus.SELL);
			item.setStock(100);
			item.setCreateTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());
			
			itemRepository.save(item);
		}
	}
	
	@Test
	@DisplayName("상품 저장 테스트")
	public void saveItemTest() {
		Item item = new Item();
		item.setName("테스트 상품");
		item.setPrice(10000);
		item.setDescription("테스트 상품 상세 설명");
		item.setStatus(ItemStatus.SELL);
		item.setStock(100);
		item.setCreateTime(LocalDateTime.now());
		item.setUpdateTime(LocalDateTime.now());
		
		Item savedItem = itemRepository.save(item);
		
		System.out.println(savedItem);
	}
	
	@Test
	@DisplayName("상품명 조회 테스트")
	public void findByNameTest() {
		
		this.createItemList();
		
		List<Item> items = itemRepository.findByName("테스트 상품1");
		assertEquals(1, items.size());		
	}
	
	@Test
	@DisplayName("상품명, 상품상세설명 OR 테스트")
	public void findByNameOrDescriptionTest() {
		
		this.createItemList();
		
		List<Item> items = itemRepository.findByNameOrDescription("테스트 상품1", "테스트 상품 상세 설명1");
		assertEquals(1, items.size());		
	}
	
	@Test
	@DisplayName("가격 LessThan 테스트")
	public void findByPriceLessThanTest() {
		
		this.createItemList();
		
		List<Item> items = itemRepository.findByPriceLessThan(15000);
		assertEquals(10, items.size());
	}
	
	
	
}
