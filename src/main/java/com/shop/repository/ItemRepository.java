package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

	List<Item> findByName(String name);
	List<Item> findByNameOrDescription(String name, String description);
	List<Item> findByPriceLessThan(Integer price);
}
