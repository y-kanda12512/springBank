package com.example.demo.entity.history;

import lombok.Data;

@Data
public class History {
	private String transactionId;
	private String transactionName;
	private int balance;
	private String userId;
}
