package com.itwillbs.notice.db;

import java.sql.Timestamp;

public class NoticeDTO {
	
	private int nt_num;
	private String nt_name;
	private String nt_title;
	private String nt_content;
	private int nt_view;
	private Timestamp nt_date;
	
	public String getNt_name() {
		return nt_name;
	}
	public void setNt_name(String nt_name) {
		this.nt_name = nt_name;
	}
	public int getNt_num() {
		return nt_num;
	}
	public void setNt_num(int nt_num) {
		this.nt_num = nt_num;
	}
	public String getNt_title() {
		return nt_title;
	}
	public void setNt_title(String nt_title) {
		this.nt_title = nt_title;
	}
	public String getNt_content() {
		return nt_content;
	}
	public void setNt_content(String nt_content) {
		this.nt_content = nt_content;
	}
	public int getNt_view() {
		return nt_view;
	}
	public void setNt_view(int nt_view) {
		this.nt_view = nt_view;
	}
	public Timestamp getNt_date() {
		return nt_date;
	}
	public void setNt_date(Timestamp nt_date) {
		this.nt_date = nt_date;
	}
	
	
	
	
	
	
}
