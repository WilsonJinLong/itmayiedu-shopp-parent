
package com.itmayiedu.common.entity;

import java.sql.Timestamp;



import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @classDesc: 功能描述:(封装一些相同字段和属性)
 * @author: 蚂蚁课堂创始人-余胜军
 * @QQ: 644064779
 * @QQ粉丝群: 116295598
 * @createTime: 2017年10月24日 下午9:20:15
 * @version: v1.0
 * @copyright:每特学院(蚂蚁课堂)上海每特教育科技有限公司
 */
@Getter
@Setter
public class BaseEntity {

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 创建时间
	 */
	private Timestamp created;
	/**
	 * 修改时间
	 */
	private Timestamp updated;
//    public static void main(String[] args) {
//		log.info("我在使用lomBok  自动生成 get 和set 方法 还有自动日志");
//	}
}
