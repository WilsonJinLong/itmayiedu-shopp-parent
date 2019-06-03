package com.itmayiedu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itmayiedu.api.service.ItemService;
import com.itmayiedu.base.controller.BaseController;
import com.itmayiedu.constants.BaseApiConstants;
import com.itmayiedu.constants.Constants;
import com.itmayiedu.entity.UserEntity;
import com.itmayiedu.manager.ShoppingCartManager;
import com.itmayiedu.web.CookieUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController extends BaseController {
	// index页面
	public static final String INDEX = "index" + "";
	@Autowired
	private ItemService itemService;
	@Autowired
	private ShoppingCartManager shoppingCartManager;
	private static final String LGOIN = "login";

	/**
	 * 首页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		// 查询所有商品
		Map<String, Object> reusltMapItem = itemService.getIndexItem();
		Integer code = (Integer) reusltMapItem.get(BaseApiConstants.HTTP_CODE_NAME);
		if (code.equals(BaseApiConstants.HTTP_200_CODE)) {
			Map<String, Object> mapItem = (Map<String, Object>) reusltMapItem.get("data");
			request.setAttribute("mapItem", mapItem);
		}
		return INDEX;
	}

	/**
	 * 加入购物车
	 * 
	 * @return
	 */
	@RequestMapping("/addShopping")
	@ResponseBody
	public Map<String, Object> addShopping(HttpServletRequest request, String itemId) {
		String token = CookieUtil.getUid(request, Constants.USER_TOKEN);
		if (StringUtils.isEmpty(token)) {
			return setResutError("您没有登录,请先登录后,才可以加入到购物车.");
		}
		UserEntity userEntity = getUserEntity(token);
		if (userEntity == null) {
			return setResutError("您没有登录,请先登录后,才可以加入到购物车.");
		}
		try {
			return shoppingCartManager.addShopping(userEntity.getId() + "", itemId);
		} catch (Exception e) {
			log.error("addShopping() ERROR:", e);
			return setResutError("加入购物车失败,请稍后重试!");
		}

	}

}
