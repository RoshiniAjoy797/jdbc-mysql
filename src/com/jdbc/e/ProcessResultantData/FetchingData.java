package com.jdbc.e.ProcessResultantData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FetchingData {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;

		String url = "jdbc:mysql://127.0.0.1:3306/roshini_db";
		String user = "root";
		String password = "Admin@123";

		// String q1 = "Select * from Employees";
		String q1 = "Select * from Employees where name = ?";

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter name: ");
		String name = sc.next();
		sc.close();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // ✅ Correct Driver Class
			con = DriverManager.getConnection(url, user, password);
			System.out.println("✅Connected to MySQL database server!");

			pstmt = con.prepareStatement(q1);
			System.out.println("Platform created................");

//			rst = pstmt.executeQuery();
//			while (rst.next()) {
//			    int id = rst.getInt(1); // First column (id)
//			    String name = rst.getString("name"); // Correct column name
//			    System.out.println(id + " " + name);
//			}

			pstmt.setString(1, name); // Use user input correctly
			rst = pstmt.executeQuery();

			boolean found = false;
			while (rst.next()) { // Loop to get all matching records
				int id = rst.getInt(1); // Assuming id is the first column
				System.out.println("ID: " + id);
				found = true;
			}
			if (!found) {
				System.err.println("No data found for name: " + name);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("❌ MySQL JDBC Driver Not Found!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("❌ Database Connection Failed!");
			e.printStackTrace();
		} finally {
			// Close resources in reverse order
			try {
				if (rst != null) 
					rst.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
				System.out.println("✅ Resources closed successfully!");
			} catch (SQLException e) {
				System.err.println("⚠️ Error while closing resources!");
				e.printStackTrace();
			}
		}
	}
}
