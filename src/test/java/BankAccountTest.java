import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gerrodmozeik on 1/17/17.
 */
public class BankAccountTest {

    @Test
    public void setAccountTypeTEST() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountType(AccountType.SAVINGS);
        AccountType expected = AccountType.SAVINGS, actual = AccountType.SAVINGS;
        assertEquals(expected, actual);
    }

    @Test
    public void changeAccountHolderNameForClosedAccountTest() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.changeAccountHolderName("BOB");
        bankAccount.setAccountStatus(AccountStatus.CLOSED);
        bankAccount.changeAccountHolderName("STEVE");
        String expected = "BOB", actual = bankAccount.getAccountHolderName();
        assertEquals(expected, actual);
    }

    @Test
    public void changeAccountHolderNameForFrozenAccountTest() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.changeAccountHolderName("BOB");
        bankAccount.setAccountStatus(AccountStatus.FROZEN);
        bankAccount.changeAccountHolderName("STEVE");
        String expected = "BOB", actual = bankAccount.getAccountHolderName();
        assertEquals(expected, actual);
    }

    @Test
    public void changeAccountHolderNameForOpenAccountTest() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.changeAccountHolderName("BOB");
        bankAccount.changeAccountHolderName("STEVE");
        String expected = "STEVE", actual = bankAccount.getAccountHolderName();
        assertEquals(expected, actual);
    }

    @Test
    public void setAccountTypeFromFrozenToFrozen() {
        BankAccount bankAccount = new BankAccount();
        System.out.println(bankAccount.getAccountStatus());
        bankAccount.setAccountStatus(AccountStatus.FROZEN);
        System.out.println(bankAccount.getAccountStatus());
        bankAccount.setAccountStatus(AccountStatus.FROZEN);
        Boolean expected = false, actual = false;
        assertEquals(expected, actual);
    }

    @Test
    public void setAccountTypeFromFrozenToClosed() {
        BankAccount bankAccount = new BankAccount();
        System.out.println(bankAccount.getAccountStatus());
        bankAccount.setAccountStatus(AccountStatus.FROZEN);
        System.out.println(bankAccount.getAccountStatus());
        boolean expected = false, actual = bankAccount.setAccountStatus(AccountStatus.CLOSED);
        assertEquals(expected, actual);
    }

    @Test
    public void setAccountTypeFromFrozenToOpen() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountStatus(AccountStatus.FROZEN);
        bankAccount.setAccountStatus(AccountStatus.OPEN);
        Boolean expected = false, actual = false;
        assertEquals(expected, actual);
    }

    @Test
    public void setAccountTypeFromOpenToOpen() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountStatus(AccountStatus.OPEN);
        Boolean expected = false, actual = false;
        assertEquals(expected, actual);
    }

    @Test
    public void setAccountTypeFromOpenToClosed() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountStatus(AccountStatus.CLOSED);
        Boolean expected = false, actual = false;
        assertEquals(expected, actual);
    }

    @Test
    public void setAccountTypeFromOpenToFrozen() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountStatus(AccountStatus.FROZEN);
        Boolean expected = false, actual = false;
        assertEquals(expected, actual);
    }

    @Test
    public void setAccountTypeFromClosedToOpen() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountStatus(AccountStatus.CLOSED);
        bankAccount.setAccountStatus(AccountStatus.OPEN);
        Boolean expected = false, actual = false;
        assertEquals(expected, actual);
    }

    @Test
    public void setAccountTypeFromClosedToClosed() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountStatus(AccountStatus.CLOSED);
        bankAccount.setAccountStatus(AccountStatus.CLOSED);
        Boolean expected = false, actual = false;
        assertEquals(expected, actual);
    }

    @Test
    public void setAccountTypeFromClosedToFrozen() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountStatus(AccountStatus.CLOSED);
        bankAccount.setAccountStatus(AccountStatus.FROZEN);
        Boolean expected = false, actual = false;
        assertEquals(expected, actual);
    }

}