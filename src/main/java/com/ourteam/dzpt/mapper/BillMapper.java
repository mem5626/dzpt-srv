package com.ourteam.dzpt.mapper;

import com.ourteam.dzpt.entity.Bill;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BillMapper {

  List<Bill> selectBillByUid(int uid);

  int createBill(Bill bill);

  int deleteById(Integer id);

  int updateBill(Bill bill);
}
