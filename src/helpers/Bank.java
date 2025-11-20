package helpers;

import java.time.LocalDate;
import java.util.ArrayList;

import accounts.*;

public class Bank {
	private static String branchName;
	private static ArrayList<BankAccount> accounts;
	
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
		Bank.accounts = accounts;
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

	public boolean transferMoney(BankAccount sender, BankAccount reciever, double amount) {
		if(sender.withdraw(amount)) {
			if(reciever.deposit(amount))
				return true;
		}
		return false;
	}

	public boolean payEmi(LoanAccount bankAccount1, double emiAmount) {
		if(bankAccount1.payEmi(emiAmount)) {
			return true;
		}
		return false;
	}
	
	public void displayTransactions(BankAccount bankAccount) {
		bankAccount.displayAllTransactions();
	}
	
	public void displayAllAccounts() {
		for(BankAccount bankAccount:accounts) {
			bankAccount.display();
		}
	}
	
	public void generateBankReport(ArrayList<BankAccount> accounts) {
		System.out.println("\n------------------- BANK REPORT -------------------");
	    System.out.println("Date: " + LocalDate.now());
	    System.out.println("---------------------------------------------------");

	    int totalAccounts = accounts.size();
	    int savingsCount = 0, salaryCount = 0, currentCount = 0,loanCount=0, frozenCount = 0;
	    double totalBalance = 0;
	    double totalDeposits = 0, totalWithdrawals = 0, totalTransfers = 0;

	    BankAccount highest = null, lowest = null;
	    int maxTransactions = 0;
	    BankAccount mostActive = null;

	    for (BankAccount acc : accounts) {
	        totalBalance += acc.getBalance();

	        switch (acc.getAccountType().toLowerCase()) {
	        	//lambda expression
	            case "saving" -> savingsCount++;
	            case "salary" -> salaryCount++;
	            case "current" -> currentCount++;
	            case "loan" -> loanCount++;
	        }

	        if (acc instanceof SalaryAccount salaryAcc) {
	        	if(salaryAcc.getIsFrozen()) frozenCount++;
	        }

	        // Highest and lowest balance
	        if (highest == null || acc.getBalance() > highest.getBalance()) highest = acc;
	        if (lowest == null || acc.getBalance() < lowest.getBalance()) lowest = acc;

	        // Most active account
	        int txnCount = acc.getTransactions().size();
	        if (txnCount > maxTransactions) {
	            maxTransactions = txnCount;
	            mostActive = acc;
	        }

	        // Optional: Summarize transaction totals
	        for (Transaction t : acc.getTransactions()) {
	            switch (t.getTransactionType().toLowerCase()) {
	                case "deposit" -> totalDeposits += t.getAmount();
	                case "withdraw" -> totalWithdrawals += t.getAmount();
	                case "transfer" -> totalTransfers += t.getAmount();
	            }
	        }
	    }

	    int activeCount = totalAccounts - frozenCount;
	    double avgBalance = totalAccounts > 0 ? totalBalance / totalAccounts : 0;

	    // Report Display
	    System.out.println("Total Accounts         : " + totalAccounts);
	    System.out.println("Savings Accounts       : " + savingsCount);
	    System.out.println("Salary Accounts        : " + salaryCount);
	    System.out.println("Current Accounts       : " + currentCount);
	    System.out.println("Loan Accounts          : " + loanCount);
	    System.out.println("Active Accounts        : " + activeCount);
	    System.out.println("Frozen Accounts        : " + frozenCount);
	    System.out.println("---------------------------------------------------");
	    System.out.printf("Total Balance in Bank   : ₹%.2f\n", totalBalance);
	    System.out.printf("Average Balance         : ₹%.2f\n", avgBalance);
	    System.out.println("---------------------------------------------------");
	    System.out.printf("Total Deposited Amount  : ₹%.2f\n", totalDeposits);
	    System.out.printf("Total Withdrawn Amount  : ₹%.2f\n", totalWithdrawals);
	    System.out.printf("Total Transferred Amount: ₹%.2f\n", totalTransfers);
	    System.out.println("---------------------------------------------------");

	    if (highest != null)
	        System.out.println("Highest Balance Holder : " + highest.getHolderName() +
	                " (A/c No: " + highest.getAccNo() + ", ₹" + highest.getBalance() + ")");
	    if (lowest != null)
	        System.out.println("Lowest Balance Holder  : " + lowest.getHolderName() +
	                " (A/c No: " + lowest.getAccNo() + ", ₹" + lowest.getBalance() + ")");
	    if (mostActive != null)
	        System.out.println("Most Active Account    : " + mostActive.getHolderName() +
	                " (A/c No: " + mostActive.getAccNo() + ", " + maxTransactions + " transactions)");

	}
	
	
	
