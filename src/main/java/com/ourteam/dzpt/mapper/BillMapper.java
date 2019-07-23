package com.ourteam.dzpt.mapper;

import com.ourteam.dzpt.entity.Bill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BillMapper {
    List<Bill> selectBillByUid(int uid);
    int createBill(Bill bill);
    int deleteById(Integer id);
    int updateBill(Bill bill);
}
