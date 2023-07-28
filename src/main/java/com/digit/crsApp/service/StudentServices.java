package com.digit.crsApp.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.crsApp.CRSApp;

public class StudentServices {
	
	private PreparedStatement pstmt;
	private PreparedStatement pstmt1;
	private Statement stmt;
	private ResultSet resultset;
	private ResultSet res; 
	
	public void availablecourses() {
		try {
			String sql = "select * from course";
			
			stmt = CRSApp.con.createStatement();
			resultset = stmt.executeQuery(sql);
			
			while(resultset.next()==true) {
				System.out.println(resultset.getString(1)+". "+resultset.getString(2));
			}
			
		}
		catch (Exception e) {
			//e.printStackTrace();
			e.getMessage();
		}
	}
	
	public void studrequest() {
		try {
			String sql = "insert into studentrequest values(?,?,?,?,?,?,?)";
			pstmt = CRSApp.con.prepareStatement(sql);
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter your name : ");
			String name = sc.nextLine();
			pstmt.setString(1, name);
			System.out.println("Enter your age : ");
			pstmt.setInt(2, Integer.parseInt(sc.nextLine()));
			System.out.println("Enter your email : ");
			pstmt.setString(3, sc.nextLine());
			System.out.println("Enter course id : ");
			pstmt.setInt(4, Integer.parseInt(sc.nextLine()));
			System.out.println("Selected course : ");
			pstmt.setString(5, sc.nextLine());
			
			String u = "";
			while (true) {
				System.out.println("Enter user name : ");
				u = sc.nextLine();
				
				String sql1 = "select * from student where user_name=?";
				pstmt1 = CRSApp.con.prepareStatement(sql1);
				pstmt1.setString(1, u);
				res = pstmt1.executeQuery();
				if (res.next()) {
					System.out.println("User already exists");
					continue;
				} else {
					break;
				}
			}
			
			pstmt.setString(6, u);
			System.out.println("About you : ");
			pstmt.setString(7, sc.nextLine());
			
			int x = pstmt.executeUpdate();
			if(x>0) {
				System.out.println("New student Request by :"+name);
			}
			
		}
		catch (Exception e) {
			//e.printStackTrace();
			e.getMessage();
		}
	}
	
	public void getscores(String user) {
		try {
			String sql = "select score from student where user_name = ?";
			
			pstmt = CRSApp.con.prepareStatement(sql);
			
			pstmt.setString(1, user);
			
			resultset = pstmt.executeQuery();
			
			while(resultset.next()==true) {
				
				System.out.println("Score : "+resultset.getInt(1));
			}
		}
		catch (Exception e) {
			//e.printStackTrace();
			e.getMessage();
		}
	}
	
	public void markscard(String user) {
		try {
			String sql = "select * from student where user_name = ?";
			
			pstmt = CRSApp.con.prepareStatement(sql);
			
			pstmt.setString(1, user);
			
			resultset = pstmt.executeQuery();
			
			while(resultset.next()==true) {
				System.out.println("ID          : "+resultset.getInt(1));
				System.out.println("Name        : "+resultset.getString(2));
				System.out.println("User Name   : "+resultset.getString(8));
				System.out.println("Age         : "+resultset.getInt(3));
				System.out.println("Email       : "+resultset.getString(4));
				System.out.println("Course id   : "+resultset.getInt(5));
				System.out.println("Course name : "+resultset.getString(6));
				System.out.println("Score       : "+resultset.getInt(7));
				if(resultset.getInt(7)>0) {
					System.out.println("You have completed the course with "+resultset.getInt(7)+"%");
				}
				else {
					System.out.println("Grades are not released yet.");
				}
			}
		}
		catch (Exception e) {
			//e.printStackTrace();
			e.getMessage();
		}
	}
	
	public void updatepword(String user) {
		try {
			Scanner sc = new Scanner(System.in);

			String sql = "update student set password = ? where user_name = ?";

			pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("Enter new password : ");
			pstmt.setString(1, sc.next());
			pstmt.setString(2, user);

			int x = pstmt.executeUpdate();
			System.out.println("Details updated successfully");
		}
		catch (Exception e) {
			//e.printStackTrace();
			e.getMessage();
		}
	}
	
	public void updatemail(String user) {
		try {
			Scanner sc = new Scanner(System.in);

			String sql = "update student set email = ? where user_name = ?";

			pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("Enter new email : ");
			pstmt.setString(1, sc.next());
			pstmt.setString(2, user);

			int x = pstmt.executeUpdate();
			System.out.println("Details updated successfully");
		}
		catch (Exception e) {
			//e.printStackTrace();
			e.getMessage();
		}
	}
	
	public void updatename(String user) {
		try {
			Scanner sc = new Scanner(System.in);

			String sql = "update student set sname = ? where user_name = ?";

			pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("Enter your name : ");
			pstmt.setString(1, sc.next());
			pstmt.setString(2, user);

			int x = pstmt.executeUpdate();
			System.out.println("Details updated successfully");
		}
		catch (Exception e) {
			//e.printStackTrace();
			e.getMessage();
		}
	}
}
