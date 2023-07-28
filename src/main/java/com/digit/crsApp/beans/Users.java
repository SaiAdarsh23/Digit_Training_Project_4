	package com.digit.crsApp.beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.crsApp.CRSApp;

public class Users {
	static String user_name;
	static String password;
	private static PreparedStatement pstmt;
	private static ResultSet res;
	public Users(String user_name, String password) {
		super();
		this.user_name = user_name;
		this.password = password;
	}
	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}
	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	public static String login(String s) {
		try {
			// TODO Auto-generated method stub
			Scanner sc = new Scanner(System.in);
			String sql;
			System.out.println("Enter the user_name:");
			user_name=sc.next();
			System.out.println("Enter the password:");
			password=sc.next();
			if(s.equals("admin")) {
				sql = "select * from users where user_name=? and password=?";
				pstmt = CRSApp.con.prepareStatement(sql);
			}
			else if(s.equals("professor")) {
				sql = "select * from professor where user_name=? and password=?";
				pstmt = CRSApp.con.prepareStatement(sql);
			}
			else {
				sql = "select * from student where user_name=? and password=?";
				pstmt = CRSApp.con.prepareStatement(sql);
			}
			
			pstmt.setString(1, user_name);
			pstmt.setString(2, password);
			res = pstmt.executeQuery();
			if(res.next()) {
				return user_name;
			}
			else {
				return "false";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		return "false";
	}


}
