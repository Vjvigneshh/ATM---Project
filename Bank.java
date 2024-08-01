import java.util.ArrayList;

public class Bank {
    
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;
    
    /**
     * Create a Bank Object with empty lists of users and accounts 
     * @param name the name of the bank
     */
    public Bank(String name) {
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }
    
    /**
     * Generate a new universally unique ID for a user
     * @return a new UUID for a user
     */
    public String getNewUserUUID() {
        // Implement UUID generation logic here
        return "";
    }
    
    /**
     * Generate a new universally unique ID for an account
     * @return a new UUID for an account
     */
    public String getNewAccountUUID() {
        // Implement UUID generation logic here
        return "";
    }
    
    /**
     * Add an account to the bank
     * @param anAcct the account to add
     */
    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }
    
    /**
     * Create a new user of the bank
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param pin the user's PIN
     * @return the new User object
     */
    public User addUser(String firstName, String lastName, String pin) {
        // Create a new user object and add it to our list
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);
        
        // Create a savings account for the user and add to User and Bank accounts lists
        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);
        
        return newUser;
    }
    
    /**
     * Perform user login based on user ID and PIN
     * @param userID the user ID of the user
     * @param pin the PIN of the user
     * @return the User object if login successful, null otherwise
     */
    public User userLogin(String userID, String pin) {
        // Search through the list of users
        for (User u : this.users) {
            // Check if user ID and PIN match
            if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
                return u;
            }
        }
        // If we haven't found the user or have an incorrect PIN
        return null;
    }

    public String getName(){
        return this.name;
    }
}
