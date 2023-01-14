package com.itwillbs.board.db;

import java.sql.Timestamp;

public class BoardDTO {

	private int qna_num;
	private String cus_id;
	private String qna_title;
	private String qna_content;
	private int qna_view;
	private Timestamp qna_reg;
	private int qna_ref;
	private int qna_re_lev;
	private int qna_re_seq;
	
	
	public int getQna_num() {
		return qna_num;
	}
	public void setQna_num(int qna_num) {
		this.qna_num = qna_num;
	}
	public String getCus_id() {
		return cus_id;
	}
	public void setCus_id(String cus_id) {
		this.cus_id = cus_id;
	}
	public String getQna_title() {
		return qna_title;
	}
	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public int getQna_view() {
		return qna_view;
	}
	public void setQna_view(int qna_view) {
		this.qna_view = qna_view;
	}
	public Timestamp getQna_reg() {
		return qna_reg;
	}
	public void setQna_reg(Timestamp qna_reg) {
		this.qna_reg = qna_reg;
	}
	public int getQna_ref() {
		return qna_ref;
	}
	public void setQna_ref(int qna_ref) {
		this.qna_ref = qna_ref;
	}
	public int getQna_re_lev() {
		return qna_re_lev;
	}
	public void setQna_re_lev(int qna_re_lev) {
		this.qna_re_lev = qna_re_lev;
	}
	public int getQna_re_seq() {
		return qna_re_seq;
	}
	public void setQna_re_seq(int qna_re_seq) {
		this.qna_re_seq = qna_re_seq;
	}
	
	
	
	
	
}
