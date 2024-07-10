package com.kg.library_1.donate;
/*
create table book_donate( 
no number,
id varchar2(30),
subject varchar2(100),
book_no number,
method varchar2(50),
content varchar2(1000),
reg_date varchar2(20),
PRIMARY KEY (no)
);

//mariadb
CREATE TABLE book_donate (
  no INT,
  id VARCHAR(30),
  subject VARCHAR(100),
  book_no INT,
  method VARCHAR(50),
  content VARCHAR(1000),
  reg_date VARCHAR(20),
  PRIMARY KEY (no)
);


 */
public class DonateDTO {
	private int no;
	private String id;
	private String subject;
	private int book_no;
	private String method;
	private String content;
	private String reg_date;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getBook_no() {
		return book_no;
	}
	public void setBook_no(int book_no) {
		this.book_no = book_no;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
		
}
