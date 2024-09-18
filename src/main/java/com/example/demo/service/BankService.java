package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.history.History;
import com.example.demo.entity.history.MHistory;
import com.example.demo.entity.user.MUser;
import com.example.demo.repository.BankMapper;

@Service
public class BankService {

	@Autowired
	private BankMapper bankMapper;

	public List<MHistory> getHistory() {

		return bankMapper.getHistory();
	}

	//残高更新
	public void balanceUpdate(int depositMoney) {

		bankMapper.balanceUpdate(depositMoney);
	}

	//残高更新(他ユーザーの残高アップデートでオーバーロード)
	public void balanceUpdateOtherUsers(String userId, int TransferAmount) {

		bankMapper.balanceUpdateOtherUsers(userId, TransferAmount);
	}

	//残高のみ取得する処理
	public int getBalance() {

		return bankMapper.getBalance();
	}

	public int getBalanceOtherUsers(String userId) {

		return bankMapper.getBalanceOtherUsers(userId);
	}

	//取引履歴をHistoryクラスのフィールドへ登録
	public void setHistory(String transactionId, String transactionName, int balance, String userId, History history) {
		history.setTransactionId(transactionId);
		history.setTransactionName(transactionName);
		history.setBalance(balance);
		history.setUserId(userId);
	}

	public int getHistorySerialNumber() {

		return bankMapper.getHistorySerialNumber();
	}

	//取引履歴登録処理
	public void insertHistory(MHistory history) {

		bankMapper.insertHistory(history);
	}

	public void updateHistorySerialNumber() {

		bankMapper.updateHistorySerialNumber();
	}

	public int getUserSerialNumber() {

		return bankMapper.getUserSerialNumber();
	}

	public void updateUserSerialNumber() {

		bankMapper.updateUserSerialNumber();
	}

	//ユーザー登録
	public void signupUser(MUser m_user) {

		bankMapper.signupUser(m_user);
	}

	//ユーザー一覧を取得
	public List<MUser> getUserList() {

		return bankMapper.getUserList();
	}

	//ユーザー取得（1件のみ）
	public MUser getUserOne(String userId) {

		return bankMapper.getUserOne(userId);
	}

	//ユーザー更新
	public void updateUserOne(String userId, String userName, String password) {

		bankMapper.updateUserOne(userId, userName, password);
	}

	//ユーザー削除
	public void deleteUserOne(String userId) {

		bankMapper.deleteUserOne(userId);
	}
}
