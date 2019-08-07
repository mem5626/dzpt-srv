package com.ourteam.dzpt.controller;

import com.icbc.api.IcbcApiException;
import com.icbc.api.response.MybankPayCpayCppayapplyResponseV1;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankPayCpayCpordercloseRequestV1.MybankPayCpayCpordercloseV1RequestV1Biz;
import com.icbc.api.request.MybankPayCpayCppayapplyRequestV1;
import com.icbc.api.request.MybankPayCpayCppayapplyRequestV1.BeanGoodsInfo;
import com.icbc.api.request.MybankPayCpayCppayapplyRequestV1.BeanRecvMallInfo;
import com.icbc.api.request.MybankPayCpayCppayapplyRequestV1.MybankPayCpayCppayapplyRequestV1Biz;
import com.icbc.api.response.MybankCreditcardOnlinecardCardapplylistReponseV1;

public class BankController {

    protected static final String APIGW_PUBILC_KEY="";

    protected static final String APP_ID="";

    protected static final String MY_PRIVATE_KEY="";

    public static void main(String[] args){
      DefaultIcbcClient client=new DefaultIcbcClient(APP_ID,MY_PRIVATE_KEY,APIGW_PUBILC_KEY);

      MybankPayCpayCppayapplyRequestV1 request=new MybankPayCpayCppayapplyRequestV1();

      request.setServiceUrl("");


MybankPayCpayCppayapplyRequestV1.MybankPayCpayCppayapplyRequestV1Biz bizContent=new MybankPayCpayCppayapplyRequestV1Biz();
      MybankPayCpayCppayapplyRequestV1.BeanGoodsInfo beanGoodsInfo=new BeanGoodsInfo();
      MybankPayCpayCppayapplyRequestV1.BeanRecvMallInfo beanRecvMallInfo=new BeanRecvMallInfo();

      List<MybankPayCpayCppayapplyRequestV1.BeanGoodsInfo> beanGoodsInfoList = new ArrayList<MybankPayCpayCppayapplyRequestV1.BeanGoodsInfo>();
      List<MybankPayCpayCppayapplyRequestV1.BeanRecvMallInfo> beanRecvMallInfoList = new ArrayList<MybankPayCpayCppayapplyRequestV1.BeanRecvMallInfo>();

      beanGoodsInfo.setGoodsSubId("1");
      beanGoodsInfo.setGoodsName("1");
      beanGoodsInfo.setPayeeCompanyName("");
      beanGoodsInfo.setPayeeCompanyName("payeeCompanyName");

      beanGoodsInfo.setGoodsNumber("1");
      beanGoodsInfo.setGoodsAmt("100");
      beanGoodsInfo.setGoodsUnit("单位");

      beanGoodsInfoList.add(beanGoodsInfo);

      beanRecvMallInfo.setMallCode("0200EH0013036");
      beanRecvMallInfo.setMallName("商户名");
      beanRecvMallInfo.setPayeeCompanyName("3065脚本账户名称_普通存款人民币");
      beanRecvMallInfo.setPayeeSysflag("1");

      beanRecvMallInfo.setMccCode("1");
      beanRecvMallInfo.setMccName("1");
      beanRecvMallInfo.setBusinessLicense("1");
      beanRecvMallInfo.setBusinessLicenseType("e");

      beanRecvMallInfo.setPayeeBankCode("1");
      beanRecvMallInfo.setPayeeAccno("0200062009212098448");
      beanRecvMallInfo.setPayAmount("3332");
      beanRecvMallInfoList.add(beanRecvMallInfo);

      bizContent.setAgreeCode("0020000998140010137438000000097112");
      bizContent.setPartnerSeq("030240009696001327461018792");
      bizContent.setPayChannel("1");
      bizContent.setPayMode("1");
      bizContent.setPayEntitys("10000000000000000000");
      bizContent.setInternationalFlag("1");
      bizContent.setAsynFlag("0");
      bizContent.setPayMemno("0200EH0013036");
      bizContent.setOrderCode("2024010130002");
      bizContent.setOrderAmount("4332");
      bizContent.setOrderCurr("1");

        bizContent.setSumPayamt("3332");
        bizContent.setOrderRemark("订单备注");
        bizContent.setRceiptRemark("回单补充信息备注");
        bizContent.setSubmitTime("20240101220000");
        bizContent.setReturnUrl("");
        bizContent.setCallbackUrl("");
        bizContent.setSubmitTime("");

        bizContent.setPayeeList(beanRecvMallInfoList);
        bizContent.setGoodsList(beanGoodsInfoList);

        request.setBizContent(bizContent);

        Random rand = new Random();
        String msgId = rand.nextInt(99999)+"msg";
        System.out.println(msgId);

        MybankPayCpayCppayapplyResponseV1 response;
        try {
            response = client.execute(request,msgId);
            System.out.println(JSONObject.toJSONString(response));
            if (response.isSuccess()){
                System.out.println(response.getReturnCode());
            }else{
                System.out.println(response.getReturnCode());
                System.out.println(response.getReturnMsg());
            }
        }catch (IcbcApiException e){
            e.printStackTrace();
        }
    }
}
