package ATM;

import ATM.ATM;

public class Main {

    public static void main(String[] args) {
        ATM atm = new ATM("users.csv", "accounts.csv", "transactions.csv");

        atm.serviceLoop();
    }
}
