
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
public class User {
	
	
	private String firstName;

	private String lastName;
	
	private String uuid;
	
	/** The MD5 hash of the user's pin number */
	private byte pinHash[];
	
	/** List of accounts for this user */
	private  ArrayList<Account> accounts;

	public Object numAccounts;
	
	/** 
	 * Create a new user
	 * @param firstName the user's first Name
	 * @param lastName  the user's last name
	 * @param pin the user's account pin number
	 * @param theBank the Bank object that the user 
	 */
	
	public User(String firstName, String lastName, String pin, Bank theBank)
	{
		//set user's name
		this.setFirstName(firstName);
		this.setLastName(lastName);
		
		// store the pin's MD5 hash,  rather than the original value, for
		//security reason
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());
		} 		catch (NoSuchAlgorithmException e)  {
					//TODO Auto-generated catch block
					System.out.println("error, caught NoSuchAlgorithmException");
					e.printStackTrace();
					System.exit(1);
		}
		
		//get a new, unique universal Id for the user
		this.uuid = theBank.getNewUserUUID();	
		
		//create empty list of accounts
		this.accounts = new ArrayList<Account>();
		
		//print log message
		System.out.printf("New User %s, %s with ID %s created .\n", firstName,lastName, this.uuid);
		
	}
	/**
	 * Add an account for the user
	 * @param anAcct the account to add
	 */
	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}
	/**
	 * Return the user's UUID
	 * @return the uuid
	 */
	public String getUUID() {
		return firstName;
		/**
		 * Check whether a given pin matches the true User pin
		 */
	}
	
	public boolean validatePin(String aPin) {

		try {
		MessageDigest md = MessageDigest.getInstance("MD5");
		return MessageDigest.isEqual(md.digest(aPin.getBytes()),
				this.pinHash);
		} catch (NoSuchAlgorithmException e) {
			System.err.println("error, caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);	
		}
		
		return false;
	}

	/**
	 * Return the User First name
	 * @return
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Print Summaries for the accounts of this user.
	 */
	public void printAccountSummary() {
		System.out.printf("\n\n%s's accounts summary\n", this.firstName);
		for(int a = 0; a< this.accounts.size(); a++) {
			System.out.printf("   %d) %s\n", a+1, this.accounts.get(a).getSummaryLine());
		}
		System.out.println();
	}

	/**
	 * Get the number of accounts of the user
	 * @return the number of accounts
	 */
	public int numAccounts() {
		return this.accounts.size();
	}
	/**
	 * Print transaction history for a particular account
	 * @param acctIdx the index of the account to use
	 */


	public void printAcctTransHistory(int acctIdx) {
		this.accounts.get(acctIdx).printTransHistory();
	}

	/**
	 * Get the balance of a particular account
	 * @param acctIdx the index of the account to use
	 * @return the balance of the account
	 */
	public double getAcctBalance(int acctIdx) {
		return this.accounts.get(acctIdx).getBalance();
	}

	/**
	 * Get the UUID of a particular account 
	 * @param acctIdx the index of the account to use
	 * @returnthe UUID of the account 
	 */
	public String getAcctUUID(int acctIdx) {
		return this.accounts.get(acctIdx).getUUID();
	}

	/**
	 * Add a transaction to a particular account
	 * @param acctIdx the index of the account
	 * @param amount the amount of the transaction
	 * @param memo the memo of the transaction
	 */
	public void addAcctTransaction(int acctIdx, double amount, String memo) {
		this.accounts.get(acctIdx).addTransaction(amount, memo);
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

    Object[] getFirstNAme() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
	


