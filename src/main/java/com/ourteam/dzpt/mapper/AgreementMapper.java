package com.ourteam.dzpt.mapper;

import com.ourteam.dzpt.entity.Agreement;
import com.ourteam.dzpt.entity.AnalysisCertificate;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AgreementMapper {

  Agreement getAgreementInfo(int tradeId);

  int createAgreement(Agreement agreement);

  int setBuyerSign(Agreement agreement);

  int setSellerSign(Agreement agreement);

  AnalysisCertificate getQISInfo(int listedGoodsId);
}
