package com.jdbc.d.ExecuteSQLqueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class executeUpdate_Statement {
	public static void main(String[] args) {

		String url = "jdbc:mysql://127.0.0.1:3306/roshini_db";
		String user = "root";
		String password = "Admin@123";
		Connection con;
		Statement stmt;
//		String q1 = "insert into employees values(22, 'AnnaPoorany')";
//		String q3 = "delete from employees where id = 14";
		
		/*
		 * executeUpdate() (For Single DML Execution) Executes INSERT, UPDATE,
		 * DELETE, or DDL statements (CREATE, ALTER, DROP, etc.). 
		 * Returns an int, indicating the number of affected rows. 
		 * Used for executing a single SQL update at a time.
		 * 
		 */

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // ✅ Correct Driver Class
			con = DriverManager.getConnection(url, user, password);
			System.out.println("✅Connected to MySQL database server!");

			stmt = con.createStatement();
			System.out.println("Platform created................");

//			stmt.executeUpdate(q1);
//			stmt.executeUpdate(q3);
			int rowsAffected = stmt.executeUpdate("UPDATE Employees SET name = 'Jack' WHERE id = 4");
			System.out.println("Rows Updated: " + rowsAffected);
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
