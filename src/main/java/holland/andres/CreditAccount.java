package holland.andres;

public class CreditAccount {

    public boolean credit (Account account, double amount) {
        String status = account.getAccountStatus();
        double balance = account.getAccountBalance();
        if (status.equalsIgnoreCase("open")) {
            account.setAccountBalance(balance + amount);
            return true;
        }
        return false;
    }
}
