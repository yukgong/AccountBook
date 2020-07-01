package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBClose {
	public static void close(Statement stat, Connection conn, ResultSet rs) {

		try {
			if (stat != null) {
				stat.close();
			}
			if (conn != null) {
				conn.close();
			}
			if (rs != null) { // Select는 DB의 데이터를 가져오는게 목적이다.
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
