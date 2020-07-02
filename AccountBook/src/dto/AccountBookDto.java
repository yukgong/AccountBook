package dto;

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

public class AccountBookDto {
	private int seq;
	private String id;
	private String io_kind;
	private int amount;
	private String content;
	private String wdate;
	
	// constructor-----------------
	public AccountBookDto() {
	}


	public AccountBookDto(int seq, String id, String io_kind, int amount, String content, String wdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.io_kind = io_kind;
		this.amount = amount;
		this.content = content;
		this.wdate = wdate;
	}

	// get & set -----------------------------
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIo_kind() {
		return io_kind;
	}

	public void setIo_kind(String io_kind) {
		this.io_kind = io_kind;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	
	
	//toString ------------------
	
	@Override
	public String toString() {
		return "AccountBookDto [seq=" + seq + ", id=" + id + ", io_kind=" + io_kind + ", amount=" + amount
				+ ", content=" + content + ", wdate=" + wdate + "]";
	}
	
	
	
	
}
