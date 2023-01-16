package com.itwillbs.cart.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CartDAO {
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

	public void insertCart(CartDTO dto) {
		// TODO
		// ArrayList객체를 회원아이디 당 최초 한번만 생성
//			if문으로 판단
		// 그 이후는 ArrayList객체가 있으면 객체 생성 안하고 add()메서드로 추가하기
		// ArrayList객체 없으면(= session에 carList라는 이름의 객체가 없으면) => 생성하기
//		ArrayList<String> cartList = (ArrayList<String>) session.getAttribute("cartList");
//
//		if(cartList == null) {
//			cartList =  new ArrayList<String>();
//			session.setAttribute("cartList", cartList);
//			
//		}
//
//			cartList.add(menu_name);

		try {
			con = getConnection();

			// cart에 해당 상품번호가 추가된적 있는지 확인
			String isAddedSql = "select count(*) as count, \r\n"
					+ "	   				case when crt_num is null then 0\r\n"
					+ "						 else crt_num end as crt_num\r\n"
					+ "										from cart\r\n"
					+ "									   where cus_id = ?\r\n"
					+ "										 and menu_num = ?";
			isAddedPstmt = con.prepareStatement(isAddedSql);
			isAddedPstmt.setString(1, dto.getCus_id());
			isAddedPstmt.setInt(2, dto.getMenu_num());

			rs = isAddedPstmt.executeQuery();
			boolean isAdded = false;
			int crt_num = 0;
			if(rs.next()) {
				isAdded = rs.getInt("count") == 0 ? false : true;
				crt_num = rs.getInt("crt_num");
			}

			if(isAdded) {
				// update
				String sql = "update cart \r\n"
						+ "   set crt_count= ?\r\n"
						+ "     , crt_price= (select A.crt_price \r\n"
						+ "					  from (select menu_price * ? as crt_price\r\n"
						+ "							from menu\r\n"
						+ "							where menu_num = ?\r\n"
						+ "							)A)\r\n"
						+ "   where crt_num= ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, dto.getCrt_count());
				pstmt.setInt(2, dto.getCrt_count());
				pstmt.setInt(3, dto.getMenu_num());
				pstmt.setInt(4, crt_num);
				pstmt.executeUpdate();
				
				

		} else {
				// TODO cus_id=> 내 아이디일때만 insert 되도록 제어하기
				
				// insert
				
				// crt_num 카트번호 구하기
				String sql2 = "select max(crt_num) from cart";
				pstmt2 = con.prepareStatement(sql2);

				rs=pstmt2.executeQuery();

				int max = 0;
				if(rs.next()) {
					max=rs.getInt("max(crt_num)") + 1;
				
				}
				
				
				String sql = "insert into cart(crt_num\r\n"
						+ "			   , cus_id\r\n"
						+ "               , menu_num\r\n"
						+ "               , crt_count\r\n"
						+ "               , crt_price)\r\n"
						+ "  values(?, ?, ?, ?, \r\n"
						+ "				(select A.crt_price from\r\n"
						+ "								 (select menu_price * ? as crt_price\r\n"
						+ "				 				 	from menu\r\n"
						+ "								   where menu_num=?)A))";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, max);
				pstmt.setString(2, dto.getCus_id());
				pstmt.setInt(3, dto.getMenu_num());
				pstmt.setInt(4, dto.getCrt_count());
				pstmt.setInt(5, dto.getCrt_count());
				pstmt.setInt(6, dto.getMenu_num());
				
				
				pstmt.executeUpdate();
			}


			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close();
			}

		return;
	}
	
	public void updateCart(CartDTO dto) {
		System.out.println("호출확인");
		
		
		// TODO 
		// userCheck: 자기id의 상품을 삭제해야하니까! => 확인 
		
		try {
			con = getConnection();
			
			String sql = "update cart \r\n"
					+ "   set crt_count= ?\r\n"
					+ "     , crt_price= (select A.crt_price \r\n"
					+ "						from (select menu_price * ? as crt_price\r\n"
					+ "							    from menu\r\n"
					+ "							   where menu_num = ?\r\n"
					+ "						 	 ) A)\r\n"
					+ "  where crt_num= ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getCrt_count());
			pstmt.setInt(2, dto.getCrt_count());
			pstmt.setInt(3, dto.getMenu_num());
			pstmt.setInt(4, dto.getCrt_num());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 예외 상관없이 마무리 작업 
			// => Connection, PreparedStatement
			// => 기억장소 해제
			close();
		
		} 
		
	} // updateCart() 메서드 끝
	
	public int getCartPrice(CartDTO dto) {
		int result = 0;
		try {
			con = getConnection();
			
			String selectSql = "select crt_price\r\n"
						+ "       from cart\r\n"
						+ "   	 where crt_num = ?";
			
			pstmt = con.prepareStatement(selectSql);
			pstmt.setInt(1, dto.getCrt_num());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("crt_price");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		} 
		
		return result;
	}
	
	
	// TODO 파라미터 cus_id 받아와야하는지 생각해보기
	public void deleteCart(int crt_num) {
		
		try {
			//1,2 디비연결
			con = getConnection();
			//3 sql
			String sql="delete from cart where crt_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, crt_num);
			//4 실행
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			close();
		}
		
	}
	
	public CartDTO getCart(int crt_num) {
		
		CartDTO dto = null;
		
		try {
			con=getConnection();
			
			String sql = "select * from cart where crt_num = ?";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, crt_num);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				
				dto = new CartDTO();
				dto.setCrt_num(rs.getInt("crt_num"));
				dto.setCus_id(rs.getString("cus_id"));
				dto.setMenu_num(rs.getInt("menu_num"));
				dto.setCrt_price(rs.getInt("crt_price"));
				dto.setCrt_count(rs.getInt("crt_count"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return dto;
	}
	

	public ArrayList<CartDTO> getCartList(String cus_id) {
		ArrayList<CartDTO> cartList = new ArrayList<CartDTO>();
//		ArrayList<Map<String, String>> cartList2 = new ArrayList<Map<String, String>>();
		System.out.println(cus_id);
		try {
			con = getConnection();

			String sql = "select * from cart where cus_id=? order by crt_num";
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

				cartList.add(dto);
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
		return cartList;

	} // getMyCart() 메서드 끝


	public String getMenuName(int menu_num, int crt_num) {
		String MenuName = "";
		
		try {
			con = getConnection();

			String sql = "select menu_name from menu where menu_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, menu_num);

			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				MenuName = rs.getString("menu_name");
			}
			
			System.out.println(MenuName);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return MenuName;
	}
	
	public String getMenuImg(int menu_num, int crt_num) {
		String MenuImg = "";
		
		try {
			con = getConnection();
			
			String sql = "select menu_img from menu where menu_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, menu_num);

			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				MenuImg = rs.getString("menu_img");
			}
			
			System.out.println(MenuImg);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return MenuImg;
		
	}
	
	public int getTotalPrice(String cus_id) {
		int totalPrice = 0;
		
		try {
			con = getConnection();
			
			String sql = "select sum(crt_price) as totalPrice from cart where cus_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cus_id);
			// TODO 오류!
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				totalPrice = rs.getInt("totalPrice");
				
			}
			
			System.out.println("totalPrice: " + totalPrice);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return totalPrice;
		
	} // getTotalPrice() 메서드 끝
	

}







