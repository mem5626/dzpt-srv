package com.ourteam.dzpt.mapper;

import com.ourteam.dzpt.entity.Agreement;
import com.ourteam.dzpt.entity.AnalysisCertificate;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AgreementMapper {

  Map getAgreementInfo(int tradeBillId);

  int createAgreement(Agreement agreement);

  int cancelAgreement(Integer id);

  Agreement selectAgreementById(Integer id);

  int setBuyerSign(Agreement agreement);

  int setSellerSign(Agreement agreement);

  AnalysisCertificate getQISInfo(int listedGoodsId);
}
