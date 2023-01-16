package com.itwillbs.payment.db;

public class OrderPgaeItemDTO {
	private int crt_num;	// 카트테이블 인덱스
	private int crt_count;	// 장바구니에 담은 수량
	
	private String menu_name;	// 메뉴테이블에 저장된 메뉴 이름
    private int menu_price;		// 메뉴테이블에 저장된 메뉴1개당 가격
    
    private int totalPrice;		// 총 주문 금액
	
	
}
