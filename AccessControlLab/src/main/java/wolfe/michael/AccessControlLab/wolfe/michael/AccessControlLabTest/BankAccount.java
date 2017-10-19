package wolfe.michael.AccessControlLab.wolfe.michael.AccessControlLabTest;

import java.util.ArrayList;

/**
 * Created by michaelwolfe on 1/17/17.
 */
public class BankAccount {

    public AccountTypes type;
    public OverdraftProtection odp;
    public AccountStatus status;
    private String accountNumber; //  CANT BE CHANGED
    private double accountBalance;
    private String accountHolderName;
    private double interestRate;
    private ArrayList<Transactions> transactions;
    //transaction log array list updated with a string builder


    public BankAccount(String actNumber, AccountTypes actType, String actHolderName, AccountStatus state, OverdraftProtection prot) {
        accountNumber = actNumber;
        type = actType;
        accountHolderName = actHolderName;
        status = state;
        odp = prot;
        //prompt for name and account type
        //auto generate account number
    }

    public void setAccountBalance(double d) {
        this.accountBalance = d;
    }

    public double getAccountBalance() {
        return this.accountBalance;
    }

    public void setAccountHolderName(String s) {
        this.accountHolderName = s;
    }

    public String getAccountHolderName() {
        return this.accountHolderName;
    }

    public void setAccountStatus(AccountStatus state) {
        this.status = state;
    }

    public AccountStatus getAccountStatus() {
        return this.status;
    }

    public void setOverdraftProtection(OverdraftProtection type) {
        odp = type;
    }

    public OverdraftProtection getOverdraftProtection() {
        return this.odp;
    }

    public void setInterestRate(double d) {
        this.interestRate = d;
    }

    public double getInterestRate() {
        return this.interestRate;
    }

    public String balanceInquiry(String accountNumber) {
        if (accountNumber.equals(this.accountNumber))
            if (this.getAccountStatus() == AccountStatus.FROZEN)
                return "error";
            else
                return Double.toString(this.accountBalance);
        else
            return "error";
    }

    public boolean creditAccount(double amount, BankAccount receivingAccount) {
        if (receivingAccount.status.equals(AccountStatus.OPEN)) {
            receivingAccount.accountBalance += amount;
            return true;
        } else
            return false;
    }

    public boolean debitAccount(double amount, BankAccount account) {
        if (account.status.equals(AccountStatus.OPEN) && account.odp.equals(OverdraftProtection.NO)) {
            account.accountBalance -= amount;
            return true;
        } else if ((!account.odp.equals(OverdraftProtection.NO)) && (account.accountBalance - amount) < 0)
            return false;
        else {
            account.accountBalance -= amount;
            return true;
        }
    }


    public boolean transferFundsFromAccountToAccount(double amount, BankAccount receivingAccount) {
        if(checkAccountForSameHolderName(this, receivingAccount))
            if(receivingAccount.status.equals(AccountStatus.OPEN)){
                receivingAccount.creditAccount(amount, receivingAccount);
                this.debitAccount(amount, this);
                return true;
            }
            else
                return false;
        else
            return false;
    }

    public boolean checkAccountForSameHolderName(BankAccount sender, BankAccount receiver){
        return sender.getAccountHolderName().equals(receiver.getAccountHolderName());
    }

    public boolean openAccount(BankAccount account){
        if(this.status.equals(AccountStatus.CLOSED))
            return false;
        else {
            this.status = AccountStatus.OPEN;
            return true;
        }
    }

    public boolean closeAccount(BankAccount account){
        if(account.getAccountBalance() == 0) {
            this.status = AccountStatus.CLOSED;
            return true;
        }
        else
            return false;
    }

    public class Transactions {
        public String accountNumber;
        public String accountHolderName;
        public String transactionType;
        public String transactionAmount;
        public String newAccountBalance;
    }

    /**
     * Created by michaelwolfe on 1/17/17.
     */
    public static class CheckingAccount extends BankAccount {
        public CheckingAccount(String actNumber, String actName, AccountStatus state, OverdraftProtection prot){
            super(actNumber, AccountTypes.CHECKING, actName,  state, prot);

        }
    }
}
