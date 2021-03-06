package com.dayuan.mapper;

import com.dayuan.entity.Card;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Card record);

    int insertSelective(Card record);

    Card selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Card record);

    int updateByPrimaryKey(Card record);
    Card seByC(String cardNo);
    Card seByCUpData(String cardNo);

   int upBalance(@Param("CardNo") String CardNo ,
                 @Param("Balance") String Balance ,
                 @Param("OldVersion") Integer OldVersion);

   List<String> findCardNoByUserid(Integer userId);

   List<Card> findCardByUserid(Integer userId);

   int upBalanceNoLock(@Param("CardNo") String CardNo ,
                       @Param("Balance") String Balance);
}