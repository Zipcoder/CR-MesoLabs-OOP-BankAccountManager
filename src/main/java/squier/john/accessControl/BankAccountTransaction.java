package squier.john.accessControl;

/**
 * Created by johnsquier on 1/17/17.
 */
public class BankAccountTransaction {
    private TransactionType transactionType;
    private Double transactionAmount;
    private BankAccountStatus newStatus;
    private String newName;

    public BankAccountTransaction(TransactionType transactionType, double transactionAmount,
                                  BankAccountStatus newStatus, String newName) {
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.newStatus = newStatus;
        this.newName = newName;
    }

    public TransactionType getTransactionType() { return transactionType; }
    public Double getTransactionAmount() { return transactionAmount; }
    public BankAccountStatus getBankAccountStatus() { return newStatus; }
    public String getName() { return  newName}
}
