package helpers;

import java.util.ArrayList;

import accounts.*;

public class Bank {
	private static String branchName;
	private ArrayList<BankAccount> accounts;
	
	static {
		Bank.branchName="Vidarbha";
	}

	
	public Bank() {
		accounts=new ArrayList<BankAccount>();
	}

	public static String getBranchName() {
		return branchName;
	}

	public static void setBranchName(String branchName) {
		Bank.branchName = branchName;
	}

	public ArrayList<BankAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<BankAccount> accounts) {
		this.accounts = accounts;
	}

	public boolean openAccount(String holderName, String mobileNo, String type, String holderCity) {
		accounts.add(new SavingAccount(holderName, mobileNo, type, holderCity));
		return true;
	}

	public boolean openAccount(String holderName, String mobileNo, String type, String holderCity,String employerName) {
		// TODO Auto-generated method stub
		accounts.add(new SalaryAccount(holderName, mobileNo, type, holderCity,employerName));
		return true;
	}

	public boolean openAccount(String holderName, String mobileNo, String type, String holderCity, double overDraftLimit) {
		accounts.add(new CurrentAccount(holderName, mobileNo, type, holderCity, overDraftLimit));
		return true;
	}

	public boolean openAccount(String holderName, String mobileNo, String type, String holderCity, double loanAmount,int tenure) {
		accounts.add(new LoanAccount(holderName, mobileNo, type, holderCity, loanAmount, tenure));
		return true;
	}

	public boolean closeAccount(int accNo) {
		BankAccount bankAccount=searchAccountbyAccountNo(accNo);
		if(bankAccount != null) {
			bankAccount.setAccountStatus(false);
			return true;
		}
		return false;
	}

	public BankAccount searchAccountbyAccountNo(int accNo) {
		for(BankAccount bankAccount: accounts) {
			if(bankAccount.getAccNo() == accNo) return bankAccount;
		}
		return null;
	}

	public boolean withdrawMoney(BankAccount bankAccount, double amount) {
		// TODO Auto-generated method stub
		if(bankAccount.withdraw(amount))
			return true;
		return false;
	}

	public boolean depositMoney(BankAccount bankAccount, double amount) {
		if(bankAccount.deposit(amount))
			return true;
		return false;
	}

	
}
