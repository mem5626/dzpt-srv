package com.ourteam.dzpt.mapper;

import com.ourteam.dzpt.entity.Agreement;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AgreementMapper {

  Map getAgreementInfo(int tradeId);

  int createAgreement(Agreement agreement);

  int setBuyerSign(Agreement agreement);

  int setSellerSign(Agreement agreement);

  Map getQISInfo(int listedGoodsId);
}
