CREATE TABLE IF NOT EXISTS m_user(
	userId VARCHAR(50) PRIMARY KEY,
	userName VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	balance INT DEFAULT 0 CHECK(0<=balance)
);

CREATE TABLE history(
	transactionId VARCHAR(50) PRIMARY KEY,
	transactionName VARCHAR(50) NOT NULL,
	balance INT DEFAULT 0 CHECK(0<=balance),
	userId VARCHAR(50) NOT NULL
);

--テーブルの連番管理DB(制約で連番が設定できればしたいが...)--
CREATE TABLE IF NOT EXISTS SerialNumber(
	historyNumber INTEGER NOT NULL,
	userNumber INTEGER NOT NULL
);