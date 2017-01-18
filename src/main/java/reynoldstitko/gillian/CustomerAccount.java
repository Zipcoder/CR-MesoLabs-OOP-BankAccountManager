package reynoldstitko.gillian;

import static java.lang.System.out;

/**
 * Created by gillianreynolds-titko on 1/17/17.
 */
public class CustomerAccount {
    private Double accountBalance;
    private String accountHolderName;

    private StatusType accountStatus;
    //private OverdraftStatus overdraftStatus = new OverdraftStatus();
    //private StatusType statusType = new StatusType();


    public String getAccountHolderName(){
        return accountHolderName;
    }

    public void setAccountHolderName(String customerName){
        accountHolderName  = customerName;
    }

    //Balance inquiries are allowed at any time except while an account
    // is under an OFAC freeze (status)
    public Double getAccountBalance(){
        if(!getAccountStatus().equals(StatusType.OFAC))
        return accountBalance;
        else {
            System.out.println("Your account is frozen");
            return null;
        }
    }

    public void setAccountBalance(Double accountBalance){
        this.accountBalance = accountBalance;
    }

    public String debit(Double reduceByAmount){
        accountBalance = accountBalance - reduceByAmount;
        return "Account debited successfully";

    }

    public String credit(Double increaseByAmount){
        accountBalance = accountBalance + increaseByAmount;
        return "Account credited successfully";

    }

    //Accounts can transfer funds to or from another account with the same account holder
    // ‐‐ Neither account's balance should fall below zero as a result of a transfer.
    public String transferFunds(CustomerAccount fromCustomerAccount, CustomerAccount toCustomerAccount,
                              Double amountToTransfer){
        String sourceName = fromCustomerAccount.getAccountHolderName();
        String destinationName = toCustomerAccount.getAccountHolderName();
        if(sourceName.equals(destinationName) && (fromCustomerAccount.accountStatus.equals(StatusType.OPEN))
                && (toCustomerAccount.accountStatus.equals(StatusType.OPEN)) &&
                fromCustomerAccount.accountBalance >= amountToTransfer)
        {
            fromCustomerAccount.debit(amountToTransfer);
            toCustomerAccount.credit(amountToTransfer);
            return "Transfer successful";
        }
        return "Transfer not successful";
    }

    public void setAccountStatus(StatusType accountStatus) {

        this.accountStatus = accountStatus;
    }

    public StatusType getAccountStatus() {

        return accountStatus;
    }


}
