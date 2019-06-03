
package com.itmayiedu.adapter;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 
 * @classDesc: 功能描述:(所有消息都会交给他进行妆发)
 * @author: 蚂蚁课堂创始人-余胜军
 * @QQ: 644064779
 * @QQ粉丝群: 116295598
 * @createTime: 2017年10月25日 上午12:07:08
 * @version: v1.0
 * @copyright:每特学院(蚂蚁课堂)上海每特教育科技有限公司
 */
public interface MessageAdapter {
	//接受消息
    public void distribute(JSONObject jsonObject);
}
