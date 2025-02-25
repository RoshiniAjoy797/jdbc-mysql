package com.jdbc.d.ExecuteSQLqueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

public class addBatch_PreparedStatement {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;

		String q1 = "insert into employees(id, name) values(?,?)";
		String q2 = "Update employees set name = ? where id = ?";

		String url = "jdbc:mysql://127.0.0.1:3306/roshini_db";
		String user = "root";
		String password = "Admin@123";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // ✅ Correct Driver Class
			con = DriverManager.getConnection(url, user, password);
			System.out.println("✅Connected to MySQL database server!");

			pstmt = con.prepareStatement(q1);
			System.out.println("Inserting record................");
			// add DML queries into Batch
			pstmt.setInt(1, 32);
			pstmt.setString(2, "Elon Musk");
			pstmt.addBatch();

			pstmt.setInt(1, 33);
			pstmt.setString(2, "Jeff Bezos");
			pstmt.addBatch();

			// Execute INSERT batch
			int ar[] = pstmt.executeBatch();
			for (int i : ar) {
				System.out.println("NUM of Rows affected (INSERT) : " + i);
			}
			pstmt.close(); // Close first `pstmt` to avoid issues

			// Batch for UPDATE
			pstmt = con.prepareStatement(q2);
			System.out.println("Updating records...");
			pstmt.setString(1, "Ganesh");
			pstmt.setInt(2, 32); // ✅ Correct order (name first, then id)
			pstmt.addBatch();

			pstmt.setString(1, "Ramesh");
			pstmt.setInt(2, 15);
			pstmt.addBatch();

			// Execute UPDATE batch
			int[] updateResults = pstmt.executeBatch();
			for (int i : updateResults) {
				System.out.println("NUM of Rows affected (UPDATE): " + i);
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
