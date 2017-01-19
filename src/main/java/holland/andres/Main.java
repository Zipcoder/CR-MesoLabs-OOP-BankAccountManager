package holland.andres;

public class Main {

    public static void main(String[] args) {

        Account account1 = new Account("andres holland","checking","enabled",3);
        Account account2 = new Account("andres holland","checking","enabled",3);
        System.out.println(account1.getAccountNumber());
        System.out.println(account2.getAccountNumber());
    }

}
