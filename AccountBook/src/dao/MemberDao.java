package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DBClose;
import db.DBConnection;

public class MemberDao {

	private static MemberDao dao = new MemberDao();

	private MemberDao() {
	}

	public static MemberDao getInstace() {
		return dao;
	}

	public boolean getId(String id) {
		String sql = " SELECT ID " + " FROM MEMBER " + " WHERE ID = ?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		boolean findId = false;

		try {
			conn = DBConnection.getConnection(); // db 정보
			psmt = conn.prepareStatement(sql); // query 정보
			psmt.setString(1, id);

			rs = psmt.executeQuery();

			if (rs.next()) { // id가 있다면
				findId = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return findId;
	}

	public boolean addMember(String id, String pwd, String name, String email) {
		String sql = " INSERT INTO MEMBER "
					+ " VALUES(?, ?, ?, ?, 3) "; 
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pwd);
			psmt.setString(3, name);
			psmt.setString(4, email);
			
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		
		return count > 0? true : false;
	}

}
