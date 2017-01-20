package marwamilton.bankaccount;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mkulima on 1/17/17.
 */
public class BankAccount_Test {
    BankAccount bankAccount;
    BankAccount bankAccount1;

    @Before
    public void setupTestVariables(){
        bankAccount = new BankAccount("CIB",30001, "Kira Granier", 2.00);
        bankAccount1 = new BankAccount("SAV", 20001, "Fitzroy North");
    }

    @Test
    public void getHoldersName_Test(){
        String expectedName = "Kira Granier";
        String actualName = bankAccount.getHoldersName();
        Assert.assertEquals("Name mismatch", expectedName,actualName);
    }

    @Test
    public void getBalance_Test(){
        double expectedBalance = 3000;
        bankAccount.credit(3000);
        double actualBalance = bankAccount.getBalance();
        Assert.assertEquals("Name mismatch", expectedBalance, actualBalance, 0.05);
    }

    @Test
    public void getAcType_Test(){
        String expectedACType = "CIB";
        String actualACType = bankAccount.getAcType();
        Assert.assertEquals("Name mismatch", expectedACType, actualACType);
    }

    @Test
    public void getRate_Test(){
        double expectedRate = 2.0;
        //bankAccount.credit(3000);
        double actualRate = bankAccount.getRate();
        Assert.assertEquals("Name mismatch", expectedRate, actualRate, 0.05);
    }

    @Test
    public void credit_Test(){
        boolean expectedCreditStatus = true;
        boolean actualCreditStatus = bankAccount.credit(1000);
        Assert.assertEquals("Name mismatch", expectedCreditStatus, actualCreditStatus);
    }

    @Test
    public void debit_Test(){
        boolean expectedDebitStatus = false;
        boolean actualDebitStatus = bankAccount.debit(1200, false);
        System.out.println(bankAccount.getBalance());
        Assert.assertEquals("Name mismatch", expectedDebitStatus, actualDebitStatus);
    }

    @Test
    public void transferFunds_Test(){
        boolean expectedStatus = false;
        boolean actualStatus = bankAccount.transferFunds(bankAccount1,3000);
        System.out.println(bankAccount.getBalance());
        Assert.assertEquals("Name mismatch", expectedStatus, actualStatus);
    }
}
