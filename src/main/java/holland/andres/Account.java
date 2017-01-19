package holland.andres;

public class Account {

    private String accountName;
    private String accountType;
    private String overdraftProtection;
    private double interest;
    private String accountStatus = "open";
    private static int accountNumberCreator = 0;
    private int accountNumber;
    private double accountBalance = 0;

    public Account (String name, String type, String overdraft, double interestRate) {
        this.accountName = name;
        this.accountType = type;
        this.overdraftProtection = overdraft;
        this.interest = interestRate;
        accountNumber = accountNumberCreator;
        accountNumberCreator++;
    }

    public void setAccountName (String name) {
        if (accountStatus != "closed") {
            accountName = name;
        }
    }

    public String getAccountStatus () {
        return accountStatus;
    }

    public void setAccountStatus (String status) {
        accountStatus = status;
    }

    public double getAccountBalance () {
        return accountBalance;
    }

    public String getAccountName () {
        return accountName;
    }

    public void setAccountBalance (double balance) {
        accountBalance = balance;
    }

    public String getOverdraftProtection () {
        return overdraftProtection;
    }

    public int getAccountNumber () {
        return accountNumber;
    }

    }