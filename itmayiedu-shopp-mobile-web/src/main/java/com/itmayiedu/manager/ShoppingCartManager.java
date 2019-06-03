package com.itmayiedu.manager;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface ShoppingCartManager {
	public Map<String, Object> addShopping(String userId, String itemId);

	public Map<String, Object> remoShopping(String userId, String itemId);

}
