package com.ourteam.dzpt.service;

import com.ourteam.dzpt.entity.Agreement;
import com.ourteam.dzpt.mapper.AgreementMapper;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgreementService {

  @Autowired
  private AgreementMapper agreementMapper;

  public int createAgreement(Agreement agreement) {
    if (agreement.getBuyerSign() == null && agreement.getSellerSign() == null) {
      agreement.setStatus(0);
    }
    agreement.setCreateDate(LocalDateTime.now().toString());
    return agreementMapper.createAgreement(agreement);
  }

  public Map getAgreementInfo(int tradeId) {
    return agreementMapper.getAgreementInfo(tradeId);
  }

  public Map getQISInfo(int listedGoodsId) {
    return agreementMapper.getQISInfo(listedGoodsId);
  }

  public int setBuyerSign(Agreement agreement) {
    if (agreement.getBuyerSign() != null) {
      agreement.setStatus(1);
    }
    return agreementMapper.setBuyerSign(agreement);
  }

  public int setSellerSign(Agreement agreement) {
    if (agreement.getSellerSign() != null) {
      agreement.setStatus(2);
    }
    return agreementMapper.setSellerSign(agreement);
  }
}
