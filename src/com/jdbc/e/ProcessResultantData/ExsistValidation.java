package com.jdbc.e.ProcessResultantData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ExsistValidation {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;

		String url = "jdbc:mysql://127.0.0.1:3306/roshini_db";
		String user = "root";
		String password = "Admin@123";

		String q1 = "Select * from Employees where id = ? and name = ? ";

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter id: ");
		int id = sc.nextInt();
		System.out.print("Enter name: ");
		String name = sc.next();
		sc.close();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // ✅ Correct Driver Class
			con = DriverManager.getConnection(url, user, password);
			System.out.println("✅Connected to MySQL database server!");

			pstmt = con.prepareStatement(q1);
			System.out.println("Platform created................");

			pstmt.setInt(1, id); // Use user input correctly
			pstmt.setString(2, name);
			rst = pstmt.executeQuery();

			boolean found = false;
			while (rst.next()) { // Loop to get all matching records
				int i = rst.getInt(1); // Assuming id is the first column
				System.out.println("ID: " + i);
				String n = rst.getString(2);
				System.out.println("Name: "+n);
				found = true;
			}
			if (!found) {
				System.err.println("No data found for id , name: " + name+","+id);
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
