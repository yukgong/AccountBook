package dao;

/*
 CREATE TABLE ACCOUNTBOOK(
	SEQ NUMBER(8),			
	ID VARCHAR2(30),		-- 외래키
	IO_KIND VARCHAR2(1),	-- 수입/지출
	AMOUNT NUMBER(9),		-- 금액
	CONTENT VARCHAR2(200),	-- 내용
	WDATE DATE				-- 입력일
);
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DBConnection;
import dto.AccountBookDto;

public class AccountBookDao {
	
	private static AccountBookDao abo = new AccountBookDao();

	private AccountBookDao() {
	}

	public static AccountBookDao getInstace() {
		return abo;
	}
	
	public boolean insertData(AccountBookDto dto) {
		String sql = " INSERT INTO ACCOUNTBOOK "
				+ " VALUES(SEQ_BOOK.NEXTVAL, ?, ?, ?, ?, SYSDATE) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;

		try {
			conn = DBConnection.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getIo_kind());
			psmt.setInt(3, dto.getAmount());
			psmt.setString(4, dto.getContent());
			
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count>0?true:false;
	}
	
}
