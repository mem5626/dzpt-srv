package com.ourteam.dzpt.controller;

import com.icbc.api.IcbcApiException;
import com.icbc.api.response.MybankPayCpayCppayapplyResponseV1;
import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.Response;
import com.ourteam.dzpt.exception.GlobalException;
import java.util.ArrayList;
import java.util.HashMap;
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
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {


      @RequestMapping(value = "/bank/test1", method = RequestMethod.GET)
      public Response test1()
          throws GlobalException {
            test();
            return new Response(ExceptionMsg.Success);
      }

      @RequestMapping(value = "/bank/test2", method = RequestMethod.GET)
      public Response test2()
          throws GlobalException {
            return new Response(ExceptionMsg.Success);
      }


      protected static final String APIGW_PUBILC_KEY="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwFgHD4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsNsvI9ABGx30aTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    protected static final String APP_ID="10000000000004095453";

    protected static final String MY_PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCNAV/7LwrlDaiJnYBo3FkdrvRJss74slXyObSM3o8O+cfqxgmOeRtuIewlm6P0s5hwDt4Q7o6dPpdmiRiooySeQWzEU75Is+Uv6ZT3jT8IaF/zyAK1sk8CXtql+jrtLW+16rN8p6HLUgwzu3IPIBEbhMpYFHuOvBj6gtaEf4JxPalLKbZH7+v7Rj6PzAOPDekJUddU08mHWgyFzhRtbId0NWnu3gfzRIP72fPYs1Mvi9KoEVzQ3bGrm4hRdXswWkBQ4jhOcDORPP8gOhawqVddTvBj9uwErJd6roimHLCtoMzrK5EawL/xeHNTWBaw1lOY8cogMdxlX5Ry9fSNNfPRAgMBAAECggEAGucgQPSAx6MLfPYoAdnIxvkSct1C0AB2lXidEiOEd/8APbL7L8u7iA/A1owThCSf+QFdLS3W1/mROWb+5W37Fpa1AeBYddac679NRM4WMKx5YjHPEouqt3ERlolfpND+d18IT8hL/lbxLQ2efXRIz09Oq+98g4cv3NwJwc3yPViVz3EUNlRab5DyJ41f4S5KYK+uUengNXoK7EJbrKTqn22TE1hC9tG8NFG1vmX3vjK4sggzsZJTxo/riEU9SfFHDHhKL3FhXqDCwB6ws0SoeAds0z4Qt9gGpwFteRSoriGOMjOSg29+jCxVVtLETwi0Cc9PYnOp3Sgal4Jmir1SgQKBgQDI6CVQ4VIXSDE6pW7cdsKpVjs1JQUhx7HGlKlQD8iPJ3mFXeoeZ4dDmhjt1gSSbAO9HNWsGZjopCz5njb2zopI26oNlmDC0QabMRiZ/hjj21pDwmeK/wYI4A4NY2njw58tblzld4X/6iKbLLqydJJh1JCsfDG+8JZjd3D06IE4zQKBgQCzrBbUTBJEpma1x1Kuk6nYXtwE1yc4jNZKEkjNOxG4/SEP83wN7O7RNbzi9S0SPqY+ZLmGlg9x4DYCALMvEMRRQnVzcja7mgGfXvfeR1rQ7zRJ0AQ+A6uAX9NT5nE/1fp91YtB2eS4lUe6RuD4wP6ZbyQpj6Ddud0ttvRAEgl3FQKBgB+FHGWmFCUG9IJI9i2sjDnZB4z88+Mq219Wb4HaGBMtlEkyRMrkXzph8MtPqoP2YlfHMFbB5VWKfGvlQKZUHSn1HKbpEuE9vhWXDE8MqIcHIRBEbc5G2IGH9WxwTmqnhE+yYuy1ZdaSuz8xM1FQLK8glFobWgqk4FNWp+gkmjF5AoGAFXSNXVigLdZ5GWft9a7ob1AUKe/C3TvkMwAv9WICYcbIdfErdTY/5ne2UJvaf/0/OOAqT2oH+h8+E8slmo80AkgfYmPXKVvgqblbfA2GtsB5y/6tIwKpLjFDORehmm/g87nWYUy1xVz+Lb5dCxdwz7234cam/RM+2ECqtiPxQWkCgYEAkCGoKcAaIfCgNKhBNK331Evwts8wekv8oCwaYAVO64OyeHeF0qGrhBsyX8YKPV9ymG1raGWblP8+u1tsfaiU5mgeVlcf1iK2T0ftDJSsB9knUJ4F2hZnbZa1w7b3iBYwoauHbwTqmpV3qHP2U/j49OisclI1MTiwKTxnLgEwo0E=";





    public static void test(){
      DefaultIcbcClient client=new DefaultIcbcClient(APP_ID,MY_PRIVATE_KEY,APIGW_PUBILC_KEY);

      MybankPayCpayCppayapplyRequestV1 request=new MybankPayCpayCppayapplyRequestV1();

      request.setServiceUrl("http://gw.dccnet.com.cn:8083/api/mybank/pay/cpay/cppayapply/V2");


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
        bizContent.setReturnUrl("http://10.2.2.50:8081");
        bizContent.setCallbackUrl("http://10.2.2.50:8080/bank/test2");
        //bizContent.setSubmitTime("");

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
