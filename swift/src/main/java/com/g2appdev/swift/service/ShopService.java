package com.g2appdev.swift.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2appdev.swift.entity.ShopEntity;
import com.g2appdev.swift.repository.ShopRepository;

@Service
public class ShopService {
	@Autowired
	ShopRepository srepo;

	public ShopService() {
		super();
	}

	public ShopEntity postShopRecord(ShopEntity shop) {
		return srepo.save(shop);
	}

	public List<ShopEntity> getAllShop() {
		return srepo.findAll();
	}

	@SuppressWarnings("finally")
	public ShopEntity putShopDetails(int itemId, ShopEntity newShopDetails) {
		ShopEntity shop = new ShopEntity();
		try {
			shop = srepo.findById(itemId).get();

			shop.setItemName(newShopDetails.getItemName());
			shop.setItemCost(newShopDetails.getItemCost());
			shop.setItemUrl(newShopDetails.getItemUrl());

		} catch (NoSuchElementException nex) {
			throw new NameNotFoundException("Item not found");
		} finally {
			return srepo.save(shop);
		}
	}

	public String deleteShop(int itemId) {
		String msg = "";
		if (srepo.findById(itemId).isPresent()) {
			srepo.deleteById(itemId);
			msg = "Shop Record successfully deleted!";
		} else {
			msg = itemId + "Not Found!";
		}
		return msg;
	}
}
