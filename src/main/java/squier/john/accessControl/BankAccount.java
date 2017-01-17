package squier.john.accessControl;

import java.util.ArrayList;

/**
 * Created by johnsquier on 1/17/17.
 */
public class BankAccount {

    private BankAccountType accountType;
    private int accountNumber;
    private static int nextAccoutNumber = 1;
    private double balance;
    private String accountHoldersName;
    private double interestRate;
    private BankAccountStatus accountStatus;
    private OverdraftProtection overdraftProtection;
    private ArrayList<BankAccountTransaction> transactionRecord;

    public BankAccount(BankAccountType accountType, double balance, String accountHoldersName, double interestRate,
                       BankAccountStatus accountStatus, OverdraftProtection overdraftProtection) {
        this.accountType = accountType;
        accountNumber = nextAccoutNumber++;
        this.balance = balance;
        this.accountHoldersName = accountHoldersName;
        this.interestRate = interestRate;
        this.accountStatus = accountStatus;
        this.overdraftProtection = overdraftProtection;
        transactionRecord = new ArrayList<BankAccountTransaction>();
    }

    public Double getBalance() {
        if ( accountStatus.equals(BankAccountStatus.OFAC_FROZEN) ) {
            // do nothing probably throw an expection at some point
            return null;
        }
        else {
            return balance;
        }
    }

    public ApprovalStatus updateBalanceWithCreditOrDebit(double amount) {
        if ( accountStatus.equals(BankAccountStatus.OPEN) ) {
            if ( amount > 0.0 ) {
                credit(amount);
            }
            else {
                debit(amount);
            }
        }
    }

    private ApprovalStatus credit(double amount) {

    }

    private ApprovalStatus debit(double amount) {

    }
}
