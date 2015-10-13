package com.cooperate.fly.mapper;

import com.cooperate.fly.bo.DataInfo;
import com.cooperate.fly.bo.DataInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataInfoMapper {
    int countByExample(DataInfoExample example);

    int deleteByExample(DataInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataInfo record);

    int insertSelective(DataInfo record);

    List<DataInfo> selectByExample(DataInfoExample example);

    DataInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataInfo record, @Param("example") DataInfoExample example);

    int updateByExample(@Param("record") DataInfo record, @Param("example") DataInfoExample example);

    int updateByPrimaryKeySelective(DataInfo record);

    int updateByPrimaryKey(DataInfo record);
}