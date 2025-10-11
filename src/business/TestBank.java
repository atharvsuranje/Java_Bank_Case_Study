package business;

import java.util.Scanner;

import accounts.SavingAccount;
import helpers.Bank;


public class TestBank {

    public static void main(String[] args) {
    	Bank bank=new Bank();
        Scanner sc = new Scanner(System.in);
        int mainChoice, subChoice;
        boolean exit = false;

         do{
            System.out.println("\n===== Bank Management System =====");
            System.out.println("1. Over the Counter Activities");
            System.out.println("2. Managerial Activities");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            mainChoice = sc.nextInt();

            switch (mainChoice) {
                case 1:
                    System.out.println("\n--- Over the Counter Activities ---");
                    System.out.println("1. Open Account");
                    System.out.println("2. Close Account");
                    System.out.println("3. Withdraw Money");
                    System.out.println("4. Deposit Money");
                    System.out.println("5. Transfer Money");
                    System.out.println("6. Calculate Interest");
                    System.out.println("7. Display All Transactions for an Account Holder");
                    System.out.print("Enter your choice: ");
                    subChoice = sc.nextInt();

                    switch (subChoice) {
                    case 1:  // Open Account
                        int accountType=0,tenure=0;
                        String holderName="", mobileNo="", holderCity="",employerName="";
                        double overDraftLimit=0.0,loanAmount=0.0;
                        boolean cancelProcedure = false;

                        do {
                            System.out.println("\nOpening Account Menu");
                            System.out.println(" 1. Saving Account.");
                            System.out.println(" 2. Salary Account.");
                            System.out.println(" 3. Current Account.");
                            System.out.println(" 4. Loan Account.");
                            System.out.println(" 5. Cancel Procedure.");
                            System.out.print("Enter Selected Account : ");
                            accountType = sc.nextInt();
                            sc.nextLine(); // consume newline

                            if(accountType == 5) { 
                                System.out.println("Procedure Cancelled!");
                                cancelProcedure = true;
                                break;
                            }

                            char choice = 'N';
                            if(accountType == 1) {
                                System.out.println("Minimum Balance Limit is : " + SavingAccount.getMinimumBalance());
                                System.out.print("Do you want to proceed (Y/N) : ");
                                choice = sc.next().charAt(0);
                                sc.nextLine(); 
                                if(choice == 'N' || choice == 'n') {
                                    System.out.println("Procedure Cancelled!");
                                    cancelProcedure = true;
                                    break;
                                }
                            }
                            
                            else if(accountType == 2) {
                            	System.out.print("Enter Employer Name : ");
                            	employerName=sc.nextLine();
                            	cancelProcedure=false;
                            }
                            
                            else if(accountType == 3){
                            	double income;
                            	System.out.print("Enter Annual Income :");
                            	income=sc.nextDouble();
                            	if(income>=100000 && income <=500000) overDraftLimit=10000;
                            	else if(income >500000 && income <= 1500000) overDraftLimit=20000;
                            	else if(income >1500000) overDraftLimit=30000;
                            	else overDraftLimit=1000;
                            	cancelProcedure=false;
                            }
                            
                            else {
                            	double creditScore;
                            	System.out.print("Enter Credit Score :");
                            	creditScore=sc.nextDouble();
                            	if(creditScore > 0 && creditScore <= 650) loanAmount=200000;
                            	else if(creditScore >650 && creditScore <= 749) loanAmount=300000;
                            	else loanAmount=500000;
                            	System.out.print("Enter Tenure in Months :");
                            	tenure=sc.nextInt();
                            	sc.nextLine();
                            	cancelProcedure=false;
                            	
                            }

                            if(cancelProcedure == false) {
                                System.out.print("Enter Account Holder Name : ");
                                holderName = sc.nextLine();
                                System.out.print("Enter Account Holder Mobile Number : ");
                                mobileNo = sc.nextLine();
                                System.out.print("Enter Account Holder City : ");
                                holderCity = sc.nextLine();
                            }
                            
                            switch(accountType) {
                            	case 1:
                            		if(bank.openAccount(holderName,mobileNo,"Saving",holderCity))
                            			System.out.println("Account Opened Successfully!!!");
                            		break;
                            	case 2:
                            		if(bank.openAccount(holderName,mobileNo,"Salary",holderCity,employerName))
                            			System.out.println("Account Opened Successfully!!!");
                            		break;
                            	case 3:
                            		if(bank.openAccount(holderName,mobileNo,"Current",holderCity,overDraftLimit))
                            			System.out.println("Account Opened Successfully!!!");
                            		break;
                            	case 4:
                            		if(bank.openAccount(holderName,mobileNo,"Loan",holderCity,loanAmount,tenure))
                            			System.out.println("Account Opened Successfully!!!");
                            		break;
                            }

                        } while(!cancelProcedure);
                        break;

                        case 2:
                        	char choice='N';
                        	do {
                    			System.out.print("Enter Account Number : ");
                    			int accNo=sc.nextInt();
                    			if(bank.closeAccount(accNo)) {
                    				System.out.println("Account Closed Successfully!!!");
                    				cancelProcedure=true;                        				
                    			}
                    			else {
                    				System.out.print("Do you want to proceed (Y/N) : ");
                    				choice=sc.next().charAt(0);
                    			}
                    		}
                    		while(choice == 'Y' || choice =='y');
                            		
                        			
                        case 3:
                            System.out.println("Withdrawing Money...");
                            // withdrawMoney()
                            break;
                        case 4:
                            // depositMoney()
                            System.out.println("Depositing Money...");
                            break;
                        case 5:
                            // transferMoney()
                            System.out.println("Transferring Money...");
                            break;
                        case 6:
                            // interest()
                            System.out.println("Calculating Interest...");
                            break;
                        case 7:
                            // displayAllTransactions()
                            System.out.println("Displaying Transactions...");
                            break;
                        default:
                            System.out.println("Invalid choice!");
                    }
                    break;

                case 2:
                    System.out.println("\n--- Managerial Activities ---");
                    System.out.println("1. Generate Bank Report");
                    System.out.println("2. Display All Accounts");
                    System.out.print("Enter your choice: ");
                    subChoice = sc.nextInt();

                    switch (subChoice) {
                        case 1:
                            // generateBankReport()
                            System.out.println("Generating Bank Report...");
                            break;
                        case 2:
                            // displayAllAccounts()
                            System.out.println("Displaying All Accounts...");
                            break;
                        default:
                            System.out.println("Invalid choice!");
                    }
                    break;

                case 3:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid main choice!");
            }
        }
        while(mainChoice != 3);

        sc.close();
    }

}