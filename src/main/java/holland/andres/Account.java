package holland.andres;

public class Account {

    private String accountName;
    private String accountType;
    private static int accountNumber = 0;
    private String accountStatus = "open";
    private boolean overdraftProtection;
    private double accountBalance = 0;
    private double interest;

    protected void createAccount (String name, String type, boolean overdraft, double interestRate) {
        accountName = name;
        accountType = type;
        accountNumber++;
        overdraftProtection = overdraft;
        interest = interestRate;
    }

    protected void setAccountName (String name) {
        if (accountStatus != "closed") {
            accountName = name;
        }
    }

    protected String getAccountStatus () {
        return accountStatus;
    }

    protected void setAccountStatus (String status) {
        accountStatus = status;
    }

    protected double getAccountBalance () {
        return accountBalance;
    }

    protected void setAccountBalance (double balance) {
        accountBalance = balance;
    }

    protected boolean getOverdraftProtection () {
        return overdraftProtection;
    }
    }


