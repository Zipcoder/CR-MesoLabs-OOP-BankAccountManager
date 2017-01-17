/**
 * Created by gerrodmozeik on 1/17/17.
 */
public class BankAccount {

    private AccountType accountType;
    private String accountNumber;
    private double accountBalance;
    private String accountHolderName;
    private double interestRate;
    private boolean overDraftProtection;
    private boolean autoTransfer;
    private String transactionRecord;
    private ODPStatus odpStatus;
    private AccountStatus accountStatus;

    public BankAccount() {
        accountStatus = AccountStatus.OPEN;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
        writeToLog("Account Type Changed");
    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    public void changeAccountHolderName(String accountHolderName) {
        if (this.accountStatus == AccountStatus.OPEN) {
            this.accountHolderName = accountHolderName;
            writeToLog("Account Holder Name Changed");
        } else {
            System.out.println("You can't change the name on this account.");
        }
    }

    public String getAccountHolderName() {
        return this.accountHolderName;
    }

    public boolean setAccountStatus(AccountStatus accountStatus) {
        if (accountStatus == AccountStatus.CLOSED && accountBalance != 0.0) {
            return false;
        } else {
            this.accountStatus = accountStatus;
            writeToLog("Account Status Changed to " + this.accountStatus);
            return true;
        }
    }

    public String getBalance() {
        if (accountStatus == AccountStatus.FROZEN) {
            return "Your account is frozen.";
        } else {
            writeToLog("Balance inquiry.");
            return Double.toString(accountBalance);
        }
    }

    public boolean creditAccount(double creditAmount) {
        if (accountStatus == AccountStatus.OPEN) {
            this.accountBalance += creditAmount;
            writeToLog("Your account has been credited by " + creditAmount);
            return true;
        } else {
            return false;
        }
    }

    public boolean debitAccount(double debitAmount) {
        if (accountStatus == AccountStatus.OPEN) {
            if (odpStatus == ODPStatus.OFF) {
                if (this.accountBalance < debitAmount) {
                    return false;
                } else {
                    this.accountBalance -= debitAmount;
                    writeToLog("Your account has been debited by " + debitAmount);
                    return true;
                }
            } else {
                this.accountBalance -= debitAmount;
                writeToLog("Your account has been debited by " + debitAmount);
                return true;
            }
        } else {
            return false;
        }
    }

    public String writeToLog(String logItem) {
        this.transactionRecord += logItem;
        return transactionRecord;
    }
}
