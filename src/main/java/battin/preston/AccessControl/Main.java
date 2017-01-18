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

    public static void main(String[] args) {

        String choice, name;


        System.out.println("Would you like to open a Checking or Savings account?");
        choice = input.nextLine();
        System.out.println("What is your full name?");
        name = input.nextLine();

        OpenAccount.openAccount(choice, name);

        System.out.println("What would you like to do today " + name + "?");
        choice = input.nextLine();
        MainMenu.MenuItems(choice);









    }
}
