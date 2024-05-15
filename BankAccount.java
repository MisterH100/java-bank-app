import java.util.Scanner;
import java.io.File;  
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;
import java.time.LocalDate;

public class BankAccount{
    //instance variables
    private String accountNumber;
    private String firstName;
    private String lastName;
    private String accountHolder;
    private float balance;
    private String accountType;
    
    //default values
    public BankAccount(){
        this.accountNumber ="1234567890";
        this.firstName = "default";
        this.lastName = "account";
        this.accountHolder = "default account";
        this.balance = 100;
        this.accountType = "Checking";
    }

    /* ---------- methods ---------- */

    //get account number
    public String getAccountNumber(){
        return accountNumber;
    }
    
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    //get account holder
    public String getAccountHolder(){
        return accountHolder;
    }
    //get balance
    public float getBalance(){
        return balance;
    }
    //get account type
    public String getAccountType(){
        return accountType;
    }
    //set details
    public void setDetails(String accNumber,String fname,String lname,String type,String balance){
        float amount = Float.parseFloat(balance);
        this.firstName = fname;
        this.lastName = lname;
        this.accountHolder = fname + " " + lname;
        this.accountNumber = accNumber;
        this.accountType = type;
        this.balance = amount;
        
    }
    //withdraw funds
    public void withdraw(String accountID,float withdrawAmount){
        if(withdrawAmount > this.balance){
            System.out.println(" ");
            System.out.println("|------------------------------|"); 
            System.out.println("Transaction Failed");
            System.out.println(" ");
            System.out.println("Insufficient funds");
            System.out.println("Available funds: "+ this.balance);
            System.out.println(" ");
            return;
        }
        String transactionHistoryDatabaseName = "transaction_history.csv";
        String accountsDatabaseName = "accounts_database.csv";
        ArrayList<String> transactionList = new ArrayList<String>();
        ArrayList<String> accountList = new ArrayList<String>();
        HashMap<String,String> transactionDetails = new HashMap<String,String>();
        HashMap<String,String> accountDetails = new HashMap<String,String>();
        LocalDate transactionDate = LocalDate.now();
        String transactionDateString = transactionDate.toString();
        String withdrawAmountString = Float.toString(withdrawAmount);
        this.balance = this.balance - withdrawAmount;

        try {
            File database = new File(accountsDatabaseName);
            Scanner databaseReader = new Scanner(database);
            while (databaseReader.hasNextLine()) {
                String data = databaseReader.nextLine();
                accountList.add(data);
                
            }
            databaseReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
         for(int i=0;i<accountList.size();i++){
            String[] tempArray = accountList.get(i).split(",");
            if(tempArray[0].strip().contentEquals(accountID)){
                System.out.println("Transferring...");
                float accountAmount = Float.parseFloat(tempArray[4]);
                float newAmount = accountAmount - withdrawAmount;
                String balance = Float.toString(newAmount);

                accountDetails.put("accountNumber", tempArray[0]);
                accountDetails.put("firstName", tempArray[1]);
                accountDetails.put("lastName", tempArray[2]);
                accountDetails.put("accountType", tempArray[3]);
                accountDetails.put("balance",balance );
                
                accountList.set(i,accountDetails.get("accountNumber")+","+accountDetails.get("firstName")+","+accountDetails.get("lastName")+","+accountDetails.get("accountType")+","+accountDetails.get("balance"));

                try {
                    FileWriter myWriter = new FileWriter(accountsDatabaseName);
                    for(int j =0;j<accountList.size();j++){
                        if(j==0){
                            myWriter.append(accountList.get(j));
                        }
                        else{
                            myWriter.append("\n"+accountList.get(j));
                        }
                    }
                    myWriter.close();
                    System.out.println("Transfer successful");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                break;
            }
            
        }

        transactionDetails.put("accountNumber", accountID);
        transactionDetails.put("transactionType","Withdraw");
        transactionDetails.put("transactionDate",transactionDateString);
        transactionDetails.put("transactionAmount",withdrawAmountString);
        transactionDetails.put("transactionBetween",accountID);
        try {
            File database = new File(transactionHistoryDatabaseName);
            Scanner databaseReader = new Scanner(database);
            while (databaseReader.hasNextLine()) {
                String data = databaseReader.nextLine();
                transactionList.add(data);    
            }
            databaseReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }



        transactionList.add(transactionDetails.get("accountNumber")+","+transactionDetails.get("transactionType")+","+transactionDetails.get("transactionDate")+","+transactionDetails.get("transactionAmount")+","+transactionDetails.get("transactionBetween"));

        try {
            FileWriter myWriter = new FileWriter(transactionHistoryDatabaseName);
            for(int i =0;i<transactionList.size();i++){
                if(i==0){
                    myWriter.append(transactionList.get(i));
                }
                else{
                    myWriter.append("\n"+transactionList.get(i));
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(" ");
        System.out.println("|------------------------------|"); 
        System.out.println("Transaction Complete");
        System.out.println(" ");  
        System.out.println("Transaction Type: Withdraw"); 
        System.out.println("Transaction Date: "+ transactionDate);
        System.out.println("Account: "+ this.accountNumber);
        System.out.println("Account Type: "+ this.accountType); 
        System.out.println("Amount: "+ "-"+withdrawAmount);
        System.out.println("Available Balance: "+ this.balance);
        System.out.println(" ");
        
    }
    //deposit funds
    public void deposit(String accountID,float depositAmount){
        String transactionHistoryDatabaseName = "transaction_history.csv";
        String accountsDatabaseName = "accounts_database.csv";
        ArrayList<String> transactionList = new ArrayList<String>();
        ArrayList<String> accountList = new ArrayList<String>();
        HashMap<String,String> transactionDetails = new HashMap<String,String>();
        HashMap<String,String> accountDetails = new HashMap<String,String>();
        LocalDate transactionDate = LocalDate.now();
        String transactionDateString = transactionDate.toString();
        String depositAmountString = Float.toString(depositAmount);
        this.balance = this.balance + depositAmount;

        try {
            File database = new File(accountsDatabaseName);
            Scanner databaseReader = new Scanner(database);
            while (databaseReader.hasNextLine()) {
                String data = databaseReader.nextLine();
                accountList.add(data);
                
            }
            databaseReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
         for(int i=0;i<accountList.size();i++){
            String[] tempArray = accountList.get(i).split(",");
            if(tempArray[0].strip().contentEquals(accountID)){
                System.out.println("Transferring...");
                float accountAmount = Float.parseFloat(tempArray[4]);
                float newAmount = accountAmount + depositAmount;
                String balance = Float.toString(newAmount);

                accountDetails.put("accountNumber", tempArray[0]);
                accountDetails.put("firstName", tempArray[1]);
                accountDetails.put("lastName", tempArray[2]);
                accountDetails.put("accountType", tempArray[3]);
                accountDetails.put("balance",balance );
                
                accountList.set(i,accountDetails.get("accountNumber")+","+accountDetails.get("firstName")+","+accountDetails.get("lastName")+","+accountDetails.get("accountType")+","+accountDetails.get("balance"));

                try {
                    FileWriter myWriter = new FileWriter(accountsDatabaseName);
                    for(int j =0;j<accountList.size();j++){
                        if(j==0){
                            myWriter.append(accountList.get(j));
                        }
                        else{
                            myWriter.append("\n"+accountList.get(j));
                        }
                    }
                    myWriter.close();
                    System.out.println("Transfer successful");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                break;
            }
            
        }


        transactionDetails.put("accountNumber", accountID);
        transactionDetails.put("transactionType","Deposit");
        transactionDetails.put("transactionDate",transactionDateString);
        transactionDetails.put("transactionAmount",depositAmountString);
        transactionDetails.put("transactionBetween",accountID);
        try {
            File database = new File(transactionHistoryDatabaseName);
            Scanner databaseReader = new Scanner(database);
            while (databaseReader.hasNextLine()) {
                String data = databaseReader.nextLine();
                transactionList.add(data);    
            }
            databaseReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        transactionList.add(transactionDetails.get("accountNumber")+","+transactionDetails.get("transactionType")+","+transactionDetails.get("transactionDate")+","+transactionDetails.get("transactionAmount")+","+transactionDetails.get("transactionBetween"));

        try {
            FileWriter myWriter = new FileWriter(transactionHistoryDatabaseName);
            for(int i =0;i<transactionList.size();i++){
                if(i==0){
                    myWriter.append(transactionList.get(i));
                }
                else{
                    myWriter.append("\n"+transactionList.get(i));
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        System.out.println(" ");
        System.out.println("|------------------------------|"); 
        System.out.println("Transaction Complete");
        System.out.println(" ");  
        System.out.println("Transaction Type: Deposit"); 
        System.out.println("Transaction Date: "+ transactionDate);
        System.out.println("Account: "+ this.accountNumber);
        System.out.println("Account Type: "+ this.accountType); 
        System.out.println("Amount: "+ "+"+depositAmount);
        System.out.println("Available Balance: "+ this.balance);
        System.out.println(" ");
    }

    public void transfer(String accountID,float transferAmount){
        String accountsDatabaseName = "accounts_database.csv";
        String transactionHistoryDatabaseName = "transaction_history.csv";
        ArrayList<String> accountList = new ArrayList<String>();
        ArrayList<String> transactionList = new ArrayList<String>();
        HashMap<String, String> accountDetails = new HashMap<String, String>();
        HashMap<String,String> transactionDetails = new HashMap<String,String>();
        LocalDate transactionDate = LocalDate.now();
        String transactionDateString = transactionDate.toString();
        String transferAmountString = Float.toString(transferAmount);

        if(transferAmount>this.balance){
            System.out.println(" ");
            System.out.println("|------------------------------|"); 
            System.out.println("Transaction Failed");
            System.out.println(" ");
            System.out.println("Insufficient funds");
            System.out.println("Available funds: "+ this.balance);
            System.out.println(" ");
            return;
        }
        System.out.println(" ");
        System.out.println("|------------------------------|"); 
        System.out.println("Transfer to: "+ accountID);
        System.out.println("Amount: "+ transferAmount);
        try {
            File database = new File(accountsDatabaseName);
            Scanner databaseReader = new Scanner(database);
            while (databaseReader.hasNextLine()) {
                String data = databaseReader.nextLine();
                accountList.add(data);
                
            }
            databaseReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println(" ");
        System.out.println("Validating account number...");
        boolean transferred = false;
        for(int i=0;i<accountList.size();i++){
            String[] tempArray = accountList.get(i).split(",");
            if(tempArray[0].strip().contentEquals(accountID)){
                System.out.println("Transferring...");
                float accountAmount = Float.parseFloat(tempArray[4]);
                float newAmount = accountAmount + transferAmount;
                String balance = Float.toString(newAmount);

                accountDetails.put("accountNumber", tempArray[0]);
                accountDetails.put("firstName", tempArray[1]);
                accountDetails.put("lastName", tempArray[2]);
                accountDetails.put("accountType", tempArray[3]);
                accountDetails.put("balance",balance );
                
                accountList.set(i,accountDetails.get("accountNumber")+","+accountDetails.get("firstName")+","+accountDetails.get("lastName")+","+accountDetails.get("accountType")+","+accountDetails.get("balance"));

                try {
                    FileWriter myWriter = new FileWriter(accountsDatabaseName);
                    for(int j =0;j<accountList.size();j++){
                        if(j==0){
                            myWriter.append(accountList.get(j));
                        }
                        else{
                            myWriter.append("\n"+accountList.get(j));
                        }
                    }
                    myWriter.close();
                    System.out.println("Transfer successful");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                    transferred = false;
                }
                transferred = true;
                break;
            }
            
        }

        transactionDetails.put("accountNumber", this.accountNumber);
        transactionDetails.put("transactionType","Transfer");
        transactionDetails.put("transactionDate",transactionDateString);
        transactionDetails.put("transactionAmount",transferAmountString);
        transactionDetails.put("transactionBetween",accountID);
        try {
            File database = new File(transactionHistoryDatabaseName);
            Scanner databaseReader = new Scanner(database);
            while (databaseReader.hasNextLine()) {
                String data = databaseReader.nextLine();
                transactionList.add(data);    
            }
            databaseReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        transactionList.add(transactionDetails.get("accountNumber")+","+transactionDetails.get("transactionType")+","+transactionDetails.get("transactionDate")+","+transactionDetails.get("transactionAmount")+","+transactionDetails.get("transactionBetween"));

        try {
            FileWriter myWriter = new FileWriter(transactionHistoryDatabaseName);
            for(int i =0;i<transactionList.size();i++){
                if(i==0){
                    myWriter.append(transactionList.get(i));
                }
                else{
                    myWriter.append("\n"+transactionList.get(i));
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        if(transferred){
            System.out.println("|------------------------------|"); 
            System.out.println("Transaction Complete");
            System.out.println(" "); 
            System.out.println("Transaction Type: Transfer"); 
            System.out.println("Transaction Date:" + transactionDate);
            System.out.println("From: " +this.accountNumber); 
            System.out.println("To: "+accountID); 
            System.out.println("Amount: " + transferAmount);
        }else{
            System.out.println("Failed to transfer");
        }
    }

    public void getTransactions(String accountID){
        String transactionHistoryDatabaseName = "transaction_history.csv";
        ArrayList<String> transactionList = new ArrayList<String>();
        int count =0;
        int match =0;
        
        try {
            File database = new File(transactionHistoryDatabaseName);
            Scanner databaseReader = new Scanner(database);
            while (databaseReader.hasNextLine()) {
                String data = databaseReader.nextLine();
                transactionList.add(data);
            }
            databaseReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("|------------------------------|");
        System.out.println("Transaction History");
        System.out.println(" ");
        if(transactionList.size() <1){
            System.out.println("No transaction history at this moment");
            return;
        }
        for(int i=0;i<transactionList.size();i++){
            String[] tempArray = transactionList.get(i).split(",");
            if(tempArray[0].contentEquals(accountID)){
                match = match+1;
                System.out.println("Account Number: "+ tempArray[0]);
                System.out.println("Transaction Type: "+ tempArray[1]);
                System.out.println("Transaction Date: "+ tempArray[2]);
                System.out.println("Amount: "+ tempArray[3]);
                System.out.println("To: "+ tempArray[4]);
                System.out.println("|------------------------------|");
            }
            if(count == transactionList.size()-1){
                if(match ==0){
                    System.out.println("No transaction history for this account at the moment");
                }
            }
            count = count+1;

        }
    }

    public static void main(String[] args) {
        //global variables
        int choice;
        int option;
        float amount;
        String accountID;
        final String accountsDatabaseName = "accounts_database.csv";
        HashMap<String, String> accountDetails = new HashMap<String, String>();
        BankAccount account = new BankAccount(); 
        @SuppressWarnings("resource")
        Scanner mainInput = new Scanner(System.in);
        
        System.out.println(" ");
        System.out.println("***WELCOME TO THOBEKA BANKING, WHERE WE TAKE YOUR MONEY SERIOUSLY***");
        System.out.println("");
        System.out.println("1. New account");
        System.out.println("2. Existing account");
        System.out.println("3. Quit");
        System.out.print("Enter your choice (options:1,2,3): ");
        choice = mainInput.nextInt();
        mainInput.nextLine();

        if(choice ==1){
            //local scope variables
            String accountNumber;
            String firstName;
            String lastName;
            String accountType;
            String[] accountTypeOptions = {"Checking","Savings"};
            final String initialBalance = "100";
            ArrayList<String> accountList = new ArrayList<String>();

            //prompt user to enter details and store them in a hashmap
            System.out.print("Enter your First Name: ");
            firstName = mainInput.nextLine();
            accountDetails.put("firstName", firstName);

            System.out.print("Enter your Last Name: ");
            lastName = mainInput.nextLine();
            accountDetails.put("lastName", lastName);

            option=0;
            System.out.println("1. Checking");
            System.out.println("2. Savings");
            System.out.print("Choose Account Type (options:1,2): ");
            option = mainInput.nextInt();
            accountType = accountTypeOptions[option-1];
            
            //generating 10 digit account number 
            Random random = new Random();
            char [] digits = new char[10];
            digits[0] = (char) (random.nextInt(9) + '1');
            for(int i=1; i<digits.length; i++) {
                digits[i] = (char) (random.nextInt(10) + '0');
            }
            
            long generatedAccNumber= Long.parseLong(new String(digits));

            accountNumber = Long.toString(generatedAccNumber);
            accountDetails.put("accountNumber",accountNumber);
            accountDetails.put("accountType",accountType);
            accountDetails.put("balance",initialBalance);
            
            try {
            File database = new File(accountsDatabaseName);
            Scanner databaseReader = new Scanner(database);
            while (databaseReader.hasNextLine()) {
                String data = databaseReader.nextLine();
                accountList.add(data);
                
            }
            databaseReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            
            accountList.add(accountDetails.get("accountNumber")+","+accountDetails.get("firstName")+","+accountDetails.get("lastName")+","+accountDetails.get("accountType")+","+accountDetails.get("balance"));
            //write details to CSV file (database)
            try {
                FileWriter myWriter = new FileWriter(accountsDatabaseName);
                for(int i =0;i<accountList.size();i++){
                    if(i==0){
                        myWriter.append(accountList.get(i));
                    }
                    else{
                        myWriter.append("\n"+accountList.get(i));
                    }
                }
                myWriter.close();
                System.out.println("Successfully created account");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                return;
            }
            
            //use setDetails method to set account details 
            account.setDetails(accountDetails.get("accountNumber"),accountDetails.get("firstName"),accountDetails.get("lastName"),accountDetails.get("accountType"),accountDetails.get("balance"));

            //print account details
            System.out.println(" ");
            System.out.println("|------------------------------|");
            System.out.println("Account Details");
            System.out.println(" ");
            System.out.println("Account Number: "+account.getAccountNumber());
            System.out.println("Account Holder Name: "+account.getAccountHolder());
            System.out.println("Account Type: " + account.getAccountType());
            System.out.println("Balance: "+account.getBalance()); 
            System.out.println(" ");

            //transaction event loop
            option =0;
            amount=0;
            do {
                System.out.println("|------------------------------|");
                System.out.println("Transact");
                System.out.println(" ");
                System.out.println("1. Withdraw");
                System.out.println("2. Deposit");
                System.out.println("3. Transfer");
                System.out.println("4. Balance");
                System.out.println("5. Transaction History");
                System.out.println("6. Quit");
                System.out.print("Choose transaction (options:1,2,3,4,5,6): ");
                option = mainInput.nextInt();
                mainInput.nextLine();
                switch(option){
                    case 1:
                        //withdrawal
                        accountID = account.getAccountNumber();
                        System.out.print("Withdraw amount: ");
                        amount = mainInput.nextInt();
                        account.withdraw(accountID,amount);
                        break;
                    case 2:
                        //deposit
                        accountID = account.getAccountNumber();
                        System.out.print("Deposit amount: ");
                        amount = mainInput.nextInt();
                        account.deposit(accountID,amount);
                        break;
                    case 3:
                        //transfer
                        System.out.println(" ");
                        System.out.print("Enter a 10 digit account number (eg: 1234567890): ");
                        accountID = mainInput.nextLine();
                        System.out.print("Enter amount to transfer: ");
                        amount = mainInput.nextFloat();
                        account.transfer(accountID, amount);
                        System.out.println(" ");
                        break;
                    case 4:
                        //check balance
                        System.out.println(" ");
                        System.out.println("|------------------------------|");
                        System.out.println("Account Balance");
                        System.out.println(" ");
                        System.out.println("Available funds: "+ account.getBalance());
                        System.out.println(" ");
                        break;
                    case 5:
                        //get transaction history
                        System.out.println(" ");
                        accountID = account.getAccountNumber();
                        account.getTransactions(accountID);
                        System.out.println(" ");
                        break;
                    case 6:
                        //exit transactions
                        System.out.println(" ");
                        System.out.println("***Thank you for banking with us***");
                        System.out.println(" ");
                        break;
                    default:
                        break;
                }
                
            } while (option != 6);
            
        }
        else if (choice ==2) {
            //local scope variables
            ArrayList<String> accountList = new ArrayList<String>();
            String[] accountDetailsList;                            

            //read CSV file (database) and add the data to accountList
            try {
                File database = new File(accountsDatabaseName);
                Scanner databaseReader = new Scanner(database);
                while (databaseReader.hasNextLine()) {
                    String data = databaseReader.nextLine();
                    accountList.add(data);
                }
                databaseReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            //check if the database is empty, exit if it is
            if(accountList.size() <1){
                System.out.println("No existing accounts found, create new account.");
                return;
            }

            //print available accounts in the database
            System.out.println(" ");
            System.out.println("|------------------------------|"); 
            System.out.println("Account(s) found:");
            System.out.println(" ");
            for(int i =0; i<accountList.size();i++){
                String[] tempList = accountList.get(i).split(",");
                System.out.println(i+1+". "+tempList[0]);
            }
            System.out.print("Choose account (options: 1,2,...): ");
            option = mainInput.nextInt();

            //add chosen account details to the hashmap
            accountDetailsList = accountList.get(option-1).split(",");
            accountDetails.put("accountNumber", accountDetailsList[0].strip());
            accountDetails.put("firstName", accountDetailsList[1].strip());
            accountDetails.put("lastName", accountDetailsList[2].strip());
            accountDetails.put("accountType", accountDetailsList[3].strip());
            accountDetails.put("balance", accountDetailsList[4].strip());

            //set account details using the setDetails method
            account.setDetails(accountDetails.get("accountNumber"),accountDetails.get("firstName"),accountDetails.get("lastName"),accountDetails.get("accountType"),accountDetails.get("balance"));

            //print account details
            System.out.println(" ");
            System.out.println("|------------------------------|");
            System.out.println("Account Details");
            System.out.println(" ");
            System.out.println("Account Number: "+account.getAccountNumber());
            System.out.println("Account Holder Name: "+account.getAccountHolder());
            System.out.println("Account Type: " + account.getAccountType());
            System.out.println("Balance: "+account.getBalance()); 
            System.out.println(" ");

           //transaction event loop
            option =0;
            amount =0;
            do {
                System.out.println("|------------------------------|");
                System.out.println("Transact");
                System.out.println(" ");
                System.out.println("1. Withdraw");
                System.out.println("2. Deposit");
                System.out.println("3. Transfer");
                System.out.println("4. Balance");
                System.out.println("5. Transaction History");
                System.out.println("6. Quit");
                System.out.print("Choose transaction (options:1,2,3,4,5,6): ");
                option = mainInput.nextInt();
                mainInput.nextLine();
                switch(option){
                    case 1:
                        //withdrawal
                        accountID = account.getAccountNumber();
                        System.out.print("Withdraw amount: ");
                        amount = mainInput.nextInt();
                        account.withdraw(accountID,amount);
                        break;
                    case 2:
                        //deposit
                        accountID = account.getAccountNumber();
                        System.out.print("Deposit amount: ");
                        amount = mainInput.nextInt();
                        account.deposit(accountID,amount);
                        break;
                    case 3:
                        //transfer
                        System.out.println(" ");
                        System.out.print("Enter a 10 digit account number (eg: 1234567890): ");
                        accountID = mainInput.nextLine();
                        System.out.print("Enter amount to transfer: ");
                        amount = mainInput.nextFloat();
                        account.transfer(accountID, amount);
                        System.out.println(" ");
                        break;
                    case 4:
                        //check balance
                        System.out.println(" ");
                        System.out.println("|------------------------------|");
                        System.out.println("Account Balance");
                        System.out.println(" ");
                        System.out.println("Available funds: "+ account.getBalance());
                        System.out.println(" ");
                        break;
                    case 5:
                        //get transaction history
                        System.out.println(" ");
                        accountID = account.getAccountNumber();
                        account.getTransactions(accountID);
                        System.out.println(" ");
                        break;
                    case 6:
                        //exit transactions
                        System.out.println(" ");
                        System.out.println("***Thank you for banking with us***");
                        System.out.println(" ");
                        break;
                    default:
                        break;
                }
             
            } while (option != 6);
 
            
        }
        else{
            System.out.println("***Thank you for choosing THOBEKA BANKING***");
        }
                 
    }
}