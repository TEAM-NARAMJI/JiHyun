package com.itwillbs.notice.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class NoticeDAO {
	Connection con=null;
	PreparedStatement pstmt2 =null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	public Connection getConnection() throws Exception {
		
		Context init = new InitialContext();
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/Mysql");
		con=ds.getConnection();
		return con;
	}
	public void close() {
		
		if(rs!=null) try{rs.close();}catch(SQLException ex) {}
		if(pstmt2!=null) try{pstmt2.close();}catch(SQLException ex) {}
		if(pstmt!=null) try{pstmt.close();}catch(SQLException ex) {}
		if(con!=null) try{con.close();}catch(SQLException ex) {}
	}
		
	public void insertBoard(NoticeDTO dto) {
		
//		System.out.println("BoardDAO insertBoard()");
//		System.out.println("BoardDTO 주소값 :"+dto);
//		System.out.println("BoardDTO name :"+dto.getNt_name());
//		System.out.println("BoardDTO subject :"+dto.getNt_title());
//		System.out.println("BoardDTO content :"+dto.getNt_content());
//		System.out.println("BoardDTO readcount :"+dto.getNt_view());
//		System.out.println("BoardDTO date :"+dto.getNt_view());
		try {
			con=getConnection();
			String sql2="select max(nt_num) as maxnum from notice_board";
			pstmt2 = con.prepareStatement(sql2);
			rs=pstmt2.executeQuery();
			
			int num=0;
			if(rs.next()) {
				num=rs.getInt("maxnum")+1;
			}
			
			String sql="insert into notice_board(nt_num,nt_name,nt_title,nt_content,nt_view,nt_date) values(?,?,?,?,?,?)";
			pstmt =con.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.setString(2, dto.getNt_name());
			pstmt.setString(3, dto.getNt_title());
			pstmt.setString(4, dto.getNt_content());
			pstmt.setInt(5, dto.getNt_view());
			pstmt.setTimestamp(6, dto.getNt_date());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}//insertBoard()
	
	// List<BoardDTO> 리턴할형 getBoardList()메서드 정의
	public List<NoticeDTO> getBoardList(int startRow, int pageSize) {
		List<NoticeDTO> boardList=new ArrayList<NoticeDTO>();
		
	try {
		con=getConnection();
//		String sql="select * from board order by num desc limit 시작행-1,글개수";
		String sql="select * from notice_board order by nt_num desc limit ?,?";
		pstmt =con.prepareStatement(sql);
		pstmt.setInt(1, startRow-1);
		pstmt.setInt(2, pageSize);
		rs=pstmt.executeQuery();
		
		while(rs.next()) {
			
			NoticeDTO dto=new NoticeDTO();
			dto.setNt_num(rs.getInt("nt_num"));
			dto.setNt_name(rs.getString("nt_name"));
			dto.setNt_title(rs.getString("nt_title"));
			dto.setNt_content(rs.getString("nt_content"));
			dto.setNt_view(rs.getInt("nt_view"));
			dto.setNt_date(rs.getTimestamp("nt_date"));
			
			boardList.add(dto);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		close();
	}
	return boardList;
	}//getBoardList()
	
	// BoardDTO 리턴할형  getBoard(int num)메서드 정의
	public NoticeDTO getBoard(int num) {
		
		NoticeDTO dto=null;
		try {
			con=getConnection();
			String sql="select * from notice_board where nt_num=?";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				//결과 있으면  => num에 대한 글있음
				dto=new NoticeDTO();
				dto.setNt_num(rs.getInt("nt_num"));
				dto.setNt_name(rs.getString("nt_name"));
				dto.setNt_title(rs.getString("nt_title"));
				dto.setNt_content(rs.getString("nt_content"));
				dto.setNt_view(rs.getInt("nt_view"));
				dto.setNt_date(rs.getTimestamp("nt_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		 return dto;
	}//
	
	// update board set readcount=readcount+1 where num=?
			public void updateReadcount(int num) {
				try {
					con = getConnection();
					
					String sql="update notice_board set nt_view = nt_view+1 where nt_num=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					close();
				}
			}//
			
			// 리턴할형없음 updateBoard(BoardDTO 변수)메서드 정의 
			public void updateBoard(NoticeDTO dto) {
				try {
					con = getConnection();
					
					String sql="update notice_board set nt_name=?,nt_title=?,nt_content=? where nt_num=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, dto.getNt_name());
					pstmt.setString(2, dto.getNt_title());
					pstmt.setString(3, dto.getNt_content());
					pstmt.setInt(4, dto.getNt_num());
					pstmt.executeUpdate();
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					close();
				}
			}
			// 리턴할형없음 deleteBoard(int num)메서드 정의
			public void deleteBoard(int num) {

				try {
					con = getConnection();
					String sql="delete from notice_board where nt_num=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					close();
				}
			}//	
			
		public int getBoardCount() {
			int count=0;
			try {
				con=getConnection();
				String sql="select count(*) from notice_board";
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					count=rs.getInt("count(*)");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close();
			}
			return count;
		}
			
}//class
