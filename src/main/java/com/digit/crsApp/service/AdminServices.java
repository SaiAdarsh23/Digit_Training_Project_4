package com.digit.crsApp.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.crsApp.CRSApp;
import com.digit.crsApp.beans.Course;

public class AdminServices {

	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultset;

	public void menu() {
		// TODO Auto-generated method stub
		System.out.println("Select Option:");
		System.out.println("1. Add course\n"
				+ "2. Add Student\n"
				+ "3. Add Professor\n"
				+ "4. Remove Course\n"
				+ "5. Remove Professor\n"
				+ "6. Remove Student\n"
				+ "7. View All Students\n"
				+ "8. View All Courses\n"
				+ "9. View All Professors\n"
				+ "10. View All Users\n"
				+ "11. View New Professor Requests\n"
				+ "12. View New Student Requests\n"
				+ "0. Exit\n");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		switch (n) {
		case 1: {
			addCourse();
			break;
		}
		case 2: {
			addStudent();
			break;
		}
		case 3: {
			addProfessor();
			break;
		}
		case 4: {
			removeCourse();
			break;
		}
		case 5: {
			removeProfessor();
			break;
		}
		case 6: {
			removeStudent();
			break;
		}
		case 7: {
			allstudents();
			break;
		}
		case 8: {
			allcourses();
			break;
		}
		case 9: {
			allpro();
			break;
		}
		case 10: {
			allusers();
			break;
		}
		case 11: {
			allproreq();
			break;
		}
		case 12: {
			allstudentsreq();
			break;
		}
		case 0:{
			break;
		}
		default:
		}
	}

	public void addCourse() {
		try {
			String sql = "insert into course(cname, fees, dur_months) values(?,?,?)";
			pstmt = CRSApp.con.prepareStatement(sql);
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the course name : ");
			String c = sc.next();
			pstmt.setString(1, c);
			System.out.println("Enter the fees : ");
			pstmt.setInt(2, sc.nextInt());
			System.out.println("Enter the duration in months : ");
			pstmt.setInt(3, sc.nextInt());
			
			int x = pstmt.executeUpdate();
			if(x>0) {
				System.out.println("New Course Added :"+c);
				menu();
			}
			
		}
		catch (Exception e) {
			//e.printStackTrace();
			e.getMessage();
		}
	}
		
		public void addStudent() {
			try {
				
                String sql1 = "select * from studentrequest";
				
				stmt = CRSApp.con.createStatement();
				resultset = stmt.executeQuery(sql1);
				
				while(resultset.next()==true) {
	
					System.out.println("Name        :  "+resultset.getString(1));
					System.out.println("Age         :  "+resultset.getString(2));
					System.out.println("Email       :  "+resultset.getString(3));
					System.out.println("Course id   :  "+resultset.getString(4));
					System.out.println("Course      :  "+resultset.getString(5));
					System.out.println("User Name   :  "+resultset.getString(6));
					System.out.println("Description :  "+resultset.getString(7));
					System.out.println("---------------");
				}
				
				String sql = "insert into student(sname, age, email, cid, course, user_name, password)  values(?,?,?,?,?,?,?)";
				
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter next to approve later.");
				System.out.println("Enter user name of the student to be added: ");
				String u = sc.nextLine();
				if(u.equals("next")) {
					menu();
				}
				
                String sql3 = "select * from studentrequest where user_name = ?";
                pstmt = CRSApp.con.prepareStatement(sql3);
				pstmt.setString(1, u);
				
				resultset = pstmt.executeQuery();
				resultset.first();
				
				pstmt = CRSApp.con.prepareStatement(sql);
				pstmt.setString(1, resultset.getString(1));
				pstmt.setString(2, resultset.getString(2));
				pstmt.setString(3, resultset.getString(3));
				pstmt.setString(4, resultset.getString(4));
				pstmt.setString(5, resultset.getString(5));
				pstmt.setString(6, resultset.getString(6));
				pstmt.setString(7, "student");
				
				int x1 = pstmt.executeUpdate();
				if(x1>0) {
					System.out.println("New Student Added :"+resultset.getString(1));
				}
				
                String sql2 = "delete from studentrequest where user_name = ?";
				
				pstmt = CRSApp.con.prepareStatement(sql2);
				pstmt.setString(1, u);
				
				int x3 = pstmt.executeUpdate();
				
				if(x3>0) {
					menu();
				}

				
			}
			catch (Exception e) {
				//e.printStackTrace();
				e.getMessage();
			}
	}
		
