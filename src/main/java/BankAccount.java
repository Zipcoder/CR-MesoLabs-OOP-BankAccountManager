/**
 * Created by gerrodmozeik on 1/17/17.
 */
public class BankAccount {

    private AccountType accountType;
    private double accountNumber;
    private double accountBalance;
    private String accountHolderName;
    private double interestRate;
    private boolean overDraftProtection;
    private boolean autoTransfer;
    private String[] transactionRecord;
    private ODPStatus odpStatus;
    private AccountStatus accountStatus;

    public BankAccount(double accountNumber, String accountHolderName, AccountType accountType) {
        //Constructor that creates new accounts
        //If accountNumber is already used, don't open account
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public void changeAccountHolderName(String accountHolderName) {
        if (this.accountStatus == AccountStatus.OPEN) {
            this.accountHolderName = accountHolderName;
        } else {
            System.out.println("You can't change the name on this account.");
        }
    }

    public boolean setAccountStatus(AccountStatus accountStatus) {
        if (accountStatus == AccountStatus.CLOSED && accountBalance != 0.0) {
            return false;
        } else {
            this.accountStatus = accountStatus;
            return true;
        }
    }

    public String getBalance() {
        if (accountStatus == AccountStatus.FROZEN) {
            return "Your account is frozen.";
        } else {
            return Double.toString(accountBalance);
        }
    }

    public boolean creditAccount(double creditAmount) {
        if (accountStatus == AccountStatus.OPEN) {
            this.accountBalance += creditAmount;
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
                    return true;
                }
            } else {
                this.accountBalance -= debitAmount;
                return true;
            }
        } else {
            return false;
        }
    }
}
