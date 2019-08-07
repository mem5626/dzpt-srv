package com.ourteam.dzpt.mapper;

import com.github.pagehelper.Page;
import com.ourteam.dzpt.entity.Bill;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BillMapper {

  List<Bill> selectBillByUid(int uid);

  int createBill(Bill bill);

  int deleteById(Integer id);

  int updateBill(Bill bill);

//  List<Bill> list(Map<String, Object> map);
//  /**
//   * 插件 分页 查询表中部分字段
//   */
//  Page<Bill> findByPage();
//  /**
//   * 插件 分页  查询表中所有字段
//   */
//  Page<Bill> findWithBLOBsByPage();
}
