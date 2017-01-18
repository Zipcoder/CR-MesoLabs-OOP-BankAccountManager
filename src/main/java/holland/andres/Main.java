package holland.andres;

public class Main {

    public static void main(String[] args) {

        Account account1 = new Account();
        Account account2 = new Account();
        account1.createAccount("andres holland","checking",true,3);
        account2.createAccount("wendy","checking",false,0);
        ChangeName changeName = new ChangeName();
        changeName.change(account2,"andres");
    }

}
