package com.jdbc.a.LoadRegisterDriver;

public class MySQLConnection1 {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver class loaded and registered..");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
