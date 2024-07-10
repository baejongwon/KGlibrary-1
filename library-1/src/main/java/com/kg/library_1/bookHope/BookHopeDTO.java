package com.kg.library_1.bookHope;

/*

순서 - 번호(1),제목(2),신청인(3),카테고리(4),도서 제목(5),저작자(6),발행자(7),발행년도(8),신청 이유(9),신청 시간(10)

create table bookHope(
no number not null,
board_title varchar2(255),
hope_user varchar2(100),
category varchar2(50),
title_info varchar2(500),
author_info varchar2(500),
pub_info varchar2(500),
pub_year_info varchar2(50),
reason varchar2(200),
hopedate varchar2(30);

//mariadb
CREATE TABLE bookHope (
  no INT AUTO_INCREMENT PRIMARY KEY,
  board_title VARCHAR(255),
  hope_user VARCHAR(100),
  category VARCHAR(50),
  title_info VARCHAR(500),
  author_info VARCHAR(500),
  pub_info VARCHAR(500),
  pub_year_info VARCHAR(50),
  reason VARCHAR(200),
  hopedate VARCHAR(30)
);






*/

public class BookHopeDTO {

	private int no;
	private String board_title;
	private String hope_user;
	private String category;
	private String title_info;
	private String author_info;
	private String pub_info;
	private String pub_year_info;
	private String reason;
	private String hopedate;
	
	
	public String getHopedate() {
		return hopedate;
	}
	public void setHopedate(String hopedate) {
		this.hopedate = hopedate;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getHope_user() {
		return hope_user;
	}
	public void setHope_user(String hope_user) {
		this.hope_user = hope_user;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle_info() {
		return title_info;
	}
	public void setTitle_info(String title_info) {
		this.title_info = title_info;
	}
	public String getAuthor_info() {
		return author_info;
	}
	public void setAuthor_info(String author_info) {
		this.author_info = author_info;
	}
	public String getPub_info() {
		return pub_info;
	}
	public void setPub_info(String pub_info) {
		this.pub_info = pub_info;
	}
	public String getPub_year_info() {
		return pub_year_info;
	}
	public void setPub_year_info(String pub_year_info) {
		this.pub_year_info = pub_year_info;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	
}
