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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
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
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count>0?true:false;
	}
	
	public List<AccountBookDto> FilterByPeriod(String id, String startDay, String endDay) {
		String sql = " SELECT IO_KIND, AMOUNT, CONTENT, TO_CHAR(WDATE,'YY-MM-DD') " 
					+ " FROM ACCOUNTBOOK " 
					+ " WHERE WDATE BETWEEN TO_DATE(?, 'YYMMDD')"
						+ " AND TO_DATE(?, 'YYMMDD') "
						+ " AND ID =  ? "; // '?'에 외부 데이터가 들어온다.
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<AccountBookDto> list = new ArrayList<AccountBookDto>();
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, startDay); 
			psmt.setString(2, endDay); 
			psmt.setString(3, id); 
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String _io_king = rs.getString("IO_KIND");
				int _amount = rs.getInt("AMOUNT");
				String _content = rs.getString("CONTENT");
				String _wdate = rs.getString("TO_CHAR(WDATE,'YY-MM-DD')");
				
				list.add(new AccountBookDto(0, "", _io_king, _amount, _content, _wdate));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

		return list;
	}
}
