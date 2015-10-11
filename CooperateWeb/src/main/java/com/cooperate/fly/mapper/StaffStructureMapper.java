package com.cooperate.fly.mapper;

import com.cooperate.fly.bo.StaffStructure;

public interface StaffStructureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StaffStructure record);

    int insertSelective(StaffStructure record);

    StaffStructure selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StaffStructure record);

    int updateByPrimaryKey(StaffStructure record);
}