package com.digit.crsApp.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.crsApp.CRSApp;

public class ProfessorServices {
	
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
	
	public void profrequest() {
		try {
			String sql = "insert into professorrequest values(?,?,?,?,?,?,?)";
			pstmt = CRSApp.con.prepareStatement(sql);
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter your name : ");
			String name = sc.nextLine();
			pstmt.setString(1, name);
			System.out.println("Enter your age : ");
			pstmt.setInt(2, Integer.parseInt(sc.nextLine()));
			System.out.println("Enter your experience : ");
			pstmt.setInt(3, Integer.parseInt(sc.nextLine()));
			System.out.println("Enter course id : ");
			pstmt.setInt(4, Integer.parseInt(sc.nextLine()));
			System.out.println("Selected course : ");
			pstmt.setString(5, sc.nextLine());
			
			String u = "";
			while (true) {
				System.out.println("Enter user name : ");
				u = sc.nextLine();
				
				String sql1 = "select * from professor where user_name=?";
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
				System.out.println("New Professor Request by :"+name);
			}
			
		}
		catch (Exception e) {
			//e.printStackTrace();
			e.getMessage();
		}
}
	public void enrolledstudents(String user) {
		try {
            String sql1 = "select cid from professor where user_name = ?";
			
			pstmt = CRSApp.con.prepareStatement(sql1);
			
			pstmt.setString(1, user);
			
			resultset = pstmt.executeQuery();
			
			
			String sql = "select sname from student where cid = ?";
			
			pstmt = CRSApp.con.prepareStatement(sql);
			resultset.first();
			pstmt.setInt(1, resultset.getInt(1));
			
			resultset = pstmt.executeQuery();
			
			while(resultset.next()==true) {
				
				System.out.println(resultset.getString(1));
			}
		}
		catch (Exception e) {
			//e.printStackTrace();
			e.getMessage();
		}
	}
	
	public void gradestudent(String user) {
		try {
			Scanner sc = new Scanner(System.in);
            String sql1 = "select cid from professor where user_name = ?";
			
			pstmt = CRSApp.con.prepareStatement(sql1);
			
			pstmt.setString(1, user);
			
			resultset = pstmt.executeQuery();
			
			
			String sql = "select sname from student where cid = ?";
			
			pstmt = CRSApp.con.prepareStatement(sql);
			resultset.first();
			pstmt.setInt(1, resultset.getInt(1));
			
			resultset = pstmt.executeQuery();
			
			while(resultset.next()==true) {
				System.out.print("Enter score for student "+resultset.getString(1)+" ");
				int score = sc.nextInt();
				System.out.print(resultset.getString(1)+" : "+score+"\n");
				
                String sql2 = "update student set score = ? where sname = ?";
				
				pstmt = CRSApp.con.prepareStatement(sql2);
				pstmt.setInt(1, score);
				pstmt.setString(2, resultset.getString(1));
				
				int x = pstmt.executeUpdate();
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

			String sql = "update professor set password = ? where user_name = ?";

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
	
	public void updatename(String user) {
		try {
			Scanner sc = new Scanner(System.in);

			String sql = "update professor set pname = ? where user_name = ?";

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
	
	public void updateexp(String user) {
		try {
			Scanner sc = new Scanner(System.in);

			String sql = "update professor set exp = ? where user_name = ?";

			pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("Enter your experience : ");
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
