package helpers;

import java.time.LocalDate;

public class Transaction {
	private static int idCounter = 100;
	
	private int transactionId;
	private LocalDate transactionDate;
	private String transactionType;
	private double balanceAfter;
	private double amount;
	
	public Transaction(String transactionType,double balance, double amount) {
		this.transactionId = idCounter++;
		this.transactionDate = LocalDate.now();
		this.transactionType = transactionType;
		this.amount = amount;
		this.balanceAfter = balance+amount;                                                                                                                                                                                                                                                                                                                                                                                                 
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getBalanceAfter() {
		return balanceAfter;
	}

	public void setBalanceAfter(double balanceAfter) {
		this.balanceAfter = balanceAfter;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
