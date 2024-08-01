import java.util.Scanner;

public class ATM {
	
	public static void main(String[] args) {
		
		//init Scanner
		Scanner sc = new Scanner(System.in);
		
		//init Bank
		Bank theBank = new Bank("State Bank Of India");
		
		//add a user, which also creates a saving account
		User aUser = theBank.addUser("Vignesh","V J", "1234");
		
		//add a checking account for our user
		Account newAccount = new Account("Checking", aUser , theBank);
		aUser.addAccount(newAccount);
		theBank.addAccount(newAccount);

        User curUser;
        while (true) { 
            //stay in the login prompt until successfully login
            curUser = ATM.mainMenuPrompt(theBank, sc);

            //stay in main menu untill user quite
            ATM.printUserMenu(curUser , sc);
        }
	}
    
    /**
     * 
     * @param theBank
     * @param sc
     * @return
     */
    public static User mainMenuPrompt(Bank theBank, Scanner sc) {
        //inits
        String userID;
        String pin;
        User authUser;

        //prompt the user for user ID/Pin compbo until a correct one reached
        do { 
         System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
         System.out.print("Enter user ID: ");
         userID = sc.nextLine();
         System.out.print("Enter pin: ");
         pin = sc.nextLine();

         //try to get the user object corresponding tothe Id and Pin combo
         authUser = theBank.userLogin(userID, pin);
         if(authUser == null) {
            System.out.println("Incorrect user ID or pin. Please try again.\n");
         }
        } while(authUser == null); //continue looping untill successful login
        return authUser;
    }
    public static void printUserMenu(User theUser, Scanner sc) {

        //print a summary of the user's accounts
        theUser.printAccountSummary();

        //int
        int choice;

        //user menu
        do { 
            System.out.printf("Welcome %s, what would you like to do ?\n", theUser.getFirstNAme());
            System.out.println("  1) Show account Transaction history");
            System.out.println("  2) Withdrawl");
            System.out.println("  3) Deposit");
            System.out.println("  4) Transfer");
            System.out.println("  5) Quit");
            System.out.println("Enter Choice");
            choice = sc.nextInt();

            if(choice < 1 || choice > 5) {
                System.out.println("Invalid choice, Please Choose 1-5");
            }
        }  while(choice < 1 || choice >5);

        //process the choice
        switch(choice) {
            
            case 1:
            ATM.showTransHistory(theUser , sc);
            break;

            case 2:
            ATM.withdrawFunds(theUser , sc);
            break;

            case 3:
            ATM.depositFunds(theUser , sc);
            break;

            case 4:
            ATM.transferFunds(theUser , sc);
            break;
            case 5:
               //gobble up rest of previous input
        sc.nextLine();
        break;
        }

        //redisplay this menu unless the user wants to quit
        if (choice != 5) {
        ATM.printUserMenu(theUser, sc);
    }
}

/**
 * Show the transaction history for an account
 * @param theUser the logged- in user object
 * @param sc the Scanner object used for user
 */
public static void showTransHistory(User theUser, Scanner sc) {
    int theAcct;

    //get account whose transaction history to look at
    do{
        System.out.printf("Enter the Number(1-%d) of the account" +"whose transactions you want to see: ", theUser.numAccounts());
        theAcct = sc.nextInt()-1;
        if(theAcct < 0 || theAcct >= theUser.numAccounts()) {
            System.err.println("Invalid account. Please try again Later ");
        }
    }while(theAcct < 0 || theAcct >= theUser.numAccounts());

    //print the transaction history
    theUser.printAcctTransHistory(theAcct);
}

/**
 * Process transferring funds from account to another
 * @param theUser theUser the logged in User object
 * @param sc the Scanner object used for user Input
 */
public static void transferFunds(User theUser, Scanner sc) {
    //inits
    int fromAcct;
    int toAcct;
    double amount;
    double acctBal;

    //get the account to transfer from
    do {
        System.out.printf("Enter the Number(1-%d) of the account\n" + "to transfer from: ",theUser.numAccounts());
        fromAcct = sc.nextInt()-1;
        if(fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
            System.err.println("Invalid account. Please try again Later ");
        }
    }while(fromAcct < 0 || fromAcct >= theUser.numAccounts());
    acctBal = theUser.getAcctBalance(fromAcct);

    //get the account to transfer to

    do {
        System.out.printf("Enter the Number(1-%d) of the account\n" + "to transfer to: ", theUser.numAccounts());
        toAcct = sc.nextInt()-1;
        if(toAcct < 0 || toAcct >= theUser.numAccounts()) {
            System.err.println("Invalid account. Please try again Later ");
        }
    }while(toAcct < 0 || toAcct >= theUser.numAccounts());

    //get the amount to transfer
    do { 
        System.out.printf("Enter the amount to transfer  (max Rs%.02f): Rs", acctBal);
        amount = sc.nextDouble();
        if(amount < 0) {
            System.out.printf("Amount must be greater than 0 ");
        }
        else if(amount > acctBal) {
            System.out.printf("Amount must not be greater than\n" + "Balance of Rs%0.2f.\n", acctBal);
        }
    } while(amount < 0 || amount > acctBal);

    //finally, do the transfer
    theUser.addAcctTransaction(fromAcct, -1*amount, String.format("Transfer to account %s", theUser.getAcctUUID(toAcct)));
    theUser.addAcctTransaction(toAcct, amount, String.format("Transfer to account %s", theUser.getAcctUUID(fromAcct)));

}

/**
 * process a fund withdraw from an account
 * @param theUser the logged in user object
 * @param sc the scanner object user for user input
 */
public static void withdrawFunds(User theUser, Scanner sc) {


      //inits
      int fromAcct;
      double amount;
      double acctBal;
      String memo;
  
      //get the account to transfer from
      do {
          System.out.printf("Enter the Number(1-%d) of the account\n" + "to withdraw from: ", theUser.numAccounts());
          fromAcct = sc.nextInt()-1;
          if(fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
              System.err.println("Invalid account. Please try again Later ");
          }
      }while(fromAcct < 0 || fromAcct >= theUser.numAccounts());
      acctBal = theUser.getAcctBalance(fromAcct);

         //get the amount to transfer
    do { 
        System.out.printf("Enter the amount to wiythdraw  (max Rs%.02f): Rs", acctBal);
        amount = sc.nextDouble();
        if(amount < 0) {
            System.out.printf("Amount must be greater than 0 ");
        }
        else if(amount > acctBal) {
            System.out.printf("Amount must not be greater than\n" + "Balance of Rs%0.2f.\n", acctBal);
        }
    } while(amount < 0 || amount > acctBal);

    //gobble up rest of previous input
    sc.nextLine();

    //get a memo
    System.out.println("Enter a memo : ");
    memo = sc.nextLine();

    // do the withdrawl
    theUser.addAcctTransaction(fromAcct, -1*amount, memo);
}

/**
 * Process a fund deposit to an account
 * @param theUser theUser the logged in user object
 * @param sc the Scanner object used for user input
 */
public static void depositFunds(User theUser, Scanner sc) {
          //inits
          int toAcct;
          double amount;
          double acctBal;
          String memo;
      
          //get the account to transfer from
          do {
              System.out.printf("Enter the Number (1-%d) of the account\n" + "to deposit in: ", theUser.numAccounts());
              toAcct = sc.nextInt()-1;
              if(toAcct < 0 || toAcct >= theUser.numAccounts()) {
                  System.err.println("Invalid account. Please try again Later ");
              }
          }while(toAcct < 0 || toAcct >= theUser.numAccounts());
          acctBal = theUser.getAcctBalance(toAcct);
    
             //get the amount to transfer
        do { 
            System.out.printf("Enter the amount to transfer  (max Rs%.02f): Rs", acctBal);
            amount = sc.nextDouble();
            if(amount < 0) {
                System.out.printf("Amount must be greater than 0 ");
            }

        } while(amount < 0);
    
        //gobble up rest of previous input
        sc.nextLine();
    
        //get a memo
        System.out.print("Enter a memo : ");
        memo = sc.nextLine();
    
        // do the withdrawl
        theUser.addAcctTransaction(toAcct, amount, memo);
}
}
