package dao;

import java.sql.*;

import db.DBClose;
import db.DBConnection;
import dto.MemberDto;

public class MemberDao {

	private static MemberDao dao = new MemberDao();
	private String loginID;

	private MemberDao() {
	}

	public static MemberDao getInstace() {
		return dao;
	}
	
	public MemberDto getIdAndPwd(String id, String pwd) {
		String sql = " SELECT ID, NAME, EMAIL, AUTH " 
					+ " FROM MEMBER " 
					+ " WHERE ID = ? AND PWD = ?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		MemberDto dto = null;


		try {
			conn = DBConnection.getConnection(); // db 정보
			psmt = conn.prepareStatement(sql); // query 정보
			psmt.setString(1, id);
			psmt.setString(2, pwd);

			rs = psmt.executeQuery();

			if (rs.next()) { // id와 pwd가 있다면
				String _id = rs.getString(1);	// id
				String _name = rs.getString(2);	// name
				String _email = rs.getString(3);// email
				int auth = rs.getInt(4);	// auth
				
				dto = new MemberDto(_id, null, _name, _email, auth);
				
//				dto = new MemberDto();
//				dto.setId(rs.getString("id"));
//				dto.setPwd(rs.getString(null));
//				dto.setName(rs.getString("name"));
//				dto.setEmail(rs.getString("email"));
//				dto.setAuth(rs.getInt(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return dto;
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
	
	// login Id get & set ------------------------
	public String getLoginID() {
		return loginID;
	}
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
}
