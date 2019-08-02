package com.ourteam.dzpt.service;

import com.ourteam.dzpt.entity.Agreement;
import com.ourteam.dzpt.entity.AnalysisCertificate;
import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.mapper.AgreementMapper;
import com.ourteam.dzpt.mapper.TradeBillMapper;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AgreementService {

  @Autowired
  private AgreementMapper agreementMapper;
  @Autowired
  private TradeBillMapper tradeBillMapper;

  @Transactional
  public int createAgreement(Agreement agreement) {
    if (getAgreementInfo(agreement.getTradeBillId()) != null) {
      throw new GlobalException(ExceptionMsg.AgreementExit);
    }
    tradeBillMapper.setTradeBillStatus(2,agreement.getTradeBillId());
    if (agreement.getBuyerSign() == null && agreement.getSellerSign() == null) {
      agreement.setStatus(0);
    }
    agreement.setCreateDate(LocalDateTime.now().toString());
    return agreementMapper.createAgreement(agreement);
  }

  public Map getAgreementInfo(int tradeBillId) {
    return agreementMapper.getAgreementInfo(tradeBillId);
  }

  public AnalysisCertificate getQISInfo(int listedGoodsId) {
    return agreementMapper.getQISInfo(listedGoodsId);
  }

  public int setBuyerSign(Agreement agreement) {
    agreement.setStatus(1);
    return agreementMapper.setBuyerSign(agreement);
  }

  public int setSellerSign(Agreement agreement) {
    agreement.setStatus(2);
    return agreementMapper.setSellerSign(agreement);
  }
}
