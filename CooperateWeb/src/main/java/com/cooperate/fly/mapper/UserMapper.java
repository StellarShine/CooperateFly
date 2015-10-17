package com.cooperate.fly.mapper;

import com.cooperate.fly.bo.User;
import com.cooperate.fly.datasource.SqlMapper;

@SqlMapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);
    int deleteMessageByUserName(String userName);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);
    User selectByUserName(String userName);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
}