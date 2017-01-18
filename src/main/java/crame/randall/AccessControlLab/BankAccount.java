package crame.randall.AccessControlLab;

/**
 * Created by randallcrame on 1/17/17.
 */
public class BankAccount {
    private AccountTypes accountType;
    final long accountNumber = AccountNumberGenerator.createAccountNumber();

    double accountBalance =  0.00;

    String accountHoldersName = "Enter Name";
    double accountInterestRate = 0.00;
    AccountStatus accountStatus = AccountStatus.OPEN;
    OverDraftStatus overDraftStatus = OverDraftStatus.OFF;

    private BankAccount() {}

    public BankAccount(AccountTypes input) {
        setAccountType(input);
    }

    void setAccountType(AccountTypes input){
        this.accountType = input;
    }

    public AccountTypes getAccountType() {
        return this.accountType;
    }

    public long getAccountNumber() {
      return this.accountNumber;
    }

    void setAccountBalance(int input){
        this.accountBalance = input;
    }

    public double getAccountBalance(){
        return 0.0;
    }

    void setAccountHoldersName(String input) {
        this.accountHoldersName = input;
    }

    public String getAccountHoldersName() {
        return null;
    }
}


