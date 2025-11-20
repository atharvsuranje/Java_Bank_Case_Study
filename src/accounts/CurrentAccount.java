package accounts;

import helpers.Transaction;

public class CurrentAccount extends BankAccount{
	private double overDraftLimit;
	private static final long serialVersionUID = 1L;

	public CurrentAccount(String holderName, String mobileNo, String accountType,String holderCity,double overDraftLimit) {
		super(holderName, mobileNo, accountType, holderCity);
		this.overDraftLimit=overDraftLimit;
	}

	public double getOverDraftLimit() {
		return overDraftLimit;
	}

	public void setOverDraftLimit(double overDraftLimit) {
		this.overDraftLimit = overDraftLimit;
	}

	@Override
	public boolean withdraw(double amount) {
		// TODO Auto-generated method stub
		if(amount<=getBalance()+this.overDraftLimit) {
			if(amount > getBalance()) {
				setOverDraftLimit(getOverDraftLimit()-(amount-getBalance()));
				setBalance(0);
				getTransactions().add(new Transaction("Withdraw",getBalance(),amount));
				return true;
			}
			else {
				setBalance(getBalance()-amount);
				getTransactions().add(new Transaction("Withdraw",getBalance(),amount));
				return true;
				
			}
		}
		
		return false;
		
	}

	@Override
	public void display() {
		super.display();
		System.out.printf("%-25s : %.2f%n", "OverDraft Limit", this.overDraftLimit);
	}
	
	
}
