package gross.ryan;

import java.sql.Time;
import java.util.ArrayList;
import java.time.*;
import java.util.Timer;


/**
 * Created by ryangross on 1/17/17.
 */
public class BankAccount {
    private String acctType;
    private double acctNumber;
    private String overDraft = "protect";
    private double acctBalance;
    private String acctName;
    private double interestRate = 0;
    private String acctStatus = "open";
    private ArrayList transactionLog = new ArrayList();
    public enum allStatus {open, closed, frozen}
    public enum overDraftOptions {yes, no, protect}
    public enum kindsOfAccts{checking, savings, investment}


    private String getType() {
        return this.acctType;
    }

    public BankAccount(String aName, String type,  double aNumber) {
        this.acctName = aName;
        //this.acctType = aType;
        this.acctNumber = aNumber;
    }

    private String getStatus() {
        return this.acctStatus;
    }

    private double getBalance(){
        return this.acctBalance;

    }

    private void addToLog(String anAction) {
        LocalDateTime occurredOn = LocalDateTime.now();
        this.transactionLog.add(occurredOn + ": " + anAction);
    }

    protected String balanceInquiry() {
        if (this.getStatus().equals(allStatus.open.toString())) {
            this.addToLog("Balance Inquiry.");
            return ("Current Balance: " + this.getBalance());
        } else if (this.getStatus().equals(allStatus.closed.toString())) {
            return "Account is closed.";
        } else {
            this.addToLog("Fraud Alert: Account Access Attempted");
            return "Error: Please call 1-877-383-4802 for more information.";
        }
    }

    private void setAccountName(String newName) {
        if(this.getStatus().equals(allStatus.open)) {
            this.acctName = newName;
        }
    }


    protected boolean debitMoney(double someMoney) {
        if (this.getStatus().equals(allStatus.open.toString())) {
            this.addToLog("Deposit of " + someMoney + " successful.");
            this.acctBalance += someMoney;
            return true;
        }
        this.addToLog("Deposit of " + someMoney + " failed; closed/frozen acct.");
        return false;
    }

    private String getOverDraft() {
        return this.overDraft;
    }

    protected boolean overDraftProtect() {
        if (this.getOverDraft().equals(overDraftOptions.protect.toString())) {
            return true;
        }
        return false;
    }


    protected boolean creditMoney(double someMoney) {
        if (this.getStatus().equals(allStatus.open.toString())) {
            if (someMoney > this.acctBalance) {
                if (this.overDraftProtect()) {
                    this.addToLog("Overdraft Protection: Transaction Blocked");
                    return false;
                }
            } else {
                this.addToLog("Transaction for " + someMoney + ": " + "Account is over-drafted");
                this.acctBalance -= someMoney;
                return true;
            }
        }
        return false;
    }

    protected boolean closeAccount() {
        if (this.getStatus().equals(allStatus.open.toString()) && this.getBalance() == 0) {
            this.acctStatus = "closed";
            this.addToLog("Account closed.");
            return true;
        }
        return false;
    }

    protected void setOverDraft(String anOption) {
        this.overDraft = anOption;
    }

    protected boolean reOpenAccount() {
        if (this.acctStatus.equals(allStatus.frozen.toString())) {
            this.acctStatus = "open";
            this.acctStatus = "Account reopened.";
            return true;
        }
        return false;
    }


    protected void setType(String newType) {
        this.acctType = newType;
    }
    protected void setInterestRate(double aRate) {
        if (!this.getType().equals(kindsOfAccts.checking.toString())) {
            this.interestRate = aRate;
        }
    }

    public void setStatus(String newStatus) {
        this.acctStatus = newStatus;
    }

}
