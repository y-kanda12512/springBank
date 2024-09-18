package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.user.MUser;
import com.example.demo.service.BankService;

@Controller
public class ListController {
	
	@Autowired
	private BankService bankService;
	
	@GetMapping("/list")
	public String listController(Model model,MUser user) {
		
		List<MUser> userList = bankService.getUserList();
		model.addAttribute("userList",userList);
		
		return "user/list";
	}
	
	@GetMapping("/detailUser/{userId}")
	public String detail(Model model,@PathVariable("userId")String userId) {
		
		MUser user = bankService.getUserOne(userId);
		
		model.addAttribute("user",user);
		
		return "user/detail";
	}
	
	@PostMapping(value="/detail",params="update")
	public String updateUser(Model model,MUser user) {
		
		bankService.updateUserOne(user.getUserId(),user.getUserName(),user.getPassword());
		
		return "redirect:/list";
	}
	
	@PostMapping(value="/detail",params="delete")
	public String deleteUser(Model model,MUser user) {
		
		bankService.deleteUserOne(user.getUserId());
		
		return "redirect:/list";
	}
}
