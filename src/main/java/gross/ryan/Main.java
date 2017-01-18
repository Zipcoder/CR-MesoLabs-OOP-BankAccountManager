package gross.ryan;

/**
 * Created by ryangross on 1/17/17.
 */
public class Main {


    public static void main(String[] args) {
        Bank initBank = new Bank();
        initBank.addAccount("ryan", "checking", 1234);
        BankAccount myAccount = initBank.findAccount("ryan");
        System.out.println(initBank.findAccount("ryan"));

    }
}
