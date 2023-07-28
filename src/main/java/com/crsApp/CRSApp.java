package com.crsApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import com.digit.crsApp.beans.Users;
import com.digit.crsApp.service.AdminServices;
import com.digit.crsApp.service.ProfessorServices;
import com.digit.crsApp.service.StudentServices;

public class CRSApp {
	public static Connection con;
	
	public static void main(String[] args) throws Exception{

		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Loaded");
		String url = "jdbc:mysql://localhost:3306/crs";

		String user = "root";
		String pwd  = "1234";
		// Step:2
		con = DriverManager.getConnection(url, user, pwd);
		
		
		while(true) {
			try {
				System.out.println("Select the Type of User:");
				System.out.println("1. Admin\n"
						+ "2. Professor\n"
						+ "3. Student\n"
						+ "4. New Professor\n"
						+ "5. New Student\n"
						+ "6. Exit");
				Scanner sc = new Scanner(System.in);
				int n = sc.nextInt();
				switch (n) {
				case 1: {
					String b = Users.login("admin");
					if(!(b.equals("false"))) {
						System.out.println("Admin Login Success");
						AdminServices adsrv = new AdminServices();
						adsrv.menu();
					}
					else {
						System.out.println("Wrong credentials. Try again with correct credentials");
					}
					break;
				}
				case 2: {
					try {
						String b = Users.login("professor");
						if(!(b.equals("false"))) {
							System.out.println("Professor Login Success");
							ProfessorServices ps = new ProfessorServices();
							while(true) {
								System.out.println("Select the Type of User:");
								System.out.println("1. Enrolled students\n"
										+ "2. Grade students\n"
										+ "3. update password\n"
										+ "4. update name\n"
										+ "5. update experience\n"
										+ "6. Exit");
								int m = sc.nextInt();
								switch (m) {
								case 1: 
									ps.enrolledstudents(b);
									break;
								case 2:
									ps.gradestudent(b);
									break;
								case 3:
									ps.updatepword(b);
									break;
								case 4:
									ps.updatename(b);
									break;
								case 5:
									ps.updateexp(b);
									break;
								case 6:
									break;
								default:
									System.out.println("Select only from given options");
									break;
								}
								if(m==6) {
									break;
								}
							}
						}
						else {
							System.out.println("Wrong credentials. Try again with correct credentials");
						}
					}
					catch(Exception e) {
						e.getMessage();
						System.out.println("Select only from given options");
					}
					break;
				}
				case 3: {
					try {
						String b = Users.login("student");
						if(!(b.equals("false"))) {
							System.out.println("Student Login Success");
							StudentServices ss = new StudentServices();
							while(true) {
								System.out.println("Select the Type of User:");
								System.out.println("1. Score\n"
										+ "2. Marks card\n"
										+ "3. update password\n"
										+ "4. update email\n"
										+ "5. update name\n"
										+ "6. Exit");
								int m = sc.nextInt();
								switch (m) {
								case 1: 
									ss.getscores(b);
									break;
								case 2:
									ss.markscard(b);
									break;
								case 3:
									ss.updatepword(b);
									break;
								case 4:
									ss.updatemail(b);
									break;
								case 5:
									ss.updatename(b);
									break;
								case 6:
									break;
								default:
									System.out.println("Select only from given options");
									break;
								}
								if(m==6) {
									break;
								}
							}
						}
						else {
							System.out.println("Wrong credentials. Try again with correct credentials");
						}
					}
					catch(Exception e) {
						System.out.println("Select only from given options");
					}
					break;
				}
				case 4: {
					ProfessorServices ps = new ProfessorServices();
					System.out.println("Select course from the following : ");
					ps.availablecourses();
					ps.profrequest();
					break;
				}
				case 5: {
					StudentServices ss = new StudentServices();
					ss.availablecourses();
					ss.studrequest();
					break;
				}
				case 6: 
					System.exit(0);
				default:
					System.out.println("Select only from given options");
				}
			}
			catch(Exception e) {
				System.out.println("Select only from given options");
			}
		}
	}
}
