package com.jdbc.f.JDBCTransaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

public class TransExample {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmtInsert = null;
		PreparedStatement pstmtUpdate = null;
		PreparedStatement pstmtDelete = null;
	    Savepoint sp = null;

		String insertQuery = "INSERT INTO employees(id, name) VALUES(?, ?)";
		String updateQuery = "UPDATE employees SET name = ? WHERE id = ?";
		String deleteQuery = "DELETE FROM employees WHERE id = ?";

		String url = "jdbc:mysql://127.0.0.1:3306/roshini_db";
		String user = "root";
		String password = "Admin@123";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("✅ Connected to MySQL database!");

			// **Step 1: Disable Auto-Commit**
			con.setAutoCommit(false);
			System.out.println("🔄 Transaction started...");

			// **Step 2: INSERT Operation (Successful)**
			pstmtInsert = con.prepareStatement(insertQuery);
			pstmtInsert.setInt(1, 36);
			pstmtInsert.setString(2, "Mark Zuckerberg");
			pstmtInsert.executeUpdate();
			con.commit();
			System.out.println("✅ Insert successful.");
			
			// **Step 3: Create Savepoint After Insert**
            sp = con.setSavepoint();
            System.out.println(" Savepoint created after Insert.");

			// **Step 3: UPDATE Operation (FAILS)**
			pstmtUpdate = con.prepareStatement(updateQuery);
			pstmtUpdate.setString(1, "Jeff Bezos");
			pstmtUpdate.setInt(2, 1000); // ID 1000 does not exist (FAILS)
			int rowsUpdated = pstmtUpdate.executeUpdate();

			if (rowsUpdated == 0) {
				throw new SQLException(" Update failed! ID not found.");
			}

			// **Step 4: DELETE Operation (Skipped if failure occurs)**
			pstmtDelete = con.prepareStatement(deleteQuery);
			pstmtDelete.setInt(1, 33);
			pstmtDelete.executeUpdate();
			System.out.println("✅ Delete successful.");
			
			// **Step 5: Commit Transaction (Only if all queries succeed)**
			con.commit();
			System.out.println("✅ Transaction committed successfully.");

		} catch (Exception e) {
			try {
				// **Step 6: Rollback if any query fails**
				if (con != null) {
					con.rollback();
					System.out.println("❌ Transaction rolled back due to failure.");
				}
			} catch (SQLException rollbackEx) {
				rollbackEx.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// **Step 7: Close resources**
			try {
				if (pstmtInsert != null)
					pstmtInsert.close();
				if (pstmtUpdate != null)
					pstmtUpdate.close();
				if (pstmtDelete != null)
					pstmtDelete.close();
				if (con != null)
					con.close();
				System.out.println("🔚 Connection closed.");
			} catch (SQLException closeEx) {
				closeEx.printStackTrace();
			}
		}
	}
}
