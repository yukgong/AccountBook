package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	static public void initConnection() {
		try {
			//forName은 DB가 있는지 없는지 확인만 하는 역할
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			System.out.println("Driver Loading Success");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Driver Loading Faile");
		}
	}
	
	static public Connection getConnection() { // jdbc에 있는 오브젝트 연결
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.7.60:1521:xe", 
					"hr", 
					"hr"
					);

			System.out.println("Oracle Connection Success!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
