package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.history.MHistory;
import com.example.demo.entity.user.MUser;

@Mapper
public interface BankMapper {
	
	public List<MHistory> getHistory();
	
	public void balanceUpdate(int depositMoney);
	
	public void balanceUpdateOtherUsers(String userId,int TransferAmount);
	
	public int getBalance();
	
	public int getBalanceOtherUsers(String userId);
	
	public int getHistorySerialNumber();
	
	public int getUserSerialNumber();
	
	public void insertHistory(MHistory history);
	
	//historyテーブルの連番をアップデート
	public void updateHistorySerialNumber();
	
	//m_userテーブルの連番をアップデート
	public void updateUserSerialNumber();
	
	public void signupUser(MUser m_user);
	
	//ユーザーの一覧取得
	public List<MUser> getUserList();
	
	//ユーザー取得（1件）
	public MUser getUserOne(String userId);
	
	//ユーザー情報更新
	public void updateUserOne(@Param("userId")String userId,@Param("userName")String userName,@Param("password")String password);
	
	//ユーザー削除
	public int deleteUserOne(@Param("userId")String userId);
}