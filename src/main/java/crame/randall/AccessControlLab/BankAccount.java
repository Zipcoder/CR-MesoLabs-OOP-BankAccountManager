package crame.randall.AccessControlLab;

/**
 * Created by randallcrame on 1/17/17.
 */
public class BankAccount {
    private AccountTypes accountType;
    int accountNumber;
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

    void setAccountNumber(int input) {

    }


}






}
