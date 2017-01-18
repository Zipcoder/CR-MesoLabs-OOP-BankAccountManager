package battin.preston.AccessControl;

import static battin.preston.AccessControl.Main.*;
import static battin.preston.AccessControl.OpenAccount.choice;

/**
 * Created by prestonbattin on 1/17/17.
 */
public class MainMenu {

   static void decideChecking(){

        System.out.println("What would you like to do today " + checkingAccount.get(i).getHoldersName() + "?");
        choice = input.nextLine();
        MainMenu.MenuItems(choice);
    }

    static  void decideSavings(){

        System.out.println("What would you like to do today " + savingsAccount.get(i).getHoldersName() + "?");
        choice = input.nextLine();
        MainMenu.MenuItems(choice);
    }

    static void MenuItems(String pick){

        switch(pick){

            case "Transfer":


                break;

            case "Inquiries":

                break;

            case "Credit":

                break;

            case "Debit":

                break;

            case "Change Name":

                break;

            case "Close":

                break;

            case "Freeze":

                break;

            default:

                System.out.println("Options: Transfer, Inquiries, Credit, Debit, Change Name, Close, Freeze.");




        }

    }

}
