package com.ourteam.dzpt.controller;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankPayCpayCppayapplyRequestV1;
import com.icbc.api.response.MybankCreditcardOnlinecardCardapplylistReponseV1;

public class BankController {

    protected static final String APIGW_PUBILC_KEY="";

    protected static final String APP_ID="";

    protected static final String MY_PRIVATE_KEY="";

    public static void main(String[] args){
      DefaultIcbcClient client=new DefaultIcbcClient(APP_ID,MY_PRIVATE_KEY,APIGW_PUBILC_KEY);

    }
}
