
import java.util.Date;

public class Transaction {
	
	private double amount;
	
	private Date timestamp;
	
	private String memo;
	
	/** The account in which */
	private Account inAccount;
	
	public Transaction(double amount, Account inAccount) {
		this.setAmount(amount);
		this.setInAccount(inAccount);
		this.setTimestamp(new Date());
		this.setMemo("");
	}
	
	/**
	 * Create a new transaction
	 * @param amount the amount transacted
	 * @param inAccountthe memo for the transaction
	 * @param inAccount the account the transaction belongs to
	 */
	
	public Transaction(double amount, String memo, Account inAccount) {
		//call the two-arg constructor first
		this(amount, inAccount);
		
		//see the memo
		this.setMemo(memo);
	}

	/**
	 * Get the amount of the transaction
	 * @return the amount
	 */
	public double getAmount() {
		return this.amount;
	}

	/**
	 * Get a String summarizing the Transaction
	 * @return the Summary String
	 */
	public String getSummaryLine(){
		if (this.amount >=0) {
			return String.format("%s: Rs%.02f %s", this.timestamp().toString(), this.amount, this.memo);
		}
		else {
			return String.format("%s: Rs(%.02f) %s", this.timestamp().toString(),-this.amount, this.memo);
		}
	}


	private Object timestamp() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'timestamp'");
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Account getInAccount() {
		return this.inAccount;
	}

	public void setInAccount(Account inAccount) {
		this.inAccount = inAccount;
	}
}
