package com.ttp.service.impl;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttp.dao.UserDao;
import com.ttp.pojo.User;
import com.ttp.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao dao;

	public User findByNameAndPwd(String name, String password) {
		SimpleHash simpleHash=new SimpleHash("Md5", password, name, 1024);
		String md5pwd=simpleHash.toString();
		return dao.findByNameAndPwd(name, md5pwd);
	}

}
