package com.jdbc.d.ExecuteSQLqueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class executeUpdate_PreparedStatement {
	public static void main(String[] args) {
		Connection con;
		PreparedStatement pstmt;
		String url = "jdbc:mysql://127.0.0.1:3306/roshini_db";
		String user = "root";
		String password = "Admin@123";
		
		String q1 = "insert into employees values(?,?)";
		

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // ✅ Correct Driver Class
			con = DriverManager.getConnection(url, user, password);
			System.out.println("✅Connected to MySQL database server!");
			
			pstmt = con.prepareStatement(q1);
			System.out.println("Platform created................");
			
			//Set the data using Placeholders before execution
			pstmt.setInt(1, 23);
			pstmt.setString(2, "Shiv");
			
			//execute query
			pstmt.executeUpdate();
			
			//Set the data using Placeholders before execution
			pstmt.setInt(1, 24);
			pstmt.setString(2, "Sakthi");
			
			//execute query
			pstmt.executeUpdate();
			System.out.println("Data modified.........");
			
		} catch (ClassNotFoundException e) {
			System.out.println("❌ MySQL JDBC Driver Not Found!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("❌ Database Connection Failed!");
			e.printStackTrace();
		}
	}

}
