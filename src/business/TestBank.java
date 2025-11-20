package business;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import accounts.BankAccount;
import accounts.CurrentAccount;
import accounts.LoanAccount;
import accounts.SalaryAccount;
import accounts.SavingAccount;
import helpers.Bank;


public class TestBank {

	public static void saveData(Bank bank) {
	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("bankData.ser"))) {
	        oos.writeObject(bank.getAccounts());
	        System.out.println("\n Data Saved Successfully!");
	    } catch (Exception e) {
	        System.out.println("Error Saving Data: " + e.getMessage());
	    }
	}

	@SuppressWarnings("unchecked")
	public static boolean loadData(Bank bank) {
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("bankData.ser"))) {
	        ArrayList<BankAccount> list = (ArrayList<BankAccount>) ois.readObject();
	        bank.setAccounts(list);
	        System.out.println("\n Previous Data Loaded Successfully!");
	        return true;
	    } catch (Exception e) {
	        System.out.println("No Previous Data Found. Starting Fresh...");
	        return false;
	    }
	}

	
    public static void main(String[] args) {
    	Bank bank=new Bank();
    	boolean dataLoaded=loadData(bank);
    	
    	if(!dataLoaded) {
    		bank.initializeDefaultAccounts();
    		bank.initializeDefaultTransactions();
    	}
    	
    	Scanner sc = new Scanner(System.in);
        int mainChoice, subChoice;
        System.out.println("--------------------------------------------------------------");

         do{
            System.out.println("\n===== Bank Management System =====");
            System.out.println("1. Over the Counter Activities");
            System.out.println("2. Managerial Activities");
            System.out.println("3. Save Data to File");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            mainChoice = sc.nextInt();
            System.out.println("--------------------------------------------------------------");
            switch (mainChoice) {
                case 1:
                    System.out.println("\n--- Over the Counter Activities ---");
                    System.out.println("1. Open Account");
                    System.out.println("2. Close Account");
                    System.out.println("3. Withdraw Money");
                    System.out.println("4. Deposit Money");
                    System.out.println("5. Transfer Money");
                    System.out.println("6. Pay EMI");
                    System.out.println("7. Display All Transactions for an Account Holder");
                    System.out.println("8. Exit");
                    System.out.print("Enter your choice: ");
                    subChoice = sc.nextInt();
                    System.out.println("--------------------------------------------------------------");
                    switch (subChoice) {
                    case 1:  // Open Account
                        int accountType=0,tenure=0, accountNo=0;
                        String holderName="", mobileNo="", holderCity="",employerName="";
                        double overDraftLimit=0.0,loanAmount=0.0,amount=0.0;
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
                                System.out.println("--------------------------------------------------------------");
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
                            break;

                        } while(!cancelProcedure);
                        break;

                        case 2:
                        	char choice='N';
                        	do {
                    			System.out.print("Enter Account Number : ");
                    			int accNo=sc.nextInt();
                    			if(bank.closeAccount(accNo)) {
                    				System.out.println("Account Closed Successfully!!!");
                    			}
                    			else {
                    				System.out.println("Account Not Found !!");
                    				System.out.print("Do you want to proceed (Y/N) : ");
                    				choice=sc.next().charAt(0);
                    			}
                    		}
                    		while(choice == 'Y' || choice =='y');
                        	break;
                            		
                        			
                        case 3:
                        	do {
                        		choice='N';
                        		System.out.println("Withdrawing Money...");
                                System.out.print("Enter the Account NO : ");
                                accountNo=sc.nextInt();
                                sc.nextLine();
                                BankAccount bankAccount=bank.searchAccountbyAccountNo(accountNo);
                                if(bankAccount!=null) {
                                	if(!bankAccount.getAccountStatus()) {
                                		System.out.println("Amount Is Closed.");
                                		System.out.print("Do you want to Reopen it? (Y/N) : ");
                        				choice=sc.next().charAt(0);
                        				if(choice == 'Y' || choice == 'y') {
                        					bankAccount.setAccountStatus(true);
                        					System.out.println("This Account is Reopened!!!");
                        				}
                        				else break;
                                	}
                                	System.out.print("Enter the Amount : ");
                                	amount=sc.nextDouble();
                                	if(bank.withdrawMoney(bankAccount,amount)) {
                                		System.out.println("Amount Withdrawn Successfully.");
                                	}
                                	else {
                                		if(bankAccount instanceof SavingAccount) {
                                			if(bankAccount.getBalance() < amount)
                                				System.out.println("Withdrawal Amount is Greater than Account Balance !!");
                                			else 
                                				System.out.println("Minimum Balance amount Requirement not Satisfied ");	
                                		}
                                		else if(bankAccount instanceof SalaryAccount){
                                			if(bankAccount.getBalance() < amount)
                                				System.out.println("Withdrawal Amount is Greater than Account Balance !!");
                                			else {
                                				System.out.println("Your account is frozen due to inactivity for more than 2 months");
                                				int freezeChoice;
                                				do {
                                					System.out.println(" 1. Unfreeze This Account By Paying Penalty of Rs. 500.");
                                					System.out.println(" 2. Exit.");
                                					System.out.print("Do you want to unfreeze it ? : ");
                                					freezeChoice=sc.nextInt();
                                					
                                					switch(freezeChoice) {
                                						case 1:
                                							((SalaryAccount) bankAccount).setFrozen(false);
                                							System.out.println("Account is Unfrozen!!");
                                							break;
                                						case 2:
                                							System.out.println("Account Remains Frozen!!!");
                                							break;
                                					}
                                				}while(freezeChoice!=2);
                                			}
                                		}
                                		else if(bankAccount instanceof CurrentAccount) {
                                			System.out.println("Withdrawal Amount is Greater than Account Balance and Over Draft Limit!!");
                                		}
                                		else {
                                			System.out.println("Withdrawal Amount is Greater than Account Balance !!");
                                		}
                                	}
                                }
                                else {
                                	System.out.println("Account Not Found !!");
                    				System.out.print("Do you want to proceed (Y/N) : ");
                    				choice=sc.next().charAt(0);
                                }
                        	}
                        	while(choice == 'Y' || choice =='y');
                        	 System.out.println("--------------------------------------------------------------");
                        	break;  
                        case 4:
                            // depositMoney()
                            System.out.println("Depositing Money...");
                            System.out.print("Enter the Account NO : ");
                            accountNo=sc.nextInt();
                            sc.nextLine();
                            BankAccount bankAccount=bank.searchAccountbyAccountNo(accountNo);
                            if(bankAccount!=null) {
                            	if(!bankAccount.getAccountStatus()) {
                            		System.out.println("Account is Closed!!");
                            		System.out.print("Do you want to Reopen it? (Y/N) : ");
                    				choice=sc.next().charAt(0);
                    				if(choice == 'Y' || choice == 'y') {
                    					bankAccount.setAccountStatus(true);
                    					System.out.println("This Account is Reopened!!!");
                    				}
                    				else break;
                            	}
                            	System.out.print("Enter the Amount : ");
                            	amount=sc.nextDouble();
                            	if(bank.depositMoney(bankAccount,amount)) {
                            		System.out.println("Amount Deposited Successfully.");
                            	}
                            	else {
                        				System.out.println("Your account is frozen due to inactivity for more than 2 months");
                        				int freezeChoice;
                        				do {
                        					System.out.println(" 1. Unfreeze This Account By Paying Penalty of Rs. 500.");
                        					System.out.println(" 2. Exit.");
                        					System.out.print("Do you want to unfreeze it ? : ");
                        					freezeChoice=sc.nextInt();
                        					
                        					switch(freezeChoice) {
                        						case 1:
                        							((SalaryAccount) bankAccount).setFrozen(false);
                        							System.out.println("Account is Unfrozen!!");
                        							break;
                        						case 2:
                        							System.out.println("Account Remains Frozen!!!");
                        							break;
                        					}
                        				}while(freezeChoice!=2);
                        			}
                            	}
                            else System.out.println("Account Not Found");
                            System.out.println("--------------------------------------------------------------");
                            break;
                        case 5:
                            System.out.println("Transferring Money...");
                            System.out.print("Enter the Sender Account NO : ");
                            accountNo=sc.nextInt();
                            int rAccountNo;
                            BankAccount sender=bank.searchAccountbyAccountNo(accountNo);
                            if(sender != null) {
                            	if(!sender.getAccountStatus()) {
                            		System.out.println("Sender Account Is Closed.");
                            		System.out.print("Do you want to Reopen it? (Y/N) : ");
                    				choice=sc.next().charAt(0);
                    				if(choice == 'Y' || choice == 'y') {
                    					sender.setAccountStatus(true);
                    					System.out.println("This Account is Reopened!!!");
                    				}
                    				else break;
                            	}
                            	System.out.print("Enter the Reciever Account NO : ");
                                rAccountNo=sc.nextInt();
                                BankAccount reciever=bank.searchAccountbyAccountNo(rAccountNo);
                                if(reciever != null) {
                                	if(!sender.getAccountStatus()) {
                                		System.out.println("Reciever Account Is Closed.");
                                		break;
                                	}
                                	System.out.print("Enter the Amount to Transfer : ");
                                	amount=sc.nextDouble();
                                	if(bank.transferMoney(sender,reciever,amount)) {
                                		System.out.println("Transfer Successful!!!");
                                	}
                                	else {
                                		System.out.println("Some Problem Occurred");
                                	}
                                	
                                }
                                else {
                                	System.out.println("Reciever Not Found!!!");
                                	break;
                                }
                            }
                            else {
                            	System.out.println("Sender Not Found!!!");
                            	break;
                            }
                            System.out.println("--------------------------------------------------------------");
                            break;
                        case 6:
                            System.out.println("Paying EMI.....");
                            System.out.print("Enter the Account NO : ");
                            accountNo=sc.nextInt();
                            bankAccount =bank.searchAccountbyAccountNo(accountNo);
                            if(bankAccount != null) {
                            	if(bankAccount instanceof LoanAccount) {
                            		LoanAccount bankAccount1 = (LoanAccount) bankAccount;
                            		if(bank.payEmi(bankAccount1,bankAccount1.getEmiAmount())) {
                            			System.out.println("EMI Paid!!!");
                            			if(bankAccount1.getTotalPayable() <=0) {
                            				System.out.println("Loan is Cleared!!!");
                            			}
                            		}
                            	}
                            	else {
                            		System.out.println("Account is Not a Loan Account!!!");
                            		break;
                            	}
                            }
                            else {
                            	System.out.println("Account Not FOund!!!");
                            	break;
                            }
                            System.out.println("--------------------------------------------------------------");
                            break;
                        case 7:
                        	System.out.print("Enter the Account NO : ");
                            accountNo=sc.nextInt();
                            bankAccount =bank.searchAccountbyAccountNo(accountNo);
                            if(bankAccount!= null) {
                            	System.out.println("Displaying Transactions...");
                            	bank.displayTransactions(bankAccount);
                            	break;
                            }
                            System.out.println("Account Not Found!!!");
                            System.out.println("--------------------------------------------------------------");
                            break;
                            
                        case 8:
                        	System.out.println("Exiting Over the Counter Activities...");
                        	System.out.println("--------------------------------------------------------------");
                        	break;
                        default:
                            System.out.println("Invalid choice!");
                            System.out.println("--------------------------------------------------------------");
                    }
                    System.out.println("--------------------------------------------------------------");
                    break;

                case 2:
                    System.out.println("\n--- Managerial Activities ---");
                    System.out.println("1. Generate Bank Report");
                    System.out.println("2. Display All Accounts");
                    System.out.println("3. Calculate Interest on All Accounts");
                    System.out.println("4. Exit.");
                    System.out.print("Enter your choice: ");
                    subChoice = sc.nextInt();

                    switch (subChoice) {
                        case 1:
                            System.out.println("Generating Bank Report...");
                            bank.generateBankReport(bank.getAccounts());
                            System.out.println("--------------------------------------------------------------");
                            break;
                        case 2:
                            System.out.println("Displaying All Accounts...");
                            bank.displayAllAccounts();
                            System.out.println("--------------------------------------------------------------");
                            break;
                            
                        case 3:
                        	System.out.println("Calculating Interest...");
                            for(BankAccount bankAccount2 : bank.getAccounts()) {
                            	if(bankAccount2.getAccountStatus()) {
                            		bankAccount2.setBalance(bankAccount2.getBalance() + bankAccount2.calculateIntrest());
                            	}
                            }
                            System.out.println("--------------------------------------------------------------");
                            break;
                        default:
                            System.out.println("Invalid choice!");
                            System.out.println("--------------------------------------------------------------");
                    }
                    System.out.println("--------------------------------------------------------------");
                    break;

                case 3:{
                	saveData(bank);
                    break;
                }
                    
                case 4:
                	System.out.println("Saving data before exit...");
                    saveData(bank);
                    System.out.println("Exiting... Thank you!");
                    System.out.println("--------------------------------------------------------------");
                    break;

                default:
                    System.out.println("Invalid main choice!");
                    System.out.println("--------------------------------------------------------------");
            }
        }
        while(mainChoice != 4);

        sc.close();
    }

}