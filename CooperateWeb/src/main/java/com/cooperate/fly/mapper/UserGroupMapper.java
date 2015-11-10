package com.cooperate.fly.mapper;

import com.cooperate.fly.bo.UserGroup;
import com.cooperate.fly.datasource.SqlMapper;

@SqlMapper
public interface UserGroupMapper {
    int deleteByPrimaryKey(Integer groupId);

    int insert(UserGroup record);

    int insertSelective(UserGroup record);

    UserGroup selectByPrimaryKey(Integer groupId);

    int updateByPrimaryKeySelective(UserGroup record);

    int updateByPrimaryKey(UserGroup record);
}