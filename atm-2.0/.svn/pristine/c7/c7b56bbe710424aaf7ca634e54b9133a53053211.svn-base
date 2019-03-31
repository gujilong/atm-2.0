package com.dayuan.mapper;

import com.dayuan.entity.Transfer;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TransferMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Transfer record);

    int insertSelective(Transfer record);

    Transfer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Transfer record);

    int updateByPrimaryKey(Transfer record);

    List<Transfer> seByStatus(@Param("status") String status,
                              @Param("startIndex") Integer startIndex,
                              @Param("rows")Integer rows);

    int updateStatus(@Param("id") Integer id,
                     @Param("oldStatus")String oldStatus,
                     @Param("status") String status,
                     @Param("modifyTime") Date modifyTime);
}