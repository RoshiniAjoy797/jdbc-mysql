package com.jdbc.b.EstablishConnecion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection2 {
	public static void main(String[] args) {

		String url = "jdbc:mysql://127.0.0.1:3306/roshini_db";
		String user = "root";
		String password = "Admin@123";
		Connection con;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // ✅ Correct Driver Class
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to MySQL database!");
		} catch (ClassNotFoundException e) {
			System.out.println("❌ MySQL JDBC Driver Not Found!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("❌ Database Connection Failed!");
			e.printStackTrace();
		}
	}
}
