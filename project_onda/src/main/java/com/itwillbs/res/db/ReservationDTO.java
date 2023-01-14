package com.itwillbs.res.db;

import java.sql.Timestamp;

public class ReservationDTO {
	private int res_num;
	private String cus_id;
	private String tb_num;
	private int res_mem;
	private String res_time;
	private Timestamp res_date;
	private String res_use_date;
	private String res_phone;
	private String res_name;

	public int getRes_num() {
		return res_num;
	}

	public void setRes_num(int res_num) {
		this.res_num = res_num;
	}

	public String getCus_id() {
		return cus_id;
	}

	public void setCus_id(String cus_id) {
		this.cus_id = cus_id;
	}

	public String getRes_time() {
		return res_time;
	}

	public void setRes_time(String res_time) {
		this.res_time = res_time;
	}

	public int getRes_mem() {
		return res_mem;
	}

	public void setRes_mem(int res_mem) {
		this.res_mem = res_mem;
	}

	public Timestamp getRes_date() {
		return res_date;
	}

	public void setRes_date(Timestamp res_date) {
		this.res_date = res_date;
	}

	public String getRes_use_date() {
		return res_use_date;
	}

	public void setRes_use_date(String res_use_date) {
		this.res_use_date = res_use_date;
	}

	public String getRes_phone() {
		return res_phone;
	}

	public void setRes_phone(String res_phone) {
		this.res_phone = res_phone;
	}

	public String getTb_num() {
		return tb_num;
	}

	public void setTb_num(String tb_num) {
		this.tb_num = tb_num;
	}

	public String getRes_name() {
		return res_name;
	}

	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}

	@Override
	public String toString() {
		return "ReservationDTO [res_num=" + res_num + ", cus_id=" + cus_id + ", res_time=" + res_time + ", res_mem="
				+ res_mem + ", res_date=" + res_date + ", res_use_date=" + res_use_date + ", res_phone=" + res_phone
				+ ", tb_num=" + tb_num + ", res_name=" + res_name + "]";
	}


	
}