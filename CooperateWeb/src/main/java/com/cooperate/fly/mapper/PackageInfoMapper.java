package com.cooperate.fly.mapper;

import com.cooperate.fly.bo.PackageInfo;

public interface PackageInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PackageInfo record);

    int insertSelective(PackageInfo record);

    PackageInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PackageInfo record);

    int updateByPrimaryKeyWithBLOBs(PackageInfo record);

    int updateByPrimaryKey(PackageInfo record);
}