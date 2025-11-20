package accounts;

import helpers.Transaction;

public class SavingAccount extends BankAccount{
	private static final long serialVersionUID = 1L;
	private static double interestRate;
	private static double minimumBalance;
	
	static {
		interestRate=0.5;
		minimumBalance=1000;
	}
	
	public SavingAccount(String holderName, String mobileNo, String accType,String holderCity) {
		super(holderName, mobileNo,accType, holderCity);
		super.deposit(minimumBalance);
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
	public boolean withdraw(double amount) {
		if(amount > this.getBalance()) return false;
		if(minimumBalance <= this.getBalance()-amount) {
			setBalance(this.getBalance()-amount);
			getTransactions().add(new Transaction("Withdraw", this.getBalance(), amount));
			return true;
		}
		return false;
	}
	
}
