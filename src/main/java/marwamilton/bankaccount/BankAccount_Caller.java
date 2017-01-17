package marwamilton.bankaccount;

import java.util.ArrayList;

/**
 * Created by mkulima on 1/17/17.
 */
public class BankAccount_Caller {

    public static BankAccount bankAccount1 = new BankAccount("CHK", 10001, "John Doe");
    public static BankAccount bankAccount2 = new BankAccount("SAV", 20001, "Asha Umar");
    public static BankAccount bankAccount3 = new BankAccount("CIB", 30001, "Jing Li", 1.0);

    public static ArrayList<BankAccount> accounts = new ArrayList<BankAccount>(3);


    public static void main(String[] args) {
        accounts.add(bankAccount1);
        accounts.add(bankAccount2);
        accounts.add(bankAccount3);
        int counter =0;

        System.out.println("Accounts Prior to Modification: \n");
        System.out.format("%15s %5s %6s %6s \n", "Name", "Type", "BAL", "Rate");
        for(BankAccount account: accounts) {
            System.out.format("%15s %4s %7.2f %6.2f \n", account.getHoldersName(), account.getAcType(),account.getRate(), account.getBalance());
        }

        double[] credits = new double[]{1000, 5000, 20000, 3000};
        //double[] debits = new double[]{1000, 5000, 20000};
        for (BankAccount account : accounts){
            System.out.println(account.credit(credits[counter++]));
        }
        counter=0;
        for (BankAccount account : accounts){
            System.out.println(account.credit(credits[counter++]));
        }

        System.out.println();
        System.out.println("Accounts After Modification: \n");
        System.out.format("%15s %5s %6s %6s \n", "Name", "Type", "Rate", "BAL");
        for(BankAccount account: accounts) {
            System.out.format("%15s %4s %7.2f %6.2f \n", account.getHoldersName(), account.getAcType(),account.getRate(), account.getBalance());
        }


        for(BankAccount account : accounts) {
            System.out.println();
            for (String record : account.allTransactions) {
                System.out.println(record);
            }
        }


    }
}
