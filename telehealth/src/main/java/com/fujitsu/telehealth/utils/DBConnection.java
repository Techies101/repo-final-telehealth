package com.fujitsu.telehealth.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static String dbURL = "jdbc:mysql://localhost:3306/telehealth";
	private static String dbUname = "root";
	private static String dbPass = "Root";
	private static String dbDriver = "com.mysql.cj.jdbc.Driver";
	

	public static Connection connect() {
		Connection conn = null;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbURL, dbUname, dbPass);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("SQLErrCode: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