		public void addProfessor() {
			try {
				
                String sql1 = "select * from professorrequest";
				
				stmt = CRSApp.con.createStatement();
				resultset = stmt.executeQuery(sql1);
				
				while(resultset.next()==true) {
	
					System.out.println("Name        :  "+resultset.getString(1));
					System.out.println("Age         :  "+resultset.getInt(2));
					System.out.println("Experience  :  "+resultset.getInt(3));
					System.out.println("Course id   :  "+resultset.getInt(4));
					System.out.println("Course      :  "+resultset.getString(5));
					System.out.println("User Name   :  "+resultset.getString(6));
					System.out.println("Description :  "+resultset.getString(7));
					System.out.println("---------------");
				}
				
				String sql = "insert into professor(pname, age, exp, cid, course, user_name, password)  values(?,?,?,?,?,?,?)";
				
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter next to approve later.");
				System.out.println("Enter user name of the professor to be added: ");
				String u = sc.nextLine();
				if(u.equals("next")) {
					menu();
				}
				
                String sql3 = "select * from professorrequest where user_name = ?";
                pstmt = CRSApp.con.prepareStatement(sql3);
				pstmt.setString(1, u);
				
				resultset = pstmt.executeQuery();
				resultset.first();
				
				pstmt = CRSApp.con.prepareStatement(sql);
				pstmt.setString(1, resultset.getString(1));
				pstmt.setString(2, resultset.getString(2));
				pstmt.setString(3, resultset.getString(3));
				pstmt.setString(4, resultset.getString(4));
				pstmt.setString(5, resultset.getString(5));
				pstmt.setString(6, resultset.getString(6));
				pstmt.setString(7, "professor");
				
				
				int x = pstmt.executeUpdate();
				if(x>0) {
					System.out.println("New Professor Added :"+u);
				}
				
                String sql2 = "delete from professorrequest where user_name = ?";
				
				pstmt = CRSApp.con.prepareStatement(sql2);
				pstmt.setString(1, u);
				
				int x1 = pstmt.executeUpdate();
				
				if(x1>0) {
					menu();
				}
				
			}
			catch (Exception e) {
				//e.printStackTrace();
				e.getMessage();
			}
	}
		
		public void removeCourse() {
			try {
				
                String sql1 = "select * from course";
				
				stmt = CRSApp.con.createStatement();
				resultset = stmt.executeQuery(sql1);
				
				while(resultset.next()==true) {
					System.out.println("Course ID   : "+resultset.getInt(1));
					System.out.println("Course Name : "+resultset.getString(2));
					System.out.println("---------------");
				}
				
				String sql = "delete from course where cid = ?";
				
				pstmt = CRSApp.con.prepareStatement(sql);
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter the course name to be removed : ");
				int c = Integer.parseInt(sc.nextLine());
				pstmt.setInt(1, c);
				
				int x = pstmt.executeUpdate();
				
				if(x>0) {
					System.out.println("Course removed ");
					menu();
				}
				
			}
			catch (Exception e) {
				//e.printStackTrace();
				e.getMessage();
			}
		}
		
		public void removeProfessor() {
			try {
				
                String sql1 = "select * from professor";
				
				stmt = CRSApp.con.createStatement();
				resultset = stmt.executeQuery(sql1);
				
				while(resultset.next()==true) {
					
					System.out.println("ID          : "+resultset.getInt(1));
					System.out.println("Name        : "+resultset.getString(2));
					System.out.println("Age         : "+resultset.getInt(3));
					System.out.println("Experience  : "+resultset.getInt(4));
					System.out.println("Course ID   : "+resultset.getInt(5));
					System.out.println("Course Name : "+resultset.getString(6));
					System.out.println("User Name   : "+resultset.getString(7));
					System.out.println("-----------------");
				}
				
				String sql = "delete from professor where user_name = ?";
				
				pstmt = CRSApp.con.prepareStatement(sql);
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter the professor user name to be removed : ");
				String c = sc.next();
				pstmt.setString(1, c);
				
				int x = pstmt.executeUpdate();
				
				if(x>0) {
					System.out.println("Professor removed ");
					menu();
				}
				
			}
			catch (Exception e) {
				//e.printStackTrace();
				e.getMessage();
			}
		}
		
		public void removeStudent() {
			try {
				
                String sql1 = "select * from student";
				
				stmt = CRSApp.con.createStatement();
				resultset = stmt.executeQuery(sql1);
				
				while(resultset.next()==true) {
					System.out.println("ID          : "+resultset.getInt(1));
					System.out.println("Name        : "+resultset.getString(2));
					System.out.println("Age         : "+resultset.getInt(3));
					System.out.println("Email       : "+resultset.getString(4));
					System.out.println("Course ID   : "+resultset.getInt(5));
					System.out.println("Course Name : "+resultset.getString(6));
					System.out.println("User Name   : "+resultset.getString(8));
					System.out.println("------------------");
				}
				
				String sql = "delete from student where user_name = ?";
					
				pstmt = CRSApp.con.prepareStatement(sql);
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter the student user name to be removed : ");
				String c = sc.next();
				pstmt.setString(1, c);
				
				int x = pstmt.executeUpdate();
				
				if(x>0) {
					System.out.println("student removed ");
					menu();
				}
				
			}
			catch (Exception e) {
				//e.printStackTrace();
				e.getMessage();
			}
		}
		
