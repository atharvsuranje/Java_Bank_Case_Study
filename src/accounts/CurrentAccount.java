package accounts;

import java.time.LocalDate;

public class CurrentAccount extends BankAccount{
	private double overDraftLimit;

	public CurrentAccount(int accNo, String holderName, String mobileNo, String accountType, double balance,
			String holderCity, LocalDate openingDate,double overDraftLimit) {
		super(accNo, holderName, mobileNo, accountType, balance, holderCity, openingDate);
		this.overDraftLimit=overDraftLimit;
	}

	public double getOverDraftLimit() {
		return overDraftLimit;
	}

	public void setOverDraftLimit(double overDraftLimit) {
		this.overDraftLimit = overDraftLimit;
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
