package mccann.kevin.bankaccount;

/**
 * Created by kevinmccann on 1/17/17.
 */
public class BankAccount {
    private char acctType;
    private String acctNumber;
    private double acctBalance;
    private String acctHolderName;
    private double interestRate;
    private AcctStatus acctStatus;
    private ODPStatus overdraftPreventionStatus;

    private BankAccount(char acctType, String acctNum, String name) {
        setAcctNumber(acctNum);
        setAcctType(acctType);
        setAcctHolderName(name);
    }

    private void setAcctHolderName(String name) {
        acctHolderName = name;
    }

    private String getAcctHolderName() {
        return acctHolderName;
    }

    private void setAcctNumber(String acctNumber) {
        this.acctNumber = acctNumber;
    }

    private String getAcctNumber() {
        return this.acctNumber;
    }

    private void setAcctType(char type) {
        acctType = type;
    }

    private char getAcctType() {
        return acctType;
    }

    void setInterestRate(double rate) {
        interestRate = rate;
    }

    public void setAcctStatus(AcctStatus status) {
        acctStatus = status;
    }

    private AcctStatus getAcctStatus() {
        return acctStatus;
    }

    private void setOverdraftPreventionStatus(ODPStatus ODP) {
        this.overdraftPreventionStatus = ODP;
     }

    private ODPStatus getOverdraftPreventionStatus() {
        return overdraftPreventionStatus;
    }

    public void setAcctBalance(double amount) {
        acctBalance = amount;
    }

    public double getAcctBalance() {
        if(!(getAcctStatus() == AcctStatus.FROZEN))
            return acctBalance;
        return 0;
    }

    private boolean credit(double amount) {
        if(getAcctStatus() == AcctStatus.OPEN) {
            acctBalance += amount;
            return true;
        }
        else return false;
    }

    private boolean debit(double amount) {
        if(getOverdraftPreventionStatus() == ODPStatus.ON && getAcctBalance() < amount)
            return false;
        if(getAcctStatus()==AcctStatus.OPEN && getAcctBalance() > amount) {
            acctBalance -= amount;
            return true;
        }
        return false;
    }

    private boolean transferTo(double amount, BankAccount BA) {
        if (BA.getAcctHolderName().equals(this.getAcctHolderName()) && this.getAcctBalance() > amount) {
            debit(amount);
            BA.credit(amount);
        }
        else
            return false;

    }

    private boolean transferFrom(double amount, BankAccount BA) {
        if (BA.getAcctHolderName().equals(this.getAcctHolderName()) && BA.getAcctBalance() > amount) {
            credit(amount);
            BA.debit(amount);
        }
        else
            return false;

    }
}
