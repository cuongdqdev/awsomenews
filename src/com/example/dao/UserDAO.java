package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.model.UserModel;
import com.example.tools.Md5;

public class UserDAO {

	public static boolean checkUserLogin(String username, String password) {

		Connection connection = DBConfig.getConnection();
		String sql = "SELECT * FROM tb_user WHERE username= ? and password = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, Md5.md5(password));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static UserModel getUser(String username, String password) {
		UserModel user = new UserModel();
		Connection connection = DBConfig.getConnection();
		String sql = "SELECT * FROM tb_user WHERE username = ? and password = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, Md5.md5(password));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user.setFullName(resultSet.getString("fullname"));
				user.setUserName(resultSet.getString("username"));
				user.setPassWord(resultSet.getString("password"));
				user.setRoleName(resultSet.getString("rolename"));
				user.setRole(resultSet.getInt("role"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	public static void main(String[] args) {
		String userName = "cuongdq";
		String passWord = "123456789";
		if(checkUserLogin(userName, passWord)) {
			System.out.println("Login success!");
		}
		else {
			System.out.println("Login fail!");
		}
		
		UserModel user = UserDAO.getUser(userName, passWord);
		System.out.println("User after login: " + user.getFullName());
	}
}
