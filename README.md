# project-2-ATM
Week 2 project: ATM Simulator

## Notes for Use
- The DB class defines database objects and a number of attendant methods to delete, search, modify, and add rows in the database
- Information is stored in csv files in the /data folder. An example data set is included and will be run when you run `main()`. Any changes to accounts, users, or additional transactions will be saved there. These files are in the `.gitignore`, so any changes you make locally wouldn't overwrite them
- One example user, for convenience of entry during testing, has card number 1 and password 1234
- There are also a couple of test database files (`test.db` and `testbad.csv` which are used in certain tests. Other tests create and destory temporary database files
- Every time a user logs in, interest is earned on savings accounts and investments get returns, based on random chance and risk tolerance defined when creating the account


## ATM Requirements

Every feature must have corresponding unit tests
Tests should demonstrate proper behavior, and proper handling of misuse (eg. attempts to deposit/transfer/withdraw negative amounts

- User interface: CLI (Command line interface) Only
  - Direct Input
  - Numbered options (instead of on-screen buttons)
  - ASCII art welcome but not required
- Must support account types:
  - Checking
  - Savings
  - Investment
- Account Actions
  - Withdraw from acct
  - Deposit to acct
  - Transfer across accounts (self)
  - Open new account
  - Close account (must be empty)
  - Print transaction history
  - Check balance
  - **Challenge:** Transfer to another user's account (but not from)
- Support multiple users
  - Users have associated accounts
  - Can create new user
  - Users are authenticated with a password (generated or provided on user creation)
  - Can exit a user and enter another user
- **BONUS** Persistence
  - Users and accounts remain persistent
  - Opportunity for research


Recommended:
Create a `Console` class that manages console interactions.
Create a `ConsoleMock` for testing (provide scripted user input using this object).

## What's next?
The next lab is located [here](https://github.com/Zipcoder/ZCW-MesoLabs-OOP-BankAccountManager).