	// function for Adding default accounts to your Bank class's accounts ArrayList
	public void initializeDefaultAccounts() {
		accounts.add(new SavingAccount("Amit Sharma", "9876543210", "Saving", "Mumbai"));
		accounts.get(accounts.size() - 1).setOpeningDate(LocalDate.now().minusMonths(1));

		accounts.add(new SavingAccount("Priya Singh", "8765432109", "Saving", "Delhi"));
		accounts.get(accounts.size() - 1).setOpeningDate(LocalDate.now().minusMonths(5));

		accounts.add(new SalaryAccount("Rahul Verma", "7654321098", "Salary", "Bangalore", "Infosys"));
		accounts.get(accounts.size() - 1).setOpeningDate(LocalDate.now().minusMonths(3));

		accounts.add(new SalaryAccount("Sneha Kapoor", "6543210987", "Salary", "Pune", "TCS"));
		accounts.get(accounts.size() - 1).setOpeningDate(LocalDate.now().minusMonths(7));

		accounts.add(new CurrentAccount("Vikram Reddy", "5432109876", "Current", "Hyderabad", 10000));
		accounts.get(accounts.size() - 1).setOpeningDate(LocalDate.now().minusMonths(2));

		accounts.add(new CurrentAccount("Anjali Mehta", "4321098765", "Current", "Chennai", 20000));
		accounts.get(accounts.size() - 1).setOpeningDate(LocalDate.now().minusMonths(6));

		accounts.add(new LoanAccount("Karan Malhotra", "3210987654", "Loan", "Jaipur", 500000, 60));
		accounts.get(accounts.size() - 1).setOpeningDate(LocalDate.now().minusMonths(8));

		accounts.add(new LoanAccount("Ritika Desai", "2109876543", "Loan", "Lucknow", 300000, 36));
		accounts.get(accounts.size() - 1).setOpeningDate(LocalDate.now().minusMonths(4));

		accounts.add(new SavingAccount("Aditya Joshi", "1098765432", "Saving", "Ahmedabad"));
		accounts.get(accounts.size() - 1).setOpeningDate(LocalDate.now().minusMonths(9));

		accounts.add(new SalaryAccount("Meera Nair", "9988776655", "Salary", "Kochi", "Wipro"));
		accounts.get(accounts.size() - 1).setOpeningDate(LocalDate.now().minusMonths(10));

	}
	
	//function for dummy transcations
	public void initializeDefaultTransactions() {
		// 1. Amit Sharma (SavingAccount) — skip first deposit
		BankAccount acc1 = accounts.get(0);
	    withdrawMoney(acc1, 200);
	    depositMoney(acc1, 500);

	    // 2. Priya Singh (SavingAccount) — skip first deposit
	    BankAccount acc2 = accounts.get(1);
	    depositMoney(acc2, 2000);
	    withdrawMoney(acc2, 500);

	    // 3. Rahul Verma (SalaryAccount)
	    BankAccount acc3 = accounts.get(2);
	    depositMoney(acc3, 50000);
	    withdrawMoney(acc3, 10000);
	    depositMoney(acc3, 25000);

	    // 4. Sneha Kapoor (SalaryAccount)
	    BankAccount acc4 = accounts.get(3);
	    depositMoney(acc4, 60000);
	    withdrawMoney(acc4, 15000);

	    // 5. Vikram Reddy (CurrentAccount)
	    BankAccount acc5 = accounts.get(4);
	    depositMoney(acc5, 5000);
	    withdrawMoney(acc5, 2000);

	    // 6. Anjali Mehta (CurrentAccount)
	    BankAccount acc6 = accounts.get(5);
	    depositMoney(acc6, 10000);
	    withdrawMoney(acc6, 5000);

	    // 7. Karan Malhotra (LoanAccount)
	    LoanAccount acc7 = (LoanAccount) accounts.get(6);
	    acc7.getTransactions().add(new Transaction("Loan Disbursed", acc7.getBalance(), 500000));
	    payEmi(acc7,acc7.getEmiAmount());

	    // 8. Ritika Desai (LoanAccount)
	    LoanAccount acc8 = (LoanAccount) accounts.get(7);
	    acc8.getTransactions().add(new Transaction("Loan Disbursed", acc8.getBalance(), 300000));
	    payEmi(acc8, 8000);

	    // 9. Aditya Joshi (SavingAccount) — skip first deposit
	    BankAccount acc9 = accounts.get(8);
	    depositMoney(acc9, 3000);
	    withdrawMoney(acc9, 1000);

	    // 10. Meera Nair (SalaryAccount)
	    BankAccount acc10 = accounts.get(9);
	    depositMoney(acc10, 40000);
	    withdrawMoney(acc10, 5000);
	}
}
