package accounts;

import helpers.Transaction;

public class LoanAccount extends BankAccount{

	private double loanAmount;
	private static double loanInterestRate;
	private int tenureInMonths;
	private double emiAmount;
	
	static {
		loanInterestRate=12;
	}
	public LoanAccount(String holderName, String mobileNo, String accountType,
			String holderCity,double loanAmount, int tenureInMonths) {
		super(holderName, mobileNo, accountType, holderCity);
		this.loanAmount=loanAmount;
		this.tenureInMonths=tenureInMonths;
		this.emiAmount=(this.loanAmount + this.calculateInterest())/tenureInMonths;
	}

	public double getLoanInterestRate() {
		return loanInterestRate;
	}

	public void setLoanInterestRate(double loanInterestRate) {
		LoanAccount.loanInterestRate = loanInterestRate;
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
	public boolean withdraw(double amount) {
		// TODO Auto-generated method stub
		
		if(this.getBalance()>=amount)
		{
			setBalance(getBalance()-amount);
			getTransactions().add(new Transaction("Withdraw", getBalance(), amount));
			return true;
		}
		return false;
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		super.display();
		System.out.printf("%-25s : %.2f%n", "Loan Amount", this.loanAmount);
		System.out.printf("%-25s : %d%n", "Tenure in Month", this.tenureInMonths);
		System.out.printf("%-25s : %.2f%n", "EMI Amount", this.emiAmount);
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
