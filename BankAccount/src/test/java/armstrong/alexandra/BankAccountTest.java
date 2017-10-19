package armstrong.alexandra;

import org.junit.*;

import static armstrong.alexandra.Status.*;
import static armstrong.alexandra.AccountType.*;
import static armstrong.alexandra.Overdraft.*;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by alexandraarmstrong on 1/17/17.
 */
public class BankAccountTest {
    BankAccount ba;
    BankAccount bb;

    @Before
    public void setUp() {
    }

    @Test
    public void constructorTest1() {
        ba = new BankAccount(SAVINGS, "Alex Armstrong");
        AccountType expected = SAVINGS;
        AccountType actual = ba.getAccountType();
        assertEquals(expected, actual);
    }

    @Test
    public void constructorTest2() {
        ba = new BankAccount(SAVINGS, "Alex Armstrong");
        String expected = "Alex Armstrong";
        String actual = ba.getAccountHolderName();
        assertEquals(expected, actual);
    }

    @Test
    public void constructorTest3() {
        ba = new BankAccount(SAVINGS, "Alex Armstrong", ENABLED);
        AccountType expected = SAVINGS;
        AccountType actual = ba.getAccountType();
        assertEquals(expected, actual);
    }

    @Test
    public void constructorTest4() {
        ba = new BankAccount(SAVINGS, "Alex Armstrong", ENABLED);
        String expected = "Alex Armstrong";
        String actual = ba.getAccountHolderName();
        assertEquals(expected, actual);
    }

    @Test
    public void constructorTest5() {
        ba = new BankAccount(SAVINGS, "Alex Armstrong", ENABLED);
        Overdraft expected = ENABLED;
        Overdraft actual = ba.getOverdraft();
        assertEquals(expected, actual);
    }

    @Test
    public void constructorTest6() {
        ba = new BankAccount(SAVINGS, "Alex Armstrong", 1.04d);
        double expected = 1.04d;
        double actual = ba.getInterestRate();
        assertEquals(expected, actual);
    }

    @Test
    public void constructorTest7() {
        ba = new BankAccount(SAVINGS, "Alex Armstrong", 1.04d);
        AccountType expected = SAVINGS;
        AccountType actual = ba.getAccountType();
        assertEquals(expected, actual);
    }

    @Test
    public void constructorTest8() {
        ba = new BankAccount(SAVINGS, "Alex Armstrong", 1.04);
        String expected = "Alex Armstrong";
        String actual = ba.getAccountHolderName();
        assertEquals(expected, actual);
    }

    @Test
    public void constructorTest9() {
        ba = new BankAccount(SAVINGS, "Alex Armstrong", 1.04d, ENABLED);
        String expected = "Alex Armstrong";
        String actual = ba.getAccountHolderName();
        assertEquals(expected, actual);
    }

    @Test
    public void constructorTest10() {
        ba = new BankAccount(SAVINGS, "Alex Armstrong", 1.04d, ENABLED);
        AccountType expected = SAVINGS;
        AccountType actual = ba.getAccountType();
        assertEquals(expected, actual);
    }

    @Test
    public void constructorTest11() {
        ba = new BankAccount(SAVINGS, "Alex Armstrong", 1.04d,  ENABLED);
        Overdraft expected = ENABLED;
        Overdraft actual = ba.getOverdraft();
        assertEquals(expected, actual);
    }

    @Test
    public void constructorTest12() {
        ba = new BankAccount(SAVINGS, "Alex Armstrong", 1.04d,  ENABLED);
        double expected = 1.04d;
        double actual = ba.getInterestRate();
        assertEquals(expected, actual);
    }

    @Test
    public void getBalanceTest(){
        ba = new BankAccount(SAVINGS, "Alex Armstrong");
        ba.setStatus(FROZEN);
        Double expected = null;
        Double actual = ba.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void changeBalanceTest(){
        ba = new BankAccount(SAVINGS, "Alex Armstrong");
        ba.changeBalance(528d);
        ba.changeBalance(-432d);
        double expected = 0d + 528d + -432d;
        double actual = ba.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void changeBalanceTest2(){
        ba = new BankAccount(SAVINGS, "Alex Armstrong");
        ba.setStatus(CLOSED);
        String expected = "Balance inaccessible";
        String actual = ba.changeBalance(-432d);
        assertEquals(expected, actual);
    }

    @Test
    public void TransferFromTest1(){
        ba = new BankAccount(SAVINGS, "Alex Armstrong");
        bb = new BankAccount(SAVINGS, "Alexandra Armstrong");
        String expected = "Permission Denied";
        String actual = ba.transferMoneyToOtherAccount(bb, 40d);
        assertEquals(expected, actual);
    }

    @Test
    public void TransferFromTest2(){
        ba = new BankAccount(SAVINGS, "Alex Armstrong");
        bb = new BankAccount(SAVINGS, "Alex Armstrong");
        ba.changeBalance(30d);
        String expected = "Insufficient funds";
        String actual = ba.transferMoneyToOtherAccount(bb, 40d);
        assertEquals(expected, actual);
    }

    @Test
    public void TransferFromTest3(){
        ba = new BankAccount(SAVINGS, "Alex Armstrong");
        bb = new BankAccount(SAVINGS, "Alex Armstrong");
        ba.changeBalance(50d);
        String expected = "Transfer Successful";
        String actual = ba.transferMoneyToOtherAccount(bb, 40d);
        assertEquals(expected, actual);
    }

    @Test
    public void setAccountHolderNameTest(){
        ba = new BankAccount(SAVINGS, "Alex Armstrong");
        ba.setAccountHolderName("Alexandra Armstrong");
        String expected = "Alexandra Armstrong";
        String actual = ba.getAccountHolderName();
        assertEquals(expected, actual);
    }

    @Test
    public void setAccountHolderNameTest2(){
        ba = new BankAccount(SAVINGS, "Alex Armstrong");
        ba.setStatus(CLOSED);
        ba.setAccountHolderName("Alexandra Armstrong");
        String expected = "Alex Armstrong";
        String actual = ba.getAccountHolderName();
        assertEquals(expected, actual);
    }

    @Test
    public void closeAccountTest(){
        ba = new BankAccount(SAVINGS, "Alex Armstrong");
        ba.changeBalance(40d);
        String expected = "Please withdraw funds";
        String actual = ba.closeAccount();
        assertEquals(expected, actual);
    }

    @Test
    public void closeAccountTest2(){
        ba = new BankAccount(SAVINGS, "Alex Armstrong");
        String expected = "Account Closed";
        String actual = ba.closeAccount();
        assertEquals(expected, actual);
    }

    @Test
    public void changeFreezeStatus(){
        ba = new BankAccount(SAVINGS, "Alex Armstrong");
        String expected = "Account frozen";
        String actual = ba.changeFreezeStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void changeFreezeStatus2(){
        ba = new BankAccount(SAVINGS, "Alex Armstrong");
        ba.setStatus(FROZEN);
        String expected = "Account unfrozen";
        String actual = ba.changeFreezeStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void writeToFileTest(){
        ba = new BankAccount(SAVINGS, "Alex Armstrong");
        ba.changeBalance(40);
        ba.changeBalance( -20);
        ba.setStatus(CLOSED);
        ba.setStatus(OPEN);
        ba.setAccountHolderName("Alexandra Armstrong");
        ba.setInterestRate(1.04d);
    }
}