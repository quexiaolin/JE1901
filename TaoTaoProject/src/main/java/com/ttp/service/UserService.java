package com.ttp.service;

import com.ttp.pojo.User;

public interface UserService {

	public User findByNameAndPwd(String name,String password);
}
