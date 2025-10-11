package accounts;

import java.time.LocalDate;

public class LoanAccount extends BankAccount{

	private double loanAmount;
	private double loanInterestRate;
	private int tenureInMonths;
	private double emiAmount;
	
	public LoanAccount(int accNo, String holderName, String mobileNo, String accountType, double balance,
			String holderCity, LocalDate openingDate,double loanAmount,double loanInterestRate, int tenureInMonths) {
		super(accNo, holderName, mobileNo, accountType, balance, holderCity, openingDate);
		this.loanAmount=loanAmount;
		this.loanInterestRate=loanInterestRate;
		this.tenureInMonths=tenureInMonths;
		this.emiAmount=(this.loanAmount + this.calculateInterest())/tenureInMonths;
		// TODO Auto-generated constructor stub
	}

	public double getLoanInterestRate() {
		return loanInterestRate;
	}

	public void setLoanInterestRate(double loanInterestRate) {
		this.loanInterestRate = loanInterestRate;
	}


	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	
	public int getTenureInMonths() {
		return tenureInMonths;
	}

	public void setTenureInMonths(int tenureInMonths) {
		this.tenureInMonths = tenureInMonths;
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
	
	public double calculateInterest() {
		return this.getLoanAmount() * (loanInterestRate/100);
	}
	
	public boolean payEmi(double amount) {
		if(amount >= this.loanAmount) {
			this.loanAmount -= this.emiAmount;
			this.setBalance(getBalance() + (amount-this.emiAmount));
			return true;
		}
		return false;
	}
}
