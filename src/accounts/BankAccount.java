package accounts;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import helpers.Transaction;

public abstract class BankAccount {
	
	//attributes
	private int accNo;
	private String holderName;
	private String mobileNo;
	private String accountType;
	private double balance;
	private ArrayList<Transaction> transactions= new ArrayList<Transaction>();
	private String holderCity;
	private LocalDate openingDate;
	private LocalDate closingDate;

	//parameterized constructor
	public BankAccount(int accNo, String holderName, String mobileNo, String accountType, double balance, String holderCity, LocalDate openingDate) {
		this.accNo = accNo;
		this.holderName = holderName;
		this.mobileNo = mobileNo;
		this.accountType = accountType;
		this.balance = balance;
		this.holderCity = holderCity;
		this.openingDate = openingDate;
		
		this.closingDate = null;
	}

	//getters
	public int getAccNo() {
		return accNo;
	}

	public String getHolderName() {
		return holderName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public String getAccountType() {
		return accountType;
	}

	public double getBalance() {
		return balance;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	
	public String getHolderCity() {
		return holderCity;
	}

	public LocalDate getOpeningDate() {
		return openingDate;
	}

	public LocalDate getClosingDate() {
		return closingDate;
	}

	//setters
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setHolderCity(String holderCity) {
		this.holderCity = holderCity;
	}

	public void setClosingDate(LocalDate closingDate) {
		this.closingDate = closingDate;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}

	//abstract functions to be overridden
	public abstract boolean withdraw(double amount);
	public void display() {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		    System.out.println("\n==================== ACCOUNT DETAILS ====================");
		    System.out.printf("%-25s : %d%n", "Account Number", accNo);
		    System.out.printf("%-25s : %s%n", "Account Holder Name", holderName);
		    System.out.printf("%-25s : %s%n", "Mobile Number", mobileNo);
		    System.out.printf("%-25s : %s%n", "Account Type", accountType);
		    System.out.printf("%-25s : %.2f%n", "Balance", balance);
		    System.out.printf("%-25s : %s%n", "City", holderCity);
		    System.out.printf("%-25s : %s%n", "Opening Date", openingDate.format(formatter));
	}
	
	public double calculateIntrest() {
		return 0;
	}
	
	public boolean deposit(double amount) {
		this.balance = this.balance+amount;
		transactions.add(new Transaction("Deposit", this.balance , amount));
		return true;
	}
}
