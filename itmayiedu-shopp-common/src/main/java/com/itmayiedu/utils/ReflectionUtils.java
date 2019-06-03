
package com.itmayiedu.utils;

import java.lang.reflect.Field;
import java.sql.Timestamp;

import org.apache.ibatis.jdbc.SQL;

import com.itmayiedu.common.entity.TestEntity;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @classDesc: 功能描述:(反射工具类F)
 * @author: 蚂蚁课堂创始人-余胜军
 * @QQ: 644064779
 * @QQ粉丝群: 116295598
 * @createTime: 2017年10月24日 下午9:45:46
 * @version: v1.0
 * @copyright:每特学院(蚂蚁课堂)上海每特教育科技有限公司
 */
@Slf4j
public class ReflectionUtils {

	/**
	 * 
	 * @methodDesc: 功能描述:(封装当前类和父类的所有属性 拼接属性sql)
	 * @param: @return
	 */
	public static String fatherAndSonField(Object oj) {
		if (oj == null) {
			return null;
		}
		// 获取class文件
		Class classInfo = oj.getClass();
		// 获取当前类属性sql
		Field[] sonFields = classInfo.getDeclaredFields();
		String s1 = getField(sonFields);
		Field[] panretFields = classInfo.getSuperclass().getDeclaredFields();
		String s2 = getField(panretFields);
		return s1 + "," + s2;
	}

	/**
	 * 
	 * @methodDesc: 功能描述:(获取到属性值)
	 * @param: @param
	 *             oj
	 * @param: @return
	 */
	public static String fatherAndSonFieldValue(Object oj) {
		if (oj == null) {
			return null;
		}
		// 获取class文件
		Class classInfo = oj.getClass();
		// 获取当前类属性sql
		Field[] sonFields = classInfo.getDeclaredFields();
		String s1 = getFieldValue(oj, sonFields);
		Field[] panretFields = classInfo.getSuperclass().getDeclaredFields();

		String s2 = getFieldValue(oj, panretFields);
		return s1 + "," + s2;
	}

	/**
	 * 
	 * @methodDesc: 功能描述:(获取到属性值)
	 * @param: @param
	 *             oj
	 * @param: @return
	 */
	public static String setfatherAndSonFieldValue(Object oj) {
		if (oj == null) {
			return null;
		}
		// 获取class文件
		Class classInfo = oj.getClass();
		// 获取当前类属性sql
		Field[] sonFields = classInfo.getDeclaredFields();
		String s1 = setField(sonFields, oj);
		Field[] panretFields = classInfo.getSuperclass().getDeclaredFields();
		String s2 = setField(panretFields, oj);
		return s1 + "," + s2;
	}

	public static String setField(Field[] declaredFields, Object oj) {
		StringBuffer sf = new StringBuffer();
		try {
			int count = 0;
			for (int i = 0; i < declaredFields.length; i++) {
				Field field = declaredFields[i];
				field.setAccessible(true);
				Object object = field.get(oj);
				if (object == null) {
					continue;
				}
				count++;
				sf.append(field.getName() + "='" + object + "'");
				if (count > 1 && i < declaredFields.length - 1) {
					sf.append(",");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sf.toString();
	}

	public static String getField(Field[] declaredFields) {
		StringBuffer sf = new StringBuffer();
		for (int i = 0; i < declaredFields.length; i++) {
			sf.append(declaredFields[i].getName());
			if (i < declaredFields.length - 1) {
				sf.append(",");
			}
		}
		return sf.toString();
	}

	public static String getFieldValue(Object oj, Field[] declaredFields) {
		StringBuffer sf = new StringBuffer();
		for (int i = 0; i < declaredFields.length; i++) {
			// 获取到属性值
			try {
				Field field = declaredFields[i];
				// 运行操作私有属性
				field.setAccessible(true);
				Object value = field.get(oj);
				// 标识类型是否为string类型
				boolean flag = false;
				if (value != null && (value instanceof String || value instanceof Timestamp)) {
					flag = true;
				}
				if (flag) {
					sf.append("'");
					sf.append(value);
					sf.append("'");
				} else {
					sf.append(value);
				}
				if (i < declaredFields.length - 1) {
					sf.append(",");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sf.toString();
	}

	public static void main(String[] args) {
		TestEntity testEntity = new TestEntity();
		testEntity.setUserName("张三");
		testEntity.setPhone("15921009245");
		testEntity.setEmail("644064779@qq.com");
		testEntity.setPassword("123456");
		testEntity.setCreated(DateUtils.getTimestamp());
		testEntity.setUpdated(DateUtils.getTimestamp());
		String filed = fatherAndSonField(testEntity);
		String value = fatherAndSonFieldValue(testEntity);
		// 封装sql

	}

}
