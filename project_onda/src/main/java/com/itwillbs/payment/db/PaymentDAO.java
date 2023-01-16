package com.itwillbs.payment.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.itwillbs.cart.db.CartDAO;
import com.itwillbs.cart.db.CartDTO;


public class PaymentDAO {
	Connection con = null;
	PreparedStatement isAddedPstmt = null;
	PreparedStatement pstmt2 = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// getConnection() : DB 연결 메서드
	public Connection getConnection() throws Exception {
		Context init = new InitialContext();
		// 자원의 이름(name) 호출("자원의 위치/자원의 이름")
		// import javax.sql.DataSource;
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/Mysql");
		// con에 저장 (DataSource -> Connection)
		con = ds.getConnection();

		return con; // 연결정보를 리턴
	}

	// close() : 마무리 작업 메서드
	public void close() {
		if (isAddedPstmt != null)
			try {
				isAddedPstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		if (pstmt2 != null)
			try {
				pstmt2.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		if (con != null)
			try {
				con.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
	}
	
	public void insertPay(PaymentDTO dto) {
		try {
			con = getConnection();
			
			String sql2="select max(pay_num) as max \r\n"
					+ "from payment\r\n"
					+ "where cus_id = ? \r\n"
					+ "and pay_date = ?;";
			pstmt2 =con.prepareStatement(sql2);
			pstmt2.setString(1, dto.getCus_id());
			pstmt2.setTimestamp(2, dto.getPay_date());
			rs=pstmt2.executeQuery();
			
			int pay_num = 0;
			// 같은 아이디로 같은 시간에 주문한 내역이 없으면 max + 1
			// 주문내역 번호(pay_num)는 메뉴는 달라도 같은 주문 내역일 경우
			// 같은 번호로 관리되는 것이 편할 것 같다 생각해 이렇게 짰음
			// 일단 pay_num pk도 없앰,,
			// 이렇게 구현하는 것이 아니고, 별로라면 원래대로 고치겠습ㄴㅣ다.. 
			if(!rs.next()) {
				pay_num = rs.getInt("max") + 1;
			}
			
			String sql = "insert into payment values(?,?,?,?,?,?,?)";
			pstmt =con.prepareStatement(sql);
			
			pstmt.setInt(1, pay_num);
			pstmt.setString(2, dto.getCus_id());
			pstmt.setInt(3, dto.getMenu_num());
			pstmt.setInt(4, dto.getPay_price());
			pstmt.setInt(5, dto.getPay_count());
			pstmt.setTimestamp(6, dto.getPay_date());
			pstmt.setString(7, dto.getPay_pick());
			
			pstmt.executeUpdate();
			
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close();
			}

	} 
	
	
	
	
	public ArrayList<CartDTO> getOrderList(String[] chk, String cus_id) {
		ArrayList<CartDTO> orderList = new ArrayList<CartDTO>();
//		ArrayList<Map<String, String>> cartList2 = new ArrayList<Map<String, String>>();
		System.out.println(cus_id);
		try {
			con = getConnection();
			
			String sql = "select * from cart where cus_id=? and in (" + String.join(", ", chk) + ") order by crt_num";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cus_id);

			rs=pstmt.executeQuery();

			while(rs.next()) {
				CartDTO dto = new CartDTO();
				dto.setCrt_num(rs.getInt("crt_num"));
				dto.setCus_id(rs.getString("cus_id"));
				dto.setMenu_num(rs.getInt("menu_num"));
				dto.setCrt_count(rs.getInt("crt_count"));
				dto.setCrt_price(rs.getInt("crt_price"));

				orderList.add(dto);
				System.out.println("장바구니 불러오기" + dto);
				
//				Map<String, String> dto2 = new HashMap<>();
//				dto2.put("crt_num", rs.getInt("crt_num") + "");
				
//				cartList2.add(dto2);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return orderList;

	} // getMyCart() 메서드 끝
	
	
	

}
