package com.itmayiedu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itmayiedu.api.service.ItemService;
import com.itmayiedu.common.api.BaseApiService;
import com.itmayiedu.dao.ItemCatDao;
import com.itmayiedu.dao.ItemDao;
import com.itmayiedu.entity.ItemCatEntity;
import com.itmayiedu.entity.ItemEntity;

@RestController
public class ItemServiceImpl extends BaseApiService implements ItemService {
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private ItemCatDao itemCatDao;

	@RequestMapping("/getIndexItem")
	public Map<String, Object> getIndexItem() {
		// 查询所有的类型
		List<ItemCatEntity> listItemCat = itemCatDao.allItemCat();
		Map<String, Object> reuslt = new HashMap<String, Object>();
		for (ItemCatEntity itemCatEntity : listItemCat) {
			Long id = itemCatEntity.getId();
			String name = itemCatEntity.getName();
			List<ItemEntity> listItem = itemDao.getIndexItem(id);
			if (!(listItem.isEmpty() && listItem.size() <= 0)) {
				reuslt.put(name, listItem);
			}

		}
		return setResutSuccessData(reuslt);
	}

	@Override
	public Map<String, Object> geItem(@RequestParam("id") Long id) {
		ItemEntity item = itemDao.getItem(id);
		if(item==null){
			return setResutError("没有查询到结果");
		}
		return setResutSuccessData(item);
	}

}
