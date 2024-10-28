package com.g2appdev.swift.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.g2appdev.swift.entity.ShopEntity;
import com.g2appdev.swift.service.ShopService;

@RestController
@RequestMapping(method = RequestMethod.GET, path="/api/shop")
public class ShopController {
	@Autowired
	ShopService sserv;
	@GetMapping("/print")
	public String print() {
		return "Welcome to our shop";
	}
	@PostMapping("/postShopRecord")
	public ShopEntity postShopRecord(@RequestBody ShopEntity shop) {
		return sserv.postShopRecord(shop);
	}
	@GetMapping("/getAllShop")
	public List<ShopEntity> getAllShop(){
		return sserv.getAllShop();
	}
	@PutMapping("/putShopDetails")
	public ShopEntity putShopDetails(@RequestParam int itemId, @RequestBody ShopEntity newShopDetails) {
		return sserv.putShopDetails(itemId, newShopDetails);
	}
	@DeleteMapping("/deleteShopDetails/{itemId}")
	public String deleteShop(@PathVariable int itemId) {
		return sserv.deleteShop(itemId);
	}
	
}
