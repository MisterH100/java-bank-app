# SIMPLE BANK APP

## HOW TO RUN

### NETBEANS

- Open NetBeans and press CTRL+SHIFT+O, to open a project.
- Navigate to the folder where this program is in and open it.
- Once you have this project opened up in NetBeans, press F6 to run it or click on the run button.

### COMMAND LINE

**_ Java must be installed in your machine for this program to run _**

- Open your terminal.
- Use the `cd + (path to folder where this program is in)` command to enter the directory where this program is in.
- Run `java src/main/java/com/mycompany/bankaccount/BankAccount.java` to start program.

## USING THE PROGRAM

### ON START UP

- On start up you will be presented with 3 options:

1. Create new account - to create a new account.
2. Existing accounts - to access existing accounts (two default accounts are already available for use, Default Account: password = 123456 and Test Account: password = test12).
3. Quit - for exiting the program.

## CREATING A NEW ACCOUNT

- Steps to creating a new account:

1. Enter your first name.
2. Enter your last name.
3. Choose account type.
4. Create password.
5. Confirm password.
   **_ Account numbers are generated automatically _**.
   **_ Default account balance of a new account is 100 _**.
   **_ Account details will be display once you are finished creating an account _**.

**_ CONGRATS YOU HAVE AN ACCOUNT _**

## LOGGING IN TO EXISTING ACCOUNT

- Steps to using an existing account:

1. A list of available accounts will be displayed by their account numbers.
2. Choose one account from the list.
3. Enter account password.
   **_ Account details will be displayed on successful login _**.

**_ CONGRATS YOU HAVE LOGGED IN _**

## TRANSACTIONS

- Once you have access to an account you can perform transaction.
- You can withdraw from you account, deposit to your account, and transfer funds to an existing account.
- In addition can check your account balance and your account's transaction history.
- There are 6 options:

1. Withdraw - withdraw funds from account.
2. Deposit - deposit funds to account.
3. Transfer - transfer funds to an existing account.
4. Balance - available balance for account.
5. Transaction History - View account transaction history.
6. Quit - to exit program.

### WITHDRAW

- Steps to withdraw funds from account:

1. Enter amount you want to withdraw.
   **_ You can't withdraw amounts greater than available funds _**.

### DEPOSIT

- Steps to deposit to account:

1. Enter amount you want to deposit.

### TRANSFER

- Steps to transfer funds to another account:

1. Enter a 10 digit account number you want to transfer funds to.
   **_ Account number should be one of the existing accounts (use Default Account: 1234567890) if you don't have another account _**.
2. Enter amount you want to transfer.
   **_ You can't transfer amounts greater than available funds_**.

### BALANCE

- With this option, account balance will be displayed.

### TRANSACTION HISTORY

- With this option, all transaction history for this account will be displayed if any.

## DATABASE

### LOCATION

- Databases for this program are 2 CSV (comma-separated values) files located in the database folder.

### ACCOUNTS DATABASE

- `accounts_database.csv` file is where all the accounts info is located.
- All the values are separated by commas.
  **_ Account Number, First Name, Last Name, Account Type, Balance, Password _**.

### TRANSACTION HISTORY DATABASE

- `transaction_history.csv` file is where transaction history for all accounts is located.
- All the values are separated by commas.
  **_ Account Number, Transaction Type, Transaction Date, Amount, Recipient_**.
