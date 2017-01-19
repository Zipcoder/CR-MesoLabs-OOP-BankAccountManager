package holland.andres;

public class CloseAccount {

    public void close (Account account) {
        double balance = account.getAccountBalance();
        account.setAccountBalance(balance);
        account.setAccountStatus("closed");
    }
}
