package com.dayuan.mapper;

import com.dayuan.entity.Flow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FlowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Flow record);

    int insertSelective(Flow record);

    Flow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Flow record);

    int updateByPrimaryKey(Flow record);



    int seTolflowByC(String cardNo);


    List<Flow> seflowByC(@Param("cardNo") String cardNo,
                         @Param("startIndex")Integer startIndex,
                         @Param("pageRows")Integer pageRows);

    List<Flow> seTenflowByUserId(Integer userId);
}