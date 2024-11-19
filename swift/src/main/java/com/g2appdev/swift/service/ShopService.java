package com.g2appdev.swift.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2appdev.swift.entity.ShopEntity;
import com.g2appdev.swift.repository.ShopRepository;

@Service
public class ShopService {
	@Autowired
	private ShopRepository shopRepository;

	public ShopEntity postShopRecord(ShopEntity shop) {
		return shopRepository.save(shop);
	}

	public List<ShopEntity> getAllShop() {
		return shopRepository.findAll();
	}

	public ShopEntity putShopDetails(int itemId, ShopEntity newShopDetails) {
		Optional<ShopEntity> optionalShop = shopRepository.findById(itemId);
		if (optionalShop.isPresent()) {
			ShopEntity shop = optionalShop.get();
			shop.setItemName(newShopDetails.getItemName());
			shop.setItemCost(newShopDetails.getItemCost());
			shop.setItemUrl(newShopDetails.getItemUrl());
			return shopRepository.save(shop);
		} else {
			throw new RuntimeException("Shop not found with ID: " + itemId);
		}
	}

	public String deleteShop(int itemId) {
		if (shopRepository.existsById(itemId)) {
			shopRepository.deleteById(itemId);
			return "Shop with ID " + itemId + " has been deleted.";
		} else {
			throw new RuntimeException("Shop not found with ID: " + itemId);
		}
	}

	public ShopEntity getShopItemById(int id) {
		Optional<ShopEntity> optionalShop = shopRepository.findById(id);
		return optionalShop.orElseThrow(() -> new RuntimeException("Shop item not found with ID: " + id));
	}
}
