package com.poscoict.board.vo;

public class UserVo {
	
	private String id;
	private String name;
	private String password;
	private String phone;
	private String email;
	
	public UserVo() {
		
	}
	
	public UserVo(String id) {
		this.id = id;
	}
	
	public UserVo(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public UserVo(String id, String name, String password, String phone, String email) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.email = email;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}
