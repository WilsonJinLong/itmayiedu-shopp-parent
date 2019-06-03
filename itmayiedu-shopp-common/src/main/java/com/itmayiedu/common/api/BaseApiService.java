
package com.itmayiedu.common.api;

import java.util.HashMap;
import java.util.Map;


import com.itmayiedu.constants.BaseApiConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @classDesc: 功能描述:(通用baseapi 父类)
 * @author: 蚂蚁课堂创始人-余胜军
 * @QQ: 644064779
 * @QQ粉丝群: 116295598
 * @createTime: 2017年10月23日 下午11:16:35
 * @version: v1.0
 * @copyright:每特学院(蚂蚁课堂)上海每特教育科技有限公司
 */
public class BaseApiService {
  
	/**
	 * 
	 * @methodDesc: 功能描述:(返回错误)
	 * @param: @param
	 *             msg
	 * @param: @return
	 */
	public Map<String, Object> setResutError(String msg) {
		return setResut(BaseApiConstants.HTTP_500_CODE, msg, null);
	}

	/**
	 * 
	 * @methodDesc: 功能描述:(返回成功)
	 * @param: @param
	 *             msg
	 * @param: @return
	 */
	public Map<String, Object> setResutSuccessData(Object data) {
		return setResut(BaseApiConstants.HTTP_200_CODE, BaseApiConstants.HTTP_SUCCESS_NAME, data);
	}

	/**
	 * 
	 * @methodDesc: 功能描述:(返回成功)
	 * @param: @param
	 *             msg
	 * @param: @return
	 */
	public Map<String, Object> setResutSuccess() {
		return setResut(BaseApiConstants.HTTP_200_CODE, BaseApiConstants.HTTP_SUCCESS_NAME, null);
	}

	/**
	 * 
	 * @methodDesc: 功能描述:(返回成功)
	 * @param: @param
	 *             msg
	 * @param: @return
	 */
	public Map<String, Object> setResutParameterError(String msg) {
		return setResut(BaseApiConstants.HTTP_400_CODE, msg, null);
	}
	/**
	 * 
	 * @methodDesc: 功能描述:(返回成功)
	 * @param: @param
	 *             msg
	 * @param: @return
	 */
	public Map<String, Object> setResutSuccess(String msg) {
		return setResut(BaseApiConstants.HTTP_200_CODE, msg, null);
	}
	/**
	 * 
	 * @methodDesc: 功能描述:(自定义返回)
	 * @param: @param
	 *             code
	 * @param: @param
	 *             msg
	 * @param: @param
	 *             data
	 * @param: @return
	 */
	public Map<String, Object> setResut(Integer code, String msg, Object data) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(BaseApiConstants.HTTP_CODE_NAME, code);
		result.put(BaseApiConstants.HTTP_200_NAME, msg);
		if (data != null)
			result.put(BaseApiConstants.HTTP_DATA_NAME, data);
		return result;
	}

}
