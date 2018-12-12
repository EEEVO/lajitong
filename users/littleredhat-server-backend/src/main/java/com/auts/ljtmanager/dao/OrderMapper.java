package com.auts.ljtmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.auts.ljtmanager.model.dao.order.OrderModel;

public interface OrderMapper {

    @Select("<script>"
    	+ "SELECT * FROM tbl_order a"
    	+ "WHERE 1=1"
    	+ "<if test='orderType != \"\"'>"
		+ 	" and #{orderType} = a.order_type "
		+ "</if>"
    	+ "<if test='startDate != \"\"'>"
		+ 	" and #{startDate} &lt;= a.create_time "
		+ "</if>"
		+ "<if test='endDate != \"\"'>"
		+ 	" and #{endDate} &gt;= a.create_time "
		+ "</if>"
		+ " order by create_time desc "
    	+ "</script>")
    @Results({
    	@Result(property = "orderId", column = "order_id"),
    	@Result(property = "deviceId", column = "device_id"),
    	@Result(property = "binNo", column = "bin_no"),
    	@Result(property = "orderType", column = "order_type"),
    	@Result(property = "deliveryTime", column = "delivery_time"),
    	@Result(property = "weight", column = "weight"),
    	@Result(property = "price", column = "price"),
    	@Result(property = "amount", column = "amount"),
    	@Result(property = "createtime", column = "create_time"),
    	@Result(property = "updatetime", column = "update_time")
    })
	List<OrderModel> queryOrders(@Param("orderType") String orderType, @Param("startDate") String startDate, @Param("endDate") String endDate);

    @Select("<script>"
        	+ "SELECT count(*) FROM tbl_order a "
        	+ "WHERE a.order_type = #{orderType} "
        	+ "<if test='startDate != \"\"'>"
    		+ 	" and #{startDate} &lt;= a.order_date "
    		+ "</if>"
    		+ "<if test='endDate != \"\"'>"
    		+ 	" and #{endDate} &gt;= a.order_date "
    		+ "</if>"
        	+ "</script>")
	int queryOrdersCnt(@Param("status") String status, @Param("startDate") String startDate, @Param("endDate") String endDate);

    @Update("update tbl_order set status = '02', update_time= NOW() where uid=#{uid}")
	void orderSettle(@Param("uid") String uid);

    @Update("update tbl_order set status = '99', update_time= NOW() where uid=#{uid}")
	void orderFailure(@Param("uid") String uid);

    @Update("update tbl_order set contract_status = '1', update_time= NOW() where uid=#{uid}")
	void orderContract(@Param("uid") String uid);

    @Update("update tbl_order set status = '03', update_time= NOW() where uid=#{uid}")
	void orderSettled(@Param("uid") String uid);

    @Update("update tbl_order set pay_status = '1', update_time= NOW() where uid=#{uid}")
	void orderPay(@Param("uid") String uid);

    @Select("select * from tbl_order where uid = #{uid}")
	OrderModel queryOrder(@Param("uid") String uid);

}