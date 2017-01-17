package squier.john.accessControl;

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

}
