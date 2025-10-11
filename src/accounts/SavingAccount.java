package accounts;

import java.time.LocalDate;

public class SavingAccount extends BankAccount{
	private static double interestRate;
	private static double minimumBalance;
	
	static {
		interestRate=0.5;
		minimumBalance=1000;
	}
	
	public SavingAccount(int accNo, String holderName, String mobileNo, String accountType, double balance,String holderCity, LocalDate openingDate) {
		super(accNo, holderName, mobileNo, accountType, balance, holderCity, openingDate);
	}

	public static double getInterestRate() {
		return interestRate;
	}

	public static void setInterestRate(double interestRate) {
		SavingAccount.interestRate = interestRate;
	}

	public static double getMinimumBalance() {
		return minimumBalance;
	}

	public static void setMinimumBalance(double minimumBalance) {
		SavingAccount.minimumBalance = minimumBalance;
	}

	public double calculateInterest() {
        return this.getBalance() * (interestRate/100);
    }

	@Override
	public boolean withdraw() {
		// TODO Auto-generated method stub
		return true;
		
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}
}
