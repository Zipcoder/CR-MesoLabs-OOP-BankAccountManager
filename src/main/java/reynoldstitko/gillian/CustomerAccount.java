package reynoldstitko.gillian;

/**
 * Created by gillianreynolds-titko on 1/17/17.
 */
public class CustomerAccount {
    private Double accountBalance;
    private String accountHolderName;

    public String getAccountHolderName(){
        return accountHolderName;
    }

    public void setAccountHolderName(String customerName){
        accountHolderName  = customerName;
    }

    //Balance inquiries are allowed at any time except while an account
    // is under an OFAC freeze (status)
    public Double getAccountBalance(){
        if(status != "OFAC")
        return accountBalance;
        else {
            System.out.println("Your account is frozen");
            return null;
        }
    }

    public void setAccountBalance(){

    }

    //Accounts can transfer funds to or from another account with the same account holder
    // ‐‐ Neither account's balance should fall below zero as a result of a transfer.
    public void transferFunds(Long accountNumber, Double amountToTransfer){

    }


}
