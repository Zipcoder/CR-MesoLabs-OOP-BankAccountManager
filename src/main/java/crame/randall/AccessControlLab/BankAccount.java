package crame.randall.AccessControlLab;

/**
 * Created by randallcrame on 1/17/17.
 */
public class BankAccount {
    private AccountTypes accountType;
    private int some;
    final long accountNumber = AccountNumberGenerator.createAccountNumber();

    double accountBalance =  0.00;

    String accountHoldersName;
    double accountInterestRate = 0.00;
    AccountStatus accountStatus = AccountStatus.OPEN;
    OverDraftStatus overDraftStatus = OverDraftStatus.OFF;

    void setAccountType(AccountTypes input){
        this.accountType = input;
    }

    AccountTypes getAccountType() {
        return this.accountType;
    }

    void setAccountNumber(long input) {
        this.accountNumber = input;
    }

    long getAccountNumber() {
      return this.accountNumber;
    }


}