		public void allstudents() {
			try {
				String sql = "select * from student";
				
				stmt = CRSApp.con.createStatement();
				resultset = stmt.executeQuery(sql);
				
				while(resultset.next()==true) {
					System.out.println("ID          : "+resultset.getInt(1));
					System.out.println("Name        : "+resultset.getString(2));
					System.out.println("Age         : "+resultset.getInt(3));
					System.out.println("Email       : "+resultset.getString(4));
					System.out.println("Course ID   : "+resultset.getInt(5));
					System.out.println("Course Name : "+resultset.getString(6));
					System.out.println("User Name   : "+resultset.getString(7));
					System.out.println("------------------");
				}
				menu();
			}
			catch (Exception e) {
				//e.printStackTrace();
				e.getMessage();
			}
		}
		
		public void allstudentsreq() {
			try {
				String sql = "select * from studentrequest";
				
				stmt = CRSApp.con.createStatement();
				resultset = stmt.executeQuery(sql);
				
				while(resultset.next()==true) {
					System.out.println("Name        : "+resultset.getString(1));
					System.out.println("Age         : "+resultset.getInt(2));
					System.out.println("Email       : "+resultset.getString(3));
					System.out.println("Course ID   : "+resultset.getInt(4));
					System.out.println("Course Name : "+resultset.getString(5));
					System.out.println("User Name   : "+resultset.getString(6));
					System.out.println("Description : "+resultset.getString(7));
					System.out.println("------------------");
				}
				menu();
			}
			catch (Exception e) {
				//e.printStackTrace();
				e.getMessage();
			}
		}
		
		public void allcourses() {
			try {
				String sql = "select * from course";
				
				stmt = CRSApp.con.createStatement();
				resultset = stmt.executeQuery(sql);
				
				while(resultset.next()==true) {
					System.out.println("Course ID   : "+resultset.getInt(1));
					System.out.println("Course Name : "+resultset.getString(2));
					System.out.println("---------------");
				}
				menu();
			}
			catch (Exception e) {
				//e.printStackTrace();
				e.getMessage();
			}
		}
		
		public void allpro() {
			try {
				String sql = "select * from professor";
				
				stmt = CRSApp.con.createStatement();
				resultset = stmt.executeQuery(sql);
				
				while(resultset.next()==true) {
					
					System.out.println("ID          : "+resultset.getInt(1));
					System.out.println("Name        : "+resultset.getString(2));
					System.out.println("Age         : "+resultset.getInt(3));
					System.out.println("Experience  : "+resultset.getInt(4));
					System.out.println("Course ID   : "+resultset.getInt(5));
					System.out.println("Course Name : "+resultset.getString(6));
					System.out.println("User Name   : "+resultset.getString(7));
					System.out.println("-----------------");
				}
				menu();
			}
			catch (Exception e) {
				//e.printStackTrace();
				e.getMessage();
			}
		}
		
		public void allproreq() {
			try {
				String sql = "select * from professorrequest";
				
				stmt = CRSApp.con.createStatement();
				resultset = stmt.executeQuery(sql);
				
				while(resultset.next()==true) {
					
					System.out.println("Name        : "+resultset.getString(1));
					System.out.println("Age         : "+resultset.getInt(2));
					System.out.println("Experience  : "+resultset.getInt(3));
					System.out.println("Course ID   : "+resultset.getInt(4));
					System.out.println("Course Name : "+resultset.getString(5));
					System.out.println("User Name   : "+resultset.getString(6));
					System.out.println("Description : "+resultset.getString(7));
					System.out.println("-----------------");
				}
				menu();
			}
			catch (Exception e) {
				//e.printStackTrace();
				e.getMessage();
			}
		}
		
		public void allusers() {
			try {
				String sql = "select * from users";
				
				stmt = CRSApp.con.createStatement();
				resultset = stmt.executeQuery(sql);
				
				while(resultset.next()==true) {
	
					System.out.println("User Name : "+resultset.getString(1));
					System.out.println("---------------");
				}
				menu();
			}
			catch (Exception e) {
				//e.printStackTrace();
				e.getMessage();
			}
		}

}
