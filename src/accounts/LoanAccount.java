package accounts;

import helpers.Transaction;

public class LoanAccount extends BankAccount {

    private double loanAmount;          
    private static double loanInterestRate; 
    private int tenureInMonths;         
    private double emiAmount;           
    private double totalPayable;     

    static {
        loanInterestRate = 12; // 12% per annum
    }

    public LoanAccount(String holderName, String mobileNo, String accountType,
                       String holderCity, double loanAmount, int tenureInMonths) {
        super(holderName, mobileNo, accountType, holderCity);
        this.loanAmount = loanAmount;
        this.tenureInMonths = tenureInMonths;

        double totalInterest = this.loanAmount * (loanInterestRate / 100.0);

        this.totalPayable = this.loanAmount + totalInterest;

        this.emiAmount = totalPayable / tenureInMonths;

        super.deposit(this.loanAmount);
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

    public int getTenureInMonths() {
        return tenureInMonths;
    }

    public double getEmiAmount() {
        return emiAmount;
    }

    public double getTotalPayable() {
        return totalPayable;
    }

    @Override
    public boolean withdraw(double amount) {
        if (this.getBalance() >= amount) {
            setBalance(getBalance() - amount);
            getTransactions().add(new Transaction("Withdraw", getBalance(), amount));
            return true;
        }
        return false;
    }

    public boolean payEmi(double amount) {
        if (amount >= emiAmount && totalPayable > 0) {
            totalPayable -= amount;
            getTransactions().add(new Transaction("EMI Paid", getBalance(), amount));
            return true;
        }
        return false;
    }

    @Override
    public void display() {
        super.display();
        System.out.printf("%-25s : %.2f%n", "Loan Amount", this.loanAmount);
        System.out.printf("%-25s : %d%n", "Tenure in Months", this.tenureInMonths);
        System.out.printf("%-25s : %.2f%n", "EMI Amount", this.emiAmount);
        System.out.printf("%-25s : %.2f%n", "Total Payable", this.totalPayable);
    }

    public double calculateInterest() {
        return loanAmount * (loanInterestRate / 100.0);
    }
}
