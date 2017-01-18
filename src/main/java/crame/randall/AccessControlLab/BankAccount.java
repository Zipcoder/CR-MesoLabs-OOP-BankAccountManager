package crame.randall.AccessControlLab;

/**
 * Created by randallcrame on 1/17/17.
 */
public class BankAccount {
    private AccountTypes accountType;
    final long accountNumber = AccountNumberGenerator.createAccountNumber();

    double accountBalance =  0.00;

    String accountHoldersName = "Enter Name";
    double accountInterestRate = 0.00;
    AccountStatus accountStatus = AccountStatus.OPEN;
    OverDraftStatus overDraftStatus = OverDraftStatus.OFF;

    private BankAccount() {}

    public BankAccount(AccountTypes input) {
        setAccountType(input);
    }

    void setAccountType(AccountTypes input){
        this.accountType = input;
    }

    public AccountTypes getAccountType() {
        return this.accountType;
    }

    public long getAccountNumber() {
      return this.accountNumber;
    }

    void setAccountBalance(int input){
        this.accountBalance = input;
    }

    public double getAccountBalance(){
        return this.accountBalance;
    }

    void setAccountHoldersName(String input) {
        this.accountHoldersName = input;
    }

    public String getAccountHoldersName() {
        return this.accountHoldersName;
    }

    void setAccountInterestRate(double input){
        this.accountInterestRate = input;
    }
    public double getAccountInterestRate() {
        return this.accountInterestRate;
    }
    void setAccountStatus(AccountStatus input) {
        this.accountStatus = input;
    }

    public AccountStatus getAccountStatus() {
        return this.accountStatus;
    }

    private boolean isAccountOpen(AccountStatus input) {
        return input.equals(AccountStatus.OPEN);
    }

    private boolean isAccountFrozen(AccountStatus input) {
        return input.equals(AccountStatus.FROZEN);
    }

    private boolean isAccountClosed(AccountStatus input) {
        return (input == AccountStatus.CLOSED);
    }

    private boolean isNSF(double input){
        return (input <= this.getAccountBalance());
    }

    private String unableToCompleteRequest(){
        return "Unable to Complete Request...";
    }
    public String balanceInquiry(){
        return (isAccountFrozen(getAccountStatus())) ? unableToCompleteRequest(): String.valueOf(getAccountBalance());
    }

    private String debitAccount(double input){
        this.accountBalance-= input;
        return "Debit Complete";
    }

    public String requestDebitAccount(double input){
        return (isAccountOpen(getAccountStatus()) && isNSF(input)) ? debitAccount(input) : unableToCompleteRequest();
    }

    private String creditAccount(double input){
        this.accountBalance+= input;
        return "Credit Complete";
    }

    public String requestCreditAccount(double input){
        return (isAccountOpen(getAccountStatus()))? creditAccount(input) : unableToCompleteRequest();
    }

}


