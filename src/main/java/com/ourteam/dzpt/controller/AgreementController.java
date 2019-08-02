package com.ourteam.dzpt.controller;

import com.ourteam.dzpt.entity.Agreement;
import com.ourteam.dzpt.entity.AnalysisCertificate;
import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.Response;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.service.AgreementService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AgreementController {

  @Autowired
  private AgreementService agreementService;

  @RequestMapping(value = "/order/createAgreement", method = RequestMethod.POST)
  public Response createAgreement(HttpServletRequest request, @RequestBody Agreement agreement) {
    if (agreementService.createAgreement(agreement) == 1) {
      return new Response(ExceptionMsg.Success);
    } else {
      return new Response(ExceptionMsg.Error);
    }
  }

  @RequestMapping(value = "/order/getAgreementInfo", method = RequestMethod.GET)
  public Response getAgreementInfo(Integer tradeBillId) {
    if (tradeBillId == null) {
      throw new GlobalException(ExceptionMsg.ParameterError);
    }
    return new Response(ExceptionMsg.Success,
        agreementService.getAgreementInfo(tradeBillId));
  }

  @RequestMapping(value = "/order/getQISInfo", method = RequestMethod.GET)
  public Response getQISInfo(AnalysisCertificate QIS) {
    return new Response(ExceptionMsg.Success, agreementService.getQISInfo(QIS.getListedGoodsId()));
  }

  @RequestMapping(value = "/order/buyerSign", method = RequestMethod.POST)
  public Response setBuyerSign(HttpServletRequest request, @RequestBody Agreement agreement)
      throws GlobalException {
    if (agreement.getBuyerSign() == null) {
      throw new GlobalException(ExceptionMsg.ParameterError);
    }
    if (agreementService.setBuyerSign(agreement) == 1) {
      return new Response(ExceptionMsg.Success);
    } else {
      return new Response(ExceptionMsg.Error);
    }
  }

  @RequestMapping(value = "/order/sellerSign", method = RequestMethod.POST)
  public Response setSellerSign(HttpServletRequest request, @RequestBody Agreement agreement)
      throws GlobalException {
    if (agreement.getSellerSign() == null) {
      throw new GlobalException(ExceptionMsg.ParameterError);
    }
    if (agreementService.setSellerSign(agreement) == 1) {
      return new Response(ExceptionMsg.Success);
    } else {
      return new Response(ExceptionMsg.Error);
    }
  }
}
