package dto;

import java.io.Serializable;

/*
	CREATE TABLE MEMBER(
		ID VARCHAR2(30) PRIMARY KEY,
		PWD VARCHAR2(30) NOT NULL,
		NAME VARCHAR2(30) NOT NULL,
		EMAIL VARCHAR2(30) UNIQUE,
		AUTH NUMBER(1)
	);
*/

public class MemberDto implements Serializable {
	
	private String id;
	private String pwd;
	private String name;
	private String email;
	private int auth; // 관리자와 / 사용자를 나누는 부분
	
	//constructor--------------------------------------
	
	public MemberDto() {}

	public MemberDto(String id, String pwd, String name, String email, int auth) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.auth = auth;
	}

	//get & set--------------------------------------
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	// toString-------------------------------------
	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", auth=" + auth + "]";
	}
	
	
	
	
}
