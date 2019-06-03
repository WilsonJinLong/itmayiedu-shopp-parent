package com.itmayiedu.manager.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.itmayiedu.common.api.BaseApiService;
import com.itmayiedu.common.redis.BaseRedisService;
import com.itmayiedu.constants.Constants;
import com.itmayiedu.manager.ShoppingCartManager;

@Service
public class ShoppingCartManagerImpl extends BaseApiService implements ShoppingCartManager {

	@Autowired
	private BaseRedisService baseRedisService;

	public Map<String, Object> addShopping(String userId, String itemId) {
		String value = baseRedisService.get(userId);
		if (StringUtils.isEmpty(value)) {
			JSONObject root = new JSONObject();
			root.put(itemId, itemId);
			baseRedisService.set(userId + "", root.toJSONString(), Constants.WEBUSER_SHOP_TERMVALIDITY);
		} else {
			JSONObject parseObject = new JSONObject().parseObject(value);
			String redisiTemId=parseObject.getString(itemId);
			if(!StringUtils.isEmpty(redisiTemId)){
				return setResutError("该商品已经加入到购物车");
			}
			//添加到redis中
			parseObject.put(itemId, itemId);
			baseRedisService.set(userId, parseObject.toJSONString(), Constants.WEBUSER_SHOP_TERMVALIDITY);
		}
		return setResutSuccess("加入购物车成功!");
	}

	public Map<String, Object> remoShopping(String userId, String itemId) {
		String value = baseRedisService.get(userId);
		if (StringUtils.isEmpty(value)) {
			return setResutError("该商品没有加入过购物车");
		}
		JSONObject parseObject = new JSONObject().parseObject(value);
		parseObject.remove(itemId);
		baseRedisService.set(userId, parseObject.toJSONString(), Constants.WEBUSER_SHOP_TERMVALIDITY);
		return setResutSuccess("删除购物车成功!");
	}

}
