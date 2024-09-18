package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.user.MUser;
import com.example.demo.entity.user.User;
import com.example.demo.service.BankService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@Validated
public class SignupController {
	
	@Autowired
	BankService bankService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping("/signup")
	public String getSignup(User user,Model model) {
		
		model.addAttribute("user",user);
		
		return "user/userSignup";
	}
	
	@PostMapping("/signup")
	public String signupUser(@ModelAttribute @Validated User user,BindingResult bindingResult,Model model) {
		
		if(bindingResult.hasErrors()) {
			//NG:ユーザー登録画面へ
			return getSignup(user,model);
		}
		
		//次に登録するユーザーの採番をする
		bankService.updateUserSerialNumber();
		int UserSerialNumber = bankService.getUserSerialNumber();
		
		//受け取った情報を変換
		user.setUserId(String.valueOf(UserSerialNumber));
		MUser m_user = modelMapper.map(user, MUser.class);
		
		//ユーザーを登録
		bankService.signupUser(m_user);
		
		log.info(m_user.toString());
		
		return "redirect:";
	}
}