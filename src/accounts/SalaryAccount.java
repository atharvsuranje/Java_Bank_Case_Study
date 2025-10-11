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
		super(holderName, mobileNo, accountType, 0, holderCity);
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

	public boolean isFrozen() {
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
	        setBalance(getBalance() + amount);
	        getTransactions().add(new Transaction("Withdraw", getBalance(), amount));
	        return true;
	    }
		else {
			lastTransactionDate = getTransactions().get(getTransactions().size() - 1).getTransactionDate();
			long monthsDiff = ChronoUnit.MONTHS.between(lastTransactionDate, LocalDate.now());
			if(monthsDiff >= 2) {
				return this.freezeProcedure(monthsDiff);
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
		// TODO Auto-generated method stub
		
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
				return this.freezeProcedure(monthsDiff);
			}
			else {
				setBalance(getBalance() + amount);
		        getTransactions().add(new Transaction("Deposit", getBalance(), amount));
		        return true;
			}
		}
		
	}

	private boolean freezeProcedure(long monthsDiff) {
		this.setFrozen(true);
		System.out.println("Your account is frozen due to inactivity (" + monthsDiff + " months).");
		do {
			System.out.println(" 1. Unfreeze This Account By Paying Penalty of Rs. 500.");
			System.out.println(" 2. Exit.");
			System.out.print("Do you want to unfreeze it ? : ");
			choice=sc.nextInt();
			
			switch(choice) {
			case 1:
				this.unFreeze();
				this.setFrozen(false);
				return true;
				
			case 2:
				return false;
				
			default:
				System.out.println("Enter a Valid Choice.");
				break;
			}
		}
		while(choice!=2);
		return false;   //only to satisfy the compiler not logically needed.
	}
	private void unFreeze() {
		// TODO Auto-generated method stub
		System.out.println("Pay Rs. 500 via Cash.");
		setLastTranscationDate(LocalDate.now());
		getTransactions().add(new Transaction("Penalty Paid",this.getBalance(), 500));
		System.out.println("Penalty Paid!!!");
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		super.display();
		 System.out.printf("%-25s : %s%n", "Employer Name", this.employerName);
		 System.out.printf("%-25s : %s%n", "Account Status", (isFrozen ? "Frozen" : "Active"));

		 
	}
	
}
