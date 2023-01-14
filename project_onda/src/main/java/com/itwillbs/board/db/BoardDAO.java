package com.itwillbs.board.db;

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

public class BoardDAO {
	
	Connection con=null;
	PreparedStatement pstmt2 =null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
		public Connection getConnection() throws Exception {
			
			Context init=new InitialContext();
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
		
	public void insertBoard(BoardDTO dto) {
		
		try {
			con = getConnection();
			String sql2 = "select max(qna_num) as maxnum from qna_board";
			pstmt2 = con.prepareStatement(sql2);
			rs = pstmt2.executeQuery();
			
			int num=0;
			if(rs.next()) {
				num=rs.getInt("maxnum")+1;
			}
			String sql="insert into qna_board(qna_num,cus_id,qna_title,qna_content,qna_view,qna_reg,"
					+ "qna_ref,qna_re_seq,qna_re_lev)"
					+ "values(?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, dto.getCus_id());
			pstmt.setString(3, dto.getQna_title());
			pstmt.setString(4, dto.getQna_content());
			pstmt.setInt(5, dto.getQna_view());
			pstmt.setTimestamp(6, dto.getQna_reg());
			pstmt.setInt(7, dto.getQna_ref());
			pstmt.setInt(8, dto.getQna_re_seq());
			pstmt.setInt(9, dto.getQna_re_lev());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}//insertBoard()
	
		public List<BoardDTO> getBoardList(int startRow, int pageSize) {
	
		List<BoardDTO> boardList=new ArrayList<BoardDTO>();
		try {
		
		con = getConnection();
		
	//		String sql="select * from board";
	//		String sql="select * from board order by num desc limit 시작행-1,글개수";
		String sql="select * from qna_board order by qna_num desc limit ?,?";
		pstmt =con.prepareStatement(sql);
		pstmt.setInt(1, startRow-1);
		pstmt.setInt(2, pageSize);
		rs=pstmt.executeQuery();
		
		while(rs.next()) {
			BoardDTO dto=new BoardDTO();
			
			dto.setQna_num(rs.getInt("qna_num"));
			dto.setCus_id(rs.getString("cus_id"));
			dto.setQna_title(rs.getString("qna_title"));
			dto.setQna_content("qna_content");
			dto.setQna_view(rs.getInt("qna_view"));
			dto.setQna_reg(rs.getTimestamp("qna_reg"));

			boardList.add(dto);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		close();
	}
	// 배열의 주소 리턴
	return boardList;
	}//getBoardList()
	
	public BoardDTO getBoard(int num) {
		
		BoardDTO dto=null;
		try {
			con=getConnection();
			
			String sql="select * from qna_board where qna_num=?";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				//결과 있으면  =>num에 대한 글있음
				dto = new BoardDTO();
				dto.setQna_num(rs.getInt("qna_num"));
				dto.setCus_id(rs.getString("cus_id"));
				dto.setQna_title(rs.getString("qna_title"));
				dto.setQna_content("qna_content");
				dto.setQna_view(rs.getInt("qna_view"));
				dto.setQna_reg(rs.getTimestamp("qna_reg"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return dto;
	}//

	public void updateReadcount(int num) {

			try {
				con = getConnection();
				String sql="update qna_board set qna_view=qna_view+1 where qna_num=?";
				
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close();
			}
		}//
			
		public void updateBoard(BoardDTO dto) {
	
			try {
				con = getConnection();
				String sql="update qna_board set qna_title=?,qna_content=? where qna_num=?";
				
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, dto.getQna_title());
				pstmt.setString(2, dto.getQna_content());
				pstmt.setInt(3, dto.getQna_num());
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close();
			}
		}//
			
		public void deleteBoard(int num) {
	
			try {
				con = getConnection();
				String sql="delete from qna_board where qna_num=?";
				
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close();
			}
		}//	
			
		public int getBoardCount() {
			int count=0;
			try {
				con=getConnection();
				String sql="select count(*) from qna_board";
				
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
		
		public int getSeq() {
	        int result = 0;
	        try {
	        	con=getConnection();
	            // 시퀀스 값을 가져온다. (DUAL : 시퀀스 값을 가져오기위한 임시 테이블)
	        	String sql="select qna_ref from qna_board";
	        	
	        	pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
	            
	            if(rs.next())    result = rs.getInt(1);
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	            
	        } finally {
	        	close();
	        }
	        return result;    
	    } // end getSeq
			
}//class
