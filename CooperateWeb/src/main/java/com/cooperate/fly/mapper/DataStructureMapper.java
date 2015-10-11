package com.cooperate.fly.mapper;

import com.cooperate.fly.bo.DataStructure;

public interface DataStructureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataStructure record);

    int insertSelective(DataStructure record);

    DataStructure selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataStructure record);

    int updateByPrimaryKey(DataStructure record);
}