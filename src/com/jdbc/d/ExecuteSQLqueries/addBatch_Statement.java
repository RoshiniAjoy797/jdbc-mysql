package com.jdbc.d.ExecuteSQLqueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class addBatch_Statement {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;

		String q1 = "insert into employees values(26,'Ajoy')";
		String q2 = "Update employees set name = 'Navamani' where id = 9";
		String q3 = "delete from employees where id = 20";
		
		
		String url = "jdbc:mysql://127.0.0.1:3306/roshini_db";
		String user = "root";
		String password = "Admin@123";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // ✅ Correct Driver Class
			con = DriverManager.getConnection(url, user, password);
			System.out.println("✅Connected to MySQL database server!");

			stmt = con.createStatement();
			System.out.println("Platform created................");
			
			// add DML queries into Batch
			stmt.addBatch(q1);
			stmt.addBatch(q2);
			stmt.addBatch(q3);
			
			// execute batch
			int ar[] = stmt.executeBatch();
			for(int i : ar) {
				System.out.println("No. of row affected : "+i);
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
				if (con != null)
					con.close();
				if (stmt != null)
					stmt.close();
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
