package battin.preston.AccessControl;

/**
 * Created by prestonbattin on 1/17/17.
 */
public class Account {


    protected String accountType, holdersName, status, overDraftProtection;
    protected  int checkingAccountNumber, savingAccountNumber;
    protected double balance, rate;
    static protected int totalCheckingAccounts = 0, totalSavingAccount = 0;

    protected Account() {
    }

    ;

    protected Account(String type, String name) {
        if (type.equals("Checking")) {
            this.checkingAccountNumber = totalCheckingAccounts;
            this.accountType = type;
            totalCheckingAccounts++;
        } else if (type.equals("Saving")) {
            this.savingAccountNumber = totalSavingAccount;
            this.accountType = type;
            totalSavingAccount++;
        }

        this.holdersName = name;

    }

    protected void setName(String holdersName) {

        this.holdersName = holdersName;
    }

    protected String getHoldersName() {

        return this.holdersName;
    }

    protected void setStatus(String status) {

        if (status.equals("Open"))
            this.status = status;

        else if (status.equals("Closed"))
            this.status = status;

        else
            this.status = "OFAC Freeze";

    }

    protected String getStatus() {

        return this.status;
    }

    protected void setOverDraftProtection(String set) {

        this.overDraftProtection = set;
    }

    protected String getOverDraftProtection() {

        return this.overDraftProtection;
    }

    protected void setRate() {

        this.rate = this.accountType.equals("Savings") ? .06 : .05;

    }

    protected double getRate() {

        return this.rate;
    }

    protected void setBalance(double deposit) {

        this.balance = deposit;
    }

    protected double getBalance() {

        return this.balance;
    }

    protected int getCheckingAccountNumber() {

        return this.checkingAccountNumber;
    }

    protected int getSavingAccountNumber() {

        return this.savingAccountNumber;
    }


}
