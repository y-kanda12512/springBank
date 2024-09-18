package com.example.demo.entity.history;

import lombok.Data;

@Data
public class MHistory {
	private String transactionId;
	private String transactionName;
	private int balance;
	private String userId;
}