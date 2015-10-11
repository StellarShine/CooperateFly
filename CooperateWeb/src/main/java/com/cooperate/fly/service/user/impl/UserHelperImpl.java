package com.cooperate.fly.service.user.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooperate.fly.bo.UserInfo;
import com.cooperate.fly.mapper.UserInfoMapper;
import com.cooperate.fly.service.user.UserHelper;

@Service("UserService")
public class UserHelperImpl implements UserHelper{
	
	@Autowired
	UserInfoMapper userInfoMapper;
	
	Logger log=Logger.getLogger(getClass());

	@Override
	public void addUser(UserInfo user) {
		userInfoMapper.insert(user);		
	}

}
