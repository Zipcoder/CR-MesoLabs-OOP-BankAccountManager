package reynoldsgillian.titko;

import org.junit.Test;
//import reynoldstitko.gillian.AccountStatus;
import reynoldstitko.gillian.CustomerAccount;
import reynoldstitko.gillian.StatusType;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by gillianreynolds-titko on 1/17/17.
 */
public class CustomerAccountTest {

    CustomerAccount customerAccount = new CustomerAccount();
    CustomerAccount customerAccount2 = new CustomerAccount();


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
    public void getAccountStatusTestOpen(){
        StatusType expected = StatusType.OPEN;
        customerAccount.setAccountStatus(StatusType.OPEN);
        StatusType actual = customerAccount.getAccountStatus();
        assertEquals("I expected OPEN", expected, actual);
    }

    @Test
    public void getAccountStatusTestClosed(){
        StatusType expected = StatusType.CLOSED;
        customerAccount.setAccountStatus(StatusType.CLOSED);
        StatusType actual = customerAccount.getAccountStatus();
        assertEquals("I expected CLOSED", expected, actual);
    }

    @Test
    public void getAccountStatusTestOFAC(){
        StatusType expected = StatusType.OFAC;
        customerAccount.setAccountStatus(StatusType.OFAC);
        StatusType actual = customerAccount.getAccountStatus();
        assertEquals("I expected OFAC", expected, actual);
    }

    @Test
    public void transferFundsTest(){
        customerAccount.setAccountStatus(StatusType.OPEN);
        customerAccount2.setAccountStatus(StatusType.OPEN);
        customerAccount.setAccountHolderName("Ted");
        customerAccount2.setAccountHolderName("Ted");
        customerAccount.setAccountBalance(20.0);
        customerAccount2.setAccountBalance(100.0);
        String expected = "Transfer successful";
        String actual = customerAccount.transferFunds(customerAccount2, customerAccount, 5.0);
        assertEquals("I expected success", expected, actual);
    }

    @Test
    public void transferFundsFail1Test(){
        customerAccount.setAccountStatus(StatusType.OPEN);
        customerAccount2.setAccountStatus(StatusType.OPEN);
        customerAccount.setAccountHolderName("Tom");
        customerAccount2.setAccountHolderName("Ted");
        customerAccount.setAccountBalance(20.0);
        customerAccount2.setAccountBalance(100.0);
        String expected = "Transfer not successful";
        String actual = customerAccount.transferFunds(customerAccount2, customerAccount, 5.0);
        assertEquals("I expected not successful", expected, actual);
    }

    @Test
    public void transferFundsFail2Test(){
        customerAccount.setAccountStatus(StatusType.CLOSED);
        customerAccount2.setAccountStatus(StatusType.OPEN);
        customerAccount.setAccountHolderName("Tom");
        customerAccount2.setAccountHolderName("Ted");
        customerAccount.setAccountBalance(20.0);
        customerAccount2.setAccountBalance(100.0);
        String expected = "Transfer not successful";
        String actual = customerAccount.transferFunds(customerAccount2, customerAccount, 5.0);
        assertEquals("I expected not successful", expected, actual);
    }

    @Test
    public  void  debitTest(){
        String expected = "Account debited successfully";
        customerAccount.setAccountBalance(100.0);
        customerAccount.setAccountStatus(StatusType.OPEN);
        String actual =  customerAccount.debit(50.0);
        assertEquals("I expected account debited successfully", expected, actual);
    }

    @Test
    public  void  creditTest(){
        String expected = "Account credited successfully";
        customerAccount.setAccountBalance(100.0);
        customerAccount.setAccountStatus(StatusType.OPEN);
        String actual =  customerAccount.credit(50.0);
        assertEquals("I expected account debited successfully", expected, actual);
    }


}
