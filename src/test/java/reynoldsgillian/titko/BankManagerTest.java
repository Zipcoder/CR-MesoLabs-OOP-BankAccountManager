package reynoldsgillian.titko;

import org.junit.Test;
import reynoldstitko.gillian.AccountType;
import reynoldstitko.gillian.BankManager;

/**
 * Created by gillianreynolds-titko on 1/17/17.
 */
public class BankManagerTest {

    BankManager bankManager = new BankManager(AccountType.CHECKING, "Jane Doe");


    @Test
    public void BankManagerTest(){

    }

    @Test
    public void closeAccountTest(){
        Double expected = 0.0;

    }
}
