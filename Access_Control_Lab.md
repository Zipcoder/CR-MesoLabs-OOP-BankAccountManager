# Access Control Lab - Bank Account

## Description

This lab focuses on implementing a simulated bank account and practicing using access control features of the Java language. By the end of this lab students should feel comfortable setting class members to be private or public, creating accessor and mutator functions for fields as needed, and using those methods to access the underlying fields.

The bank account functionality produced in this lab will be integrated into the weekly project and may be further enhanced during the project.

## Testing

All features should be developed following a Test-Driven Development methodology. All features should be thoroughly tested and demonstrated through unit tests.

## Instructions

Create a class for bank accounts.

Accounts must have: 

- Account type (Checking, Savings, Investment, etc.)
- Account number (Must be unique for each account created)
- Balance
- Account Holder's name
- Interest rate (some accounts may not draw interest)
- Status (Open, Closed, [OFAC](https://www.treasury.gov/about/organizational-structure/offices/Pages/Office-of-Foreign-Assets-Control.aspx) Freeze...)
- Overdraft prevention (enabled, disabled, or automatic account transfer*)
- A record of all transactions that have taken place on the accounts (withdrawals, deposits, transfers, and changes to the status, name, or interest rate)


Code that uses the Account class should not be able to change the properties of an account directly; this should be something handled by methods provided by the account class. The methods should enforce the following behavior:

- Account type and account number must be set during account creation (in the constructor) and cannot be changed afterward.
- Balance inquiries are allowed at any time except while an account is under an OFAC freeze
- The balance can be changed with a credit (add money) or debit (remove money)
  - Balance changes can only occur on `Open` accounts.
  - The `debit` and `credit` methods should return an approval status indicating whether the transaction was approved.
  - Accounts can transfer funds to or from another account with the same account holder -- Neither account's balance should fall below zero as a result of a transfer.
- Account holder's name must be set during account creation. It can be changed later (but not on closed accounts)
- Accounts with overdraft prevention enabled cannot over-draw (a debit that is greater than the account balance will be declined and the balance will not change)
- Accounts, once closed, cannot be reopened (frozen accounts can be unfrozen).
  - No changes to the balance of an account can take place while it is closed or frozen
  - Accounts must have a zero balance before they can be closed.