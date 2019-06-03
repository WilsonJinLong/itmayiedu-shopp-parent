
package com.itmayiedu.common.mybatis;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

public interface BaseDao {

	/**
	 * @InsertProvider 注解作用， 自定义sql语句.
	 */

	@InsertProvider(type = BaseProvider.class, method = "save")
	@Options(useGeneratedKeys = true, keyProperty = "id") // 添加该行，product中的id将被自动添加
	public void save(@Param("oj") Object oj, @Param("table") String table);

	/**
	 * @InsertProvider 注解作用， 自定义sql语句.
	 */

	@InsertProvider(type = BaseProvider.class, method = "update")
	public void update(@Param("oj") Object oj, @Param("table") String tablem, @Param("id") Long id);

}
