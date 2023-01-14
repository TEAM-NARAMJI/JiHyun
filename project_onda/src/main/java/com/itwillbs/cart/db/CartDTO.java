package com.itwillbs.cart.db;

public class CartDTO {
	private int crt_num;	// 카트번호(인덱스)
	private String cus_id;	// 고객아이디
	private int menu_num;	// 상품번호
	private int crt_price;	// 카트에담긴 가격 (상품가격 * 수량)
	private int crt_count;	// 카트에담기 수량 
	
	public int getCrt_num() {
		return crt_num;
	}
	public void setCrt_num(int crt_num) {
		this.crt_num = crt_num;
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
	public int getCrt_count() {
		return crt_count;
	}
	public void setCrt_count(int crt_count) {
		this.crt_count = crt_count;
	}
	public int getCrt_price() {
		return crt_price;
	}
	public void setCrt_price(int crt_price) {
		this.crt_price = crt_price;
	}
	@Override
	public String toString() {
		return "CartDTO [crt_num=" + crt_num + ", cus_id=" + cus_id + ", menu_num=" + menu_num + ", crt_price="
				+ crt_price + ", crt_count=" + crt_count + "]";
	}
 	
 	
	
}
