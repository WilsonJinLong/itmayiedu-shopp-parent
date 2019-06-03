
package com.itmayiedu.common.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.itmayiedu.common.entity.BaseEntity;
import com.itmayiedu.common.entity.TestEntity;
import com.itmayiedu.utils.DateUtils;
import com.itmayiedu.utils.ReflectionUtils;

public class BaseProvider {

	/**
	 * 
	 * @methodDesc: 功能描述:(自定义封装sql语句)
	 * @param: @return
	 */
	public String save(Map<String, Object> map) {
		// 实体类
		Object oj = map.get("oj");
		// 表名称
		String table = (String) map.get("table");
		// 生成添加的sql语句。 使用反射机制
		// 步驟：使用反射机制加载这个类所有属性
		// INSERT INTO `mb_user` (username,password,phone,email,created,updated)
		// VALUES ('yushengjun2', 'e10adc3949ba59abbe56e057f20f883e',
		// '15527339673', 'aa1@a', '2015-04-06 17:03:55', '2015-04-06
		// 17:03:55');
		SQL sql = new SQL() {
			{

				INSERT_INTO(table);
				VALUES(ReflectionUtils.fatherAndSonField(oj), ReflectionUtils.fatherAndSonFieldValue(oj));

			}
		};

		return sql.toString();

	}

	/**
	 * 
	 * @methodDesc: 功能描述:(自定义封装sql语句)
	 * @param: @return
	 */
	static public String update(Map<String, Object> map) {
		// 实体类
		Object oj = map.get("oj");
		// 表名称
		String table = (String) map.get("table");
		// id
		Long id = (Long) map.get("id");
		// 生成添加的sql语句。 使用反射机制
		// 步驟：使用反射机制加载这个类所有属性
		// update mb_user set openid ='12323' where id='1'
		SQL sql = new SQL() {
			{

				UPDATE(table);
				SET(ReflectionUtils.setfatherAndSonFieldValue(oj));
				WHERE(" id= " + id);

			}
		};
		return sql.toString();
	}

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<>();
		TestEntity baseEntity = new TestEntity();
		baseEntity.setOpenId("12525");
		baseEntity.setUpdated(DateUtils.getTimestamp());
		map.put("oj", baseEntity);
		map.put("table", "mb_user");
		map.put("id", "1");
		String sql = update(map);
		System.out.println(sql);
	}

}
