package com.itwillbs.menu.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class menuDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;

	public Connection getConnection() throws Exception {
		Context init = new InitialContext();

		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/Mysql");
		con = ds.getConnection();
		return con;
	}

	public void close() {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException ex) {
			}
		if (pstmt2 != null)
			try {
				pstmt2.close();
			} catch (SQLException ex) {
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException ex) {
			}
		if (con != null)
			try {
				con.close();
			} catch (SQLException ex) {
			}
	}

	public void insertMenu(menuDTO dto) {

		try {
			con = getConnection();
			String sql2 = "select max(menu_num) as maxnum from menu";
			pstmt2 = con.prepareStatement(sql2);

			rs = pstmt2.executeQuery();

			int num = 0;
			if (rs.next()) {
				num = rs.getInt("maxnum") + 1;
			}

			String sql = "insert into menu(menu_num, menu_name, menu_price, menu_category, menu_img, menu_detail) values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.setString(2, dto.getMenu_name());
			pstmt.setInt(3, dto.getMenu_price());
			pstmt.setString(4, dto.getMenu_category());
			pstmt.setString(5, dto.getMenu_img());
			pstmt.setString(6, dto.getMenu_detail());

			pstmt.executeUpdate();
			System.out.println("메뉴 등록 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
	}

	public List<menuDTO> getMenuList(int startRow, int pageSize) {
		List<menuDTO> menuList = new ArrayList<menuDTO>();
		System.out.println("@@@@@@@@@@@@");
		
		try {
			// 1,2단계 디비연결
			con = getConnection();
			// 3 sql
			String sql = "select * from menu order by menu_num desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			// 4 실행=> 결과저장
			rs = pstmt.executeQuery();
			// 5 while 결과 접근
			// => menuDTO 객체생성 set호출 디비에서 가져온 값저장
			// => 글하나를 배열한칸에 저장
			while (rs.next()) {
				menuDTO dto = new menuDTO();
				dto.setMenu_num(rs.getInt("menu_num"));
				dto.setMenu_name(rs.getString("menu_name"));
				dto.setMenu_price(rs.getInt("menu_price"));
				dto.setMenu_category(rs.getString("menu_category"));
				dto.setMenu_detail(rs.getString("menu_detail"));
				dto.setMenu_img(rs.getString("menu_img"));
				// 배열 한칸에 글한개 저장
				menuList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		// 배열의 주소 리턴
		return menuList;
	}// getList()

	public menuDTO getMenu(int menu_num) {
		menuDTO dto = null;

		try {
			con = getConnection();

			String sql = "select * from menu where menu_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, menu_num);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				dto = new menuDTO();

				dto.setMenu_num(rs.getInt("menu_num"));
				dto.setMenu_name(rs.getString("menu_num"));
				dto.setMenu_detail(rs.getString("menu_detail"));
				dto.setMenu_price(rs.getInt("menu_price"));
				dto.setMenu_img(rs.getString("menu_img"));
				dto.setMenu_category(rs.getString("menu_category"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return dto;
	}// getMenu

	public void updateMenu(menuDTO dto) {
		try {
			// 1,2 디비연결
			con = getConnection();
			// 3 sql
			String sql = "update menu set menu_name=? ,menu_price=?, menu_category=?, menu_img=, menu_detail=? where menu_num=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getMenu_name());
			pstmt.setInt(2, dto.getMenu_price());
			pstmt.setString(3, dto.getMenu_category());
			pstmt.setString(4, dto.getMenu_img());
			pstmt.setString(5, dto.getMenu_detail());
			pstmt.setInt(6, dto.getMenu_num());
			// 4 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}// updatemenu

	public void deleteMenu(int menu_num) {
		try {
			// 1,2 디비연결
			con = getConnection();
			// 3 sql
			String sql = "delete from menu where menu_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, menu_num);
			// 4 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}//

	public int getMenuCount() {
		int count = 0;
		try {
			con = getConnection();

			String sql = "select count(*) from menu";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("count(*)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return count;
	}//

	public List<menuDTO> DrinkList(String menu_category) {
		List<menuDTO> drinkList = new ArrayList<menuDTO>();
		System.out.println("@ DrinkList@");
		
		try {
			// 1,2단계 디비연결
			con = getConnection();
			// 3 sql
			String sql = "select * from menu where menu_category=? ";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, menu_category);
		
			

			// 4 실행=> 결과저장
			rs = pstmt.executeQuery();
			// 5 while 결과 접근
			// => menuDTO 객체생성 set호출 디비에서 가져온 값저장
			// => 글하나를 배열한칸에 저장
			while (rs.next()) {
				menuDTO dto = new menuDTO();
				dto.setMenu_num(rs.getInt("menu_num"));
				dto.setMenu_name(rs.getString("menu_name"));
				dto.setMenu_price(rs.getInt("menu_price"));
				dto.setMenu_category(rs.getString("menu_category"));
				dto.setMenu_detail(rs.getString("menu_detail"));
				dto.setMenu_img(rs.getString("menu_img"));
				// 배열 한칸에 글한개 저장
				drinkList.add(dto);
			}
			System.out.println("DAO : 상품목록 모두 저장완료! "+drinkList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		// 배열의 주소 리턴
		return drinkList;
	}// getmenuList()
	
	public List<menuDTO> FoodList(String menu_category) {
		List<menuDTO> foodList = new ArrayList<menuDTO>();
		System.out.println("@@@@@@FoodList@@@@@@");
		
		try {
			// 1,2단계 디비연결
			con = getConnection();
			// 3 sql
			String sql = "select * from menu where menu_category = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, menu_category);
	
			// 4 실행=> 결과저장
			rs = pstmt.executeQuery();
			// 5 while 결과 접근
			// => menuDTO 객체생성 set호출 디비에서 가져온 값저장
			// => 글하나를 배열한칸에 저장
			while (rs.next()) {
				menuDTO dto = new menuDTO();
				dto.setMenu_num(rs.getInt("menu_num"));
				dto.setMenu_name(rs.getString("menu_name"));
				dto.setMenu_price(rs.getInt("menu_price"));
				dto.setMenu_category(rs.getString("menu_category"));
				dto.setMenu_detail(rs.getString("menu_detail"));
				dto.setMenu_img(rs.getString("menu_img"));
				// 배열 한칸에 글한개 저장
				foodList.add(dto);
			}
			System.out.println("DAO : 상품목록 모두 저장완료! "+foodList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		// 배열의 주소 리턴
		return foodList;
	}// getmenuList()
	
	public List<menuDTO> GoodsList(String menu_category) {
		List<menuDTO> goodsList = new ArrayList<menuDTO>();
		System.out.println("@GoodsList@");
		
		try {
			// 1,2단계 디비연결
			con = getConnection();
			// 3 sql
			String sql = "select * from menu where menu_category = ? ";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, menu_category);
	
			// 4 실행=> 결과저장
			rs = pstmt.executeQuery();
			// 5 while 결과 접근
			// => menuDTO 객체생성 set호출 디비에서 가져온 값저장
			// => 글하나를 배열한칸에 저장
			while (rs.next()) {
				menuDTO dto = new menuDTO();
				dto.setMenu_num(rs.getInt("menu_num"));
				dto.setMenu_name(rs.getString("menu_name"));
				dto.setMenu_price(rs.getInt("menu_price"));
				dto.setMenu_category(rs.getString("menu_category"));
				dto.setMenu_detail(rs.getString("menu_detail"));
				dto.setMenu_img(rs.getString("menu_img"));
				// 배열 한칸에 글한개 저장
				goodsList.add(dto);
			}
			System.out.println("DAO : 상품목록 모두 저장완료! " + goodsList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		// 배열의 주소 리턴
		return goodsList;
	}// getmenuList()
	
}	
	
	
