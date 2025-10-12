package accounts;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import helpers.Transaction;

public class SalaryAccount extends BankAccount{
	private LocalDate lastTransactionDate;
	private boolean isFrozen;
	private String employerName;
	private static double interestRate;
		
	static {
		interestRate=0.5;
	}
	
	public SalaryAccount(String holderName, String mobileNo, String accountType,String holderCity,String employerName) {
		super(holderName, mobileNo, accountType, holderCity);
		this.lastTransactionDate = null;
		this.isFrozen = false;
		this.employerName = employerName;
	}

	public LocalDate getLastTranscationDate() {
		return lastTransactionDate;
	}

	public void setLastTranscationDate(LocalDate lastTranscationDate) {
		this.lastTransactionDate = lastTranscationDate;
	}

	public boolean getIsFrozen() {
		return isFrozen;
	}

	public void setFrozen(boolean isFrozen) {
		this.isFrozen = isFrozen;
	}


	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public static double getInterestRate() {
		return interestRate;
	}

	public static void setInterestRate(double interestRate) {
		SalaryAccount.interestRate = interestRate;
	}

	public double calculateInterest() {
        return this.getBalance() * (interestRate/100);
    }
	
	Scanner sc= new Scanner(System.in);
	int choice;
	@Override
	public boolean withdraw(double amount) {
		if (getTransactions().isEmpty()) {
			if(this.getBalance() >= amount) {
				setBalance(getBalance() - amount);
				getTransactions().add(new Transaction("Withdraw", getBalance(), amount));
				return true;
			}
			return false;
	    }
		else {
			lastTransactionDate = getTransactions().get(getTransactions().size() - 1).getTransactionDate();
			long monthsDiff = ChronoUnit.MONTHS.between(lastTransactionDate, LocalDate.now());
			if(monthsDiff >= 2) {
				return false;
			}
			else {
				if(this.getBalance()>=amount) {
					setBalance(this.getBalance()-amount);
					getTransactions().add(new Transaction("Withdraw", getBalance(), amount));
					return true;
				}
				else
					return false;
			}
		}		
	}
	
	public boolean deposit(double amount) {
		if (getTransactions().isEmpty()) {
	        setBalance(getBalance() + amount);
	        getTransactions().add(new Transaction("Deposit", getBalance(), amount));
	        return true;
	    }
		else {
			lastTransactionDate = getTransactions().get(getTransactions().size() - 1).getTransactionDate();
			long monthsDiff = ChronoUnit.MONTHS.between(lastTransactionDate, LocalDate.now());
			if(monthsDiff >= 2) {
				return false;
			}
			else {
				setBalance(getBalance() + amount);
		        getTransactions().add(new Transaction("Deposit", getBalance(), amount));
		        return true;
			}
		}
		
	}

//	private void unFreeze() {
//		// TODO Auto-generated method stub
//		System.out.println("Pay Rs. 500 via Cash.");
//		setLastTranscationDate(LocalDate.now());
//		getTransactions().add(new Transaction("Penalty Paid",this.getBalance(), 500));
//		System.out.println("Penalty Paid!!!");
//	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		super.display();
		 System.out.printf("%-25s : %s%n", "Employer Name", this.employerName);
		 System.out.printf("%-25s : %s%n", "Account Freeze Status", (isFrozen ? "Frozen" : "UnFrozen"));

		 
	}
	
}
