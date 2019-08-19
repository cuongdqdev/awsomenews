package com.example.model;

public class UserModel {
	public String fullName;
	public String userName;
	public String passWord;
	public String roleName;
	public int role;

	public UserModel() {
		super();
	}

	public UserModel(String fullName, String userName, String passWord, String roleName, int role) {
		super();
		this.fullName = fullName;
		this.userName = userName;
		this.passWord = passWord;
		this.roleName = roleName;
		this.role = role;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

}
