
package com.itmayiedu.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itmayiedu.common.mybatis.BaseDao;
import com.itmayiedu.entity.UserEntity;

@Mapper
public interface UserDao extends BaseDao {
	@Select("select ID,USERNAME,PASSWORD,phone,EMAIL, created,updated from mb_user  WHERE PHONE=#{phone} and password=#{password}")
	public UserEntity getUserPhoneAndPwd(@Param("phone") String userName, @Param("password") String password);

	@Select("select ID,USERNAME,PASSWORD,phone,EMAIL, created,updated from mb_user  WHERE id=#{id}")
	public UserEntity getUserInfo(@Param("id") Long id);

	@Select("select ID,USERNAME,PASSWORD,phone,EMAIL, created,updated from mb_user  WHERE openid=#{openid}")
	public UserEntity findOpenId(@Param("openid") String openid);
}
