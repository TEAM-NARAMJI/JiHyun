package com.itwillbs.payment.db;

import java.sql.Timestamp;
import java.util.List;


public class PaymentDTO {
	private int pay_num;
	private String cus_id;
	private int menu_num;
	private int pay_price;
	private int pay_count;
	private Timestamp pay_date;
	private String pay_pick;
	
	public int getPay_num() {
		return pay_num;
	}
	public void setPay_num(int pay_num) {
		this.pay_num = pay_num;
	}
	public String getCus_id() {
		return cus_id;
	}
	public void setCus_id(String cus_id) {
		this.cus_id = cus_id;
	}
	public int getMenu_num() {
		return menu_num;
	}
	public void setMenu_num(int menu_num) {
		this.menu_num = menu_num;
	}
	public int getPay_price() {
		return pay_price;
	}
	public void setPay_price(int pay_price) {
		this.pay_price = pay_price;
	}
	public int getPay_count() {
		return pay_count;
	}
	public void setPay_count(int pay_count) {
		this.pay_count = pay_count;
	}
	public Timestamp getPay_date() {
		return pay_date;
	}
	public void setPay_date(Timestamp pay_date) {
		this.pay_date = pay_date;
	}
	public String getPay_pick() {
		return pay_pick;
	}
	public void setPay_pick(String pay_pick) {
		this.pay_pick = pay_pick;
	}
	@Override
	public String toString() {
		return "PaymentDTO [pay_num=" + pay_num + ", cus_id=" + cus_id + ", menu_num=" + menu_num + ", pay_price="
				+ pay_price + ", pay_count=" + pay_count + ", pay_date=" + pay_date + ", pay_pick=" + pay_pick + "]";
	}
	
	
	
	
	// List<TestCartDTO> testCartList
//	public List<PaymentDTO> ListDtoToListEntity(){
//       List<PaymentDTO> paymentList = null;
//    		   
//       for(int i=0; i<testCartList.size(); i++) {
//    	  return testCartList.stream().map(TestCartDTO::toEntity(testCartList)).collect(Collectors.toList());
      
//       }
//       return paymentList;
//    }

}
