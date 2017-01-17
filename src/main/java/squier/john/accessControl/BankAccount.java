package squier.john.accessControl;

import java.util.ArrayList;

/**
 * Created by johnsquier on 1/17/17.
 */
public class BankAccount {

    private BankAccountType accountType;
    private int accountNumber;
    private static int nextAccountNumber = 1;
    private double balance;
    private String accountHoldersName;
    private double interestRate;
    private BankAccountStatus accountStatus;
    private OverdraftProtection overdraftProtection;
    private ArrayList<BankAccountTransaction> transactionRecord;

    public BankAccount(BankAccountType accountType, double balance, String accountHoldersName, double interestRate,
                       BankAccountStatus accountStatus, OverdraftProtection overdraftProtection) {
        this.accountType = accountType;
        accountNumber = nextAccountNumber++;
        this.balance = balance;
        this.accountHoldersName = accountHoldersName;
        this.interestRate = interestRate;
        this.accountStatus = accountStatus;
        this.overdraftProtection = overdraftProtection;
        transactionRecord = new ArrayList<BankAccountTransaction>();
    }

    public Double getBalance() {
        if ( accountStatus.equals(BankAccountStatus.OFAC_FROZEN) ) {
            return null;
        }
        else {
            return balance;
        }
    }

    public String getAccountHoldersName() {
        return accountHoldersName;
    }

    public void setAccountHoldersName(String newName) {
        if ( accountStatus.equals(BankAccountStatus.OPEN) ) {
            accountHoldersName = newName;
        }

        return;
    }

    public BankAccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(BankAccountStatus accountStatus) {
        if ( isAccountOpenOrFrozen() ) {
            // check balance if trying to close
            if ( isNewAccountStatusClose(accountStatus) ) {
                if ( this.balance == 0.0 ) {
                    this.accountStatus = accountStatus;
                }
            }
            else {
                this.accountStatus = accountStatus;
            }
        }

        return;
    }

    private boolean isAccountOpenOrFrozen() {
        return !accountStatus.equals(BankAccountStatus.CLOSED);
    }

    private boolean isNewAccountStatusClose(BankAccountStatus newStatus) {
        return newStatus.equals(BankAccountStatus.CLOSED);
    }

    public ApprovalStatus updateBalanceWithCreditOrDebit(double amount) {

        if ( accountStatus.equals(BankAccountStatus.OPEN) ) {

            if ( overdraftProtection.equals(OverdraftProtection.ENABLED) ) {
                if ( isTransactionAnOverDraft(amount) ) {
                    return ApprovalStatus.NOT_APPROVED;
                }
            }

            if ( amount > 0.0 ) {
                return credit(amount);
            }
            else if ( amount < 0.0 ) {
                return debit(amount);
            }
            else {
                return ApprovalStatus.ZERO_TRANSACTION;
            }
        }
        else {
            return ApprovalStatus.CANT_COMPLETE_DUE_TO_ACCT_STATUS;
        }
    }

    private ApprovalStatus credit(double amountToCredit) {
        Double previousBalance = getBalance();
        balance += amountToCredit;

        // check if balance updated
        if ( (previousBalance + amountToCredit) == getBalance() ) {
            return ApprovalStatus.APPROVED;
        }
        else {
            return ApprovalStatus.NOT_APPROVED;
        }
    }

    private ApprovalStatus debit(double amountToDebit) {
        // add overdraft logic
        balance += amountToDebit;
        return ApprovalStatus.APPROVED;
    }


    private boolean isTransactionAnOverDraft(double amount) {
        return (Math.abs(amount) > this.getBalance());
    }

    // refactor this and transferBalanceTo, gotta be some reapeat code
    public ApprovalStatus transferBalanceFrom(BankAccount transferFrom, double amountToTransfer) {
        if ( bothAccountsHaveSameOwner(this, transferFrom) ) {

            if ( doesAccountHaveSufficientBalance(transferFrom, amountToTransfer) ) {
                ApprovalStatus debitApproval = transferFrom.updateBalanceWithCreditOrDebit(-amountToTransfer);
                ApprovalStatus creditApproval = this.updateBalanceWithCreditOrDebit(amountToTransfer);

                // pull out into check approvals method
                if ( (debitApproval.equals(ApprovalStatus.APPROVED))
                        && creditApproval.equals(ApprovalStatus.APPROVED) ) {
                    return ApprovalStatus.APPROVED;
                }
            }
        }

        return ApprovalStatus.NOT_APPROVED;
    }

    public ApprovalStatus transferBalanceTo(BankAccount transferTo, double amountToTransfer) {
        // check if both accts have the same owener
        if ( bothAccountsHaveSameOwner(this, transferTo) ) {

            // check if from has enough money
            if (doesAccountHaveSufficientBalance(this, amountToTransfer)) {

                // do the transfer
                ApprovalStatus debitApproval = this.updateBalanceWithCreditOrDebit(-amountToTransfer);
                ApprovalStatus creditApproval = transferTo.updateBalanceWithCreditOrDebit(amountToTransfer);

                if ((debitApproval.equals(ApprovalStatus.APPROVED))
                        && creditApproval.equals(ApprovalStatus.APPROVED)) {
                    return ApprovalStatus.APPROVED;
                }
            }
        }

        return ApprovalStatus.NOT_APPROVED;
    }

    private boolean bothAccountsHaveSameOwner(BankAccount transferFrom, BankAccount transferTo) {
        return transferFrom.getAccountHoldersName().equals(transferTo.accountHoldersName);
    }

    private boolean doesAccountHaveSufficientBalance(BankAccount acct, double amount) {
        return acct.getBalance() >= amount;
    }
}
