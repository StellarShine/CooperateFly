package com.cooperate.fly.mapper;

import com.cooperate.fly.bo.PackageVersion;

public interface PackageVersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PackageVersion record);

    int insertSelective(PackageVersion record);

    PackageVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PackageVersion record);

    int updateByPrimaryKey(PackageVersion record);
}