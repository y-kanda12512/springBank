INSERT INTO m_user 
	(userId,userName,password,balance)
	VALUES
	('1','神田','password',1000),
	('2','しみけん','password',2000)
;

INSERT INTO history
	(transactionId,transactionName,balance,userId)
	VALUES
	('1','預け入れ','1000','1')
;

--テーブル増えるたび更新必須--
INSERT INTO SerialNumber (historyNumber,userNumber) VALUES(1,2);