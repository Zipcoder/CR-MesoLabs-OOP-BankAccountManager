package ATM.interfaces;

import ATM.ATM;

public interface Menu {

    void displayMenu();

    void handleChoice(int choice);

    String getName();
}