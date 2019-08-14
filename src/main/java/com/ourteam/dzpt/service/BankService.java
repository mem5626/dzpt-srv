package com.ourteam.dzpt.service;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankPayCpayCppayapplyRequestV1;
import com.icbc.api.request.MybankPayCpayCppayapplyRequestV1.BeanGoodsInfo;
import com.icbc.api.request.MybankPayCpayCppayapplyRequestV1.BeanRecvMallInfo;
import com.icbc.api.request.MybankPayCpayCppayapplyRequestV1.MybankPayCpayCppayapplyRequestV1Biz;
import com.icbc.api.response.MybankPayCpayCppayapplyResponseV1;
import com.ourteam.dzpt.controller.BankController;
import com.ourteam.dzpt.util.StaticUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BankService {

  public MybankPayCpayCppayapplyResponseV1 payByBank(Map<String, Object> info) {
    return test(info);
  }

  private static Logger logger = LoggerFactory.getLogger(BankService.class);

  protected static final String APIGW_PUBILC_KEY = StaticUtil.APIGW_PUBILC_KEY;
  protected static final String APP_ID = StaticUtil.APP_ID;

  protected static final String MY_PRIVATE_KEY = StaticUtil.MY_PRIVATE_KEY;

  public static MybankPayCpayCppayapplyResponseV1 test(Map info) {
    DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBILC_KEY);

    MybankPayCpayCppayapplyRequestV1 request = new MybankPayCpayCppayapplyRequestV1();

    request.setServiceUrl(StaticUtil.ServiceUrl);

    MybankPayCpayCppayapplyRequestV1.MybankPayCpayCppayapplyRequestV1Biz bizContent = new MybankPayCpayCppayapplyRequestV1Biz();
    MybankPayCpayCppayapplyRequestV1.BeanGoodsInfo beanGoodsInfo = new BeanGoodsInfo();
    MybankPayCpayCppayapplyRequestV1.BeanRecvMallInfo beanRecvMallInfo = new BeanRecvMallInfo();

    List<BeanGoodsInfo> beanGoodsInfoList = new ArrayList<BeanGoodsInfo>();
    List<MybankPayCpayCppayapplyRequestV1.BeanRecvMallInfo> beanRecvMallInfoList = new ArrayList<MybankPayCpayCppayapplyRequestV1.BeanRecvMallInfo>();

    //商品信息
    beanGoodsInfo.setGoodsSubId((String) info.get("orderId"));
    //选填
    beanGoodsInfo.setGoodsName((String) info.get("goodsName"));
    //选填
    beanGoodsInfo.setPayeeCompanyName((String) info.get("sellerName"));
    //选填
    beanGoodsInfo.setGoodsNumber(String.valueOf(info.get("amount")));
    //选填
    beanGoodsInfo.setGoodsAmt(
        String.valueOf((Integer) info.get("price") * 100 * (Integer) info.get("amount")));
    //选填
    beanGoodsInfo.setGoodsUnit((String) info.get("unit"));
    //选填
    beanGoodsInfoList.add(beanGoodsInfo);

    beanRecvMallInfo.setMallCode(StaticUtil.MallCode);
    beanRecvMallInfo.setMallName(StaticUtil.MallName);
    beanRecvMallInfo.setPayeeCompanyName(StaticUtil.PayeeCompanyName);
    beanRecvMallInfo.setPayeeSysflag(StaticUtil.PayeeSysflag);

    beanRecvMallInfo.setMccCode(StaticUtil.MccCode);
    beanRecvMallInfo.setMccName(StaticUtil.MccName);
    beanRecvMallInfo.setBusinessLicense(StaticUtil.BusinessLicense);
    beanRecvMallInfo.setBusinessLicenseType(StaticUtil.BusinessLicenseType);

    beanRecvMallInfo.setPayeeBankCode(StaticUtil.PayeeBankCode);
    beanRecvMallInfo.setPayeeAccno(StaticUtil.PayeeAccno);
    //必填
    Integer amount = (Integer) info.get("amount") * (Integer) info.get("price") * 100;
    beanRecvMallInfo.setPayAmount(String.valueOf(amount));
    beanRecvMallInfoList.add(beanRecvMallInfo);

    bizContent.setAgreeCode(StaticUtil.AgreeCode);
    //必填
    bizContent.setPartnerSeq(String.valueOf(System.currentTimeMillis()));
    //必填 手机电脑
    bizContent.setPayChannel((String) info.get("payChannel"));
    bizContent.setPayMode(StaticUtil.PayMode);
    bizContent.setPayEntitys(StaticUtil.PayEntitys);
    bizContent.setInternationalFlag(StaticUtil.InternationalFlag);
    bizContent.setAsynFlag(StaticUtil.AsynFlag);
    bizContent.setPayMemno(StaticUtil.PayMemno);
    //必填

    bizContent.setOrderCode("202401013" + String.format("%04d", (Integer) info.get("orderId")));
    //必填
    bizContent.setOrderAmount(String.valueOf(amount));
    bizContent.setOrderCurr(StaticUtil.OrderCurr);
    //必填
    bizContent.setSumPayamt(String.valueOf(amount));
    bizContent.setOrderRemark(StaticUtil.OrderRemark);
    bizContent.setRceiptRemark(StaticUtil.RceiptRemark);

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
    bizContent.setSubmitTime(simpleDateFormat.format(new Date()));

    bizContent.setReturnUrl("http://127.0.0.1:8081");
    bizContent.setCallbackUrl("http://120.77.205.253:8080/bank/test2");
    //bizContent.setSubmitTime("");

    bizContent.setPayeeList(beanRecvMallInfoList);
    bizContent.setGoodsList(beanGoodsInfoList);

    request.setBizContent(bizContent);

    Random rand = new Random();
    String msgId = rand.nextInt(99999) + "msg";
    System.out.println(msgId);

    MybankPayCpayCppayapplyResponseV1 response;
    try {
      response = client.execute(request, msgId);
      System.out.println(JSONObject.toJSONString(response));
      logger.info("调用工行接口请求内容:" + JSONObject.toJSONString(request));
      logger.info("调用工行接口相应内容:" + JSONObject.toJSONString(response));
      return response;
    } catch (IcbcApiException e) {
      e.printStackTrace();
      return null;
    }
  }
}