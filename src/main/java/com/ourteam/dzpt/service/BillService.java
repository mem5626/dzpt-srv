package com.ourteam.dzpt.service;


import com.ourteam.dzpt.entity.Bill;
import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.mapper.BillMapper;
import com.ourteam.dzpt.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BillService {
    @Autowired
    private BillMapper billMapper;

    public List<Bill> selectBillByUid(int id){
        try{
            return billMapper.selectBillByUid(id);
        }catch (Exception e) {
            System.out.print(e.getMessage());
            throw new GlobalException(ExceptionMsg.Error);
        }
    }

    public int addBill(Bill bill){
        bill.setCreateDate(new Date());
        return billMapper.createBill(bill);
    }

}
