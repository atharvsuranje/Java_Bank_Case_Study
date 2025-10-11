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

	public void openAccount(String holderName, String mobileNo, String type, String holderCity) {
		accounts.add(new SavingAccount(holderName, mobileNo, type, holderCity));
	}

	public void openAccount(String holderName, String mobileNo, String type, String holderCity,String employerName) {
		// TODO Auto-generated method stub
		accounts.add(new SalaryAccount(holderName, mobileNo, type, holderCity,employerName));
	}

	public void openAccount(String holderName, String mobileNo, String type, String holderCity, double overDraftLimit) {
		accounts.add(new CurrentAccount(holderName, mobileNo, type, holderCity, overDraftLimit));
	}

	public void openAccount(String holderName, String mobileNo, String type, String holderCity, double loanAmount,int tenure) {
		accounts.add(new LoanAccount(holderName, mobileNo, type, holderCity, loanAmount, tenure));
	}

	
}
