CREATE TABLE MEMBER(
	ID VARCHAR2(30) PRIMARY KEY,
	PWD VARCHAR2(30)	NOT NULL,
	NAME VARCHAR2(30)	NOT NULL,
	EMAIL VARCHAR2(30) UNIQUE,
	AUTH NUMBER(1)
);

SELECT * FROM MEMBER;