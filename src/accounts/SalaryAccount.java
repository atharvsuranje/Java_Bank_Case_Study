package accounts;

import java.time.LocalDate;

public class SalaryAccount extends BankAccount{
	private LocalDate lastTranscationDate;
	private boolean isFrozen;
	private String employerName;
	private static double interestRate;
		
	static {
		interestRate=0.5;
	}
	
	public SalaryAccount(int accNo, String holderName, String mobileNo, String accountType, double balance,String holderCity, LocalDate openingDate, LocalDate lastTranscationDate, boolean isFrozen,String employerName) {
		super(accNo, holderName, mobileNo, accountType, balance, holderCity, openingDate);
		this.lastTranscationDate = lastTranscationDate;
		this.isFrozen = isFrozen;
		this.employerName = employerName;
	}

	public LocalDate getLastTranscationDate() {
		return lastTranscationDate;
	}

	public void setLastTranscationDate(LocalDate lastTranscationDate) {
		this.lastTranscationDate = lastTranscationDate;
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

	@Override
	public void withdraw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}
	
}
