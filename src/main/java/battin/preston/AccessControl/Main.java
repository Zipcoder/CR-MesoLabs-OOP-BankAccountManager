package battin.preston.AccessControl;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by prestonbattin on 1/17/17.
 */
public class Main {

    static Scanner input = new Scanner(System.in);
    static ArrayList<Checking> checkingAccount = new ArrayList<>();
    static ArrayList<Savings> savingsAccount = new ArrayList<>();
    static int i;

    public static void main(String[] args) {

        OpenAccount openedAccount = new OpenAccount();
        openedAccount.getNameandAccountType();
        openedAccount.openAccount();
















    }
}
