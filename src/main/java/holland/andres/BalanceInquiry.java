package holland.andres;

public class BalanceInquiry {
    public void inquire (Account account) {
        String status = account.getAccountStatus();
        if (status.equalsIgnoreCase("open") ||
                status.equalsIgnoreCase("closed")) {
            double balance = account.getAccountBalance();
            System.out.println(balance);
        }
    }
}
