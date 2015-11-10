package com.cooperate.fly.mapper;

import com.cooperate.fly.bo.SysMenu;
import com.cooperate.fly.datasource.SqlMapper;

@SqlMapper
public interface SysMenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
}