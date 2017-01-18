package reynoldsgillian.titko;

import org.junit.Test;
import reynoldstitko.gillian.AccountStatus;
import reynoldstitko.gillian.CustomerAccount;
import reynoldstitko.gillian.StatusType;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by gillianreynolds-titko on 1/17/17.
 */
public class CustomerAccountTest {

    CustomerAccount customerAccount = new CustomerAccount();


    @Test
    public void getAccountHolderNameTest(){
        String expected = "Gillian";
        customerAccount.setAccountHolderName("Gillian");
        String actual = customerAccount.getAccountHolderName();
        assertEquals("I expected Gillian", expected, actual);
    }

    @Test
    public void setAccountHolderNameTest(){
        String expected = "Jane Doe";
        customerAccount.setAccountHolderName("Jane Doe");
        String actual = customerAccount.getAccountHolderName();
        assertEquals("I expected Jane Doe", expected, actual);
    }

    @Test
    public void getAccountBalanceTest(){
        Double expected = 10.0;
        customerAccount.setAccountBalance(10.0);
        customerAccount.setAccountStatus(StatusType.OPEN);
        Double actual = customerAccount.getAccountBalance();
        assertEquals("Exepected 10", expected, actual);
    }

    @Test
    public void getAccountBalanceTestFrozen(){
        Double expected = null;
        customerAccount.setAccountBalance(10.0);
        customerAccount.setAccountStatus(StatusType.OFAC);
        Double actual = customerAccount.getAccountBalance();
        assertEquals("Expected null", expected, actual);
    }

    @Test
    public void setAccountBalanceTest(){
        Double expected = 15.0;
        customerAccount.setAccountBalance(15.0);
        customerAccount.setAccountStatus(StatusType.OPEN);
        Double actual = customerAccount.getAccountBalance();
        assertEquals("I expected 15.0", expected, actual);
    }

    @Test
    public void getAccountStatusTest(){
        StatusType expected = StatusType.OPEN;
        customerAccount.setAccountStatus(StatusType.OPEN);
        StatusType actual = customerAccount.getAccountStatus();
        assertEquals("I expected OPEN", expected, actual);
    }

    @Test
    public void getAccountStatusTest2(){
        StatusType expected = StatusType.CLOSED;
        customerAccount.setAccountStatus(StatusType.CLOSED);
        StatusType actual = customerAccount.getAccountStatus();
        assertEquals("I expected OPEN", expected, actual);
    }
}
