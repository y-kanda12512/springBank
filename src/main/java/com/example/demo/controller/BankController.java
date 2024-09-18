package com.example.demo.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.history.History;
import com.example.demo.entity.history.MHistory;
import com.example.demo.service.BankService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.NonNull;

@Transactional
@Controller
@Validated
public class BankController {

	@Autowired
	private BankService bankService;

	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/redirectMain")
	public String redirectMain() {
		
		return "redirect:";
	}
	
	@PostMapping("/logout")
	public String logout() {
		
		return "redirect:";
	}
	
	//取引履歴表示
	@GetMapping("/history")
	public String getHistory(Model model) {

		//ここに取引履歴を全部持ってくる処理
		List<MHistory> historyList = bankService.getHistory();
		//model.addAttributeわすれずに
		model.addAttribute("historyList", historyList);

		return "bankHTML/history";
	}

	//預け入れ登録画面を表示
	@GetMapping("/deposit")
	public String getDeposit() {

		return "bankHTML/deposit";
	}

	//預け入れ処理
	/*
	 * ・金額を受け取るが、その金額をユーザーの残高へ足して更新する
	 * ・預け入れ後の金額を表示する(データベースに登録 & Modelに登録)
	 * */
	@PostMapping("/depositRegistration")
	public String depositRegistration(Model model,@Valid @Min(1) @RequestParam("deposit")int depositAmount, History history,BindingResult bindingResult) {
		
		//バリデーションを
		if(bindingResult.hasErrors()) {
			//預入金額入力画面へ戻ります
			return "/deposit";
		}
		
		String transactionName = "預け入れ";

		//ユーザーの残高更新処理
		bankService.balanceUpdate(depositAmount);

		//取引後の残高を取得、次の画面へmodel登録
		int balanceResult = bankService.getBalance();
		model.addAttribute("balanceResult",balanceResult);

		//historyテーブルのテーブル数から、次に登録する取引履歴のNumberを設定
		//追記：updateSerialNumberを追加、シリアルナンバーをアップデート
		bankService.updateHistorySerialNumber();
		String transactionIdSerialNumber = String.valueOf(bankService.getHistorySerialNumber());
		
		//取引履歴をJAVAのエンティティに設定
		bankService.setHistory(transactionIdSerialNumber, transactionName, balanceResult, "1", history);
		MHistory m_history = modelMapper.map(history, MHistory.class);

		//取引履歴登録処理
		bankService.insertHistory(m_history);

		return "bankHTML/depositResult";
	}
	
	//引き出し金額画面へ
	@GetMapping("/withdraw")
	public String getWithdraw() {
		
		return "bankHTML/withdraw";
	}
	
	//引き出し処理
	@PostMapping("/withdraw")
	public String withdraw(Model model, @Valid @Min(1) @RequestParam("withdrawalAmount")int withdrawalAmount,History history){
		
		String transactionName = "引き出し";
		
		//ユーザーの残高更新処理
		bankService.balanceUpdate(-withdrawalAmount);
		
		//取引後の残高を取得、次の画面へmodel登録
		int balanceResult = bankService.getBalance();
		model.addAttribute("balanceResult", balanceResult);
		
		//historyテーブルのテーブル数から、次に登録する取引履歴のNumberを設定
		bankService.updateHistorySerialNumber();
		String transactionIdSerialNumber = String.valueOf(bankService.getHistorySerialNumber());
		
		//取引履歴をJAVAのエンティティに設定
		bankService.setHistory(transactionIdSerialNumber, transactionName, balanceResult, "1", history);
		MHistory m_history = modelMapper.map(history, MHistory.class);
		
		//取引履歴登録処理
		bankService.insertHistory(m_history);

		return "bankHTML/withdrawResult";
	}
	
	//振り込み処理画面へ
	@GetMapping("/transfer")
	public String getTransfer() {
		
		return "bankHTML/transfer";
	}
	
	//振り込み処理
	@PostMapping("/transfer")
	public String transfer(Model model,@NonNull @Valid @RequestParam("userId") String userId,@RequestParam("TransferAmount")int TransferAmount,History history) {
		
		String transactionName = "お振込";
		
		//ユーザーの残高更新処理
		bankService.balanceUpdateOtherUsers(userId, TransferAmount);
		
		//取引後の残高を取得、次の画面へmodel登録
		int balanceResult = bankService.getBalanceOtherUsers(userId);
		model.addAttribute("balanceResult", balanceResult);
		model.addAttribute(userId);
		
		//historyテーブルのテーブル数から、次に登録する取引履歴のNumberを設定
		bankService.updateHistorySerialNumber();
		String transactionIdSerialNumber = String.valueOf(bankService.getHistorySerialNumber());
		
		//取引履歴をJAVAのエンティティに設定
		bankService.setHistory(transactionIdSerialNumber, transactionName, balanceResult, "1", history);
		MHistory m_history = modelMapper.map(history, MHistory.class);
		
		//取引履歴登録処理
		bankService.insertHistory(m_history);
		
		return "bankHTML/transferResult";
	}
}
