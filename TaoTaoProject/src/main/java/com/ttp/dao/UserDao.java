package com.ttp.dao;

import org.apache.ibatis.annotations.Param;

import com.ttp.pojo.User;

public interface UserDao {

	public User findByNameAndPwd(@Param("name")String name,
			@Param("password")String password);
}
