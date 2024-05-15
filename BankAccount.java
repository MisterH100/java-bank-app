import java.util.Scanner;
import java.io.File;  
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;

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
    public void withdraw(float withdrawAmount){
        if(withdrawAmount > this.balance){
            System.out.println(" ");
            System.out.println("Insufficient funds");
            System.out.println("Available funds: "+ this.balance);
            System.out.println(" ");
        } else {
            System.out.println(" ");
            this.balance = this.balance - withdrawAmount;
            System.out.println("Withdrawal success"+ " (-" + withdrawAmount + ")");
            System.out.println("New balance: "+ this.balance);
            System.out.println(" ");
        }
        
    }
    //deposit funds
    public void deposit(float depositAmount){
        this.balance = this.balance + depositAmount;
        System.out.println(" ");
        System.out.println("Deposit success" + " (+" + depositAmount +")");
        System.out.println("New balance: "+ this.balance);
        System.out.println(" ");
    }

    public void getTransactions(){
        
    }

    public static void main(String[] args) {
        int choice;
        int option;
        float amount;
        final String databaseName = "accounts_database.csv";
        generateAccountNumber accNumber = new generateAccountNumber();
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

            accountNumber = Long.toString(accNumber.generate());
            accountDetails.put("accountNumber",accountNumber);
            accountDetails.put("accountType",accountType);
            accountDetails.put("balance",initialBalance);

            //write details to CSV file (database)
            try {
                FileWriter myWriter = new FileWriter(databaseName,true);
                myWriter.write("\n"+accountDetails.get("accountNumber")+","+accountDetails.get("firstName")+","+accountDetails.get("lastName")+","+accountDetails.get("accountType")+","+accountDetails.get("balance"));
                myWriter.close();
                System.out.println("Successfully created account");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            
            //use setDetails method to set account details 
            account.setDetails(accountDetails.get("accountNumber"),accountDetails.get("firstName"),accountDetails.get("lastName"),accountDetails.get("accountType"),accountDetails.get("balance"));

            //print account details
            System.out.println(" ");
            System.out.println("|------------------------------|");
            System.out.println("Account Details");
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
                System.out.println("1. Withdraw");
                System.out.println("2. Deposit");
                System.out.println("3. Balance");
                System.out.println("4. Quit");
                System.out.print("Choose transaction (options:1,2,3,4): ");
                option = mainInput.nextInt();
                mainInput.nextLine();
                switch(option){
                    case 1:
                        //withdrawal
                        System.out.print("Withdraw amount: ");
                        amount = mainInput.nextInt();
                        account.withdraw(amount);
                        break;
                    case 2:
                        //deposit
                        System.out.print("Deposit amount: ");
                        amount = mainInput.nextInt();
                        account.deposit(amount);
                        break;
                    case 3:
                        //check balance
                        System.out.println(" ");
                        System.out.println("Available funds: "+ account.getBalance());
                        System.out.println(" ");
                        break;
                    case 4:
                        //exit transactions
                        System.out.println(" ");
                        System.out.println("***Thank you for banking with us***");
                        System.out.println(" ");
                        break;
                    default:
                        break;
                }
                
            } while (option != 4);
            
        }
        else if (choice ==2) {
            //local scope variables
            ArrayList<String> accountList = new ArrayList<String>();
            String[] accountDetailsList;

            //read CSV file (database) and add the data to accountList
            try {
                File database = new File(databaseName);
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
                System.out.println("1. Withdraw");
                System.out.println("2. Deposit");
                System.out.println("3. Balance");
                System.out.println("4. Quit");
                System.out.print("Choose transaction (options:1,2,3,4): ");
                option = mainInput.nextInt();
                mainInput.nextLine();
                switch(option){
                    case 1:
                        //withdrawal
                        System.out.print("Withdraw amount: ");
                        amount = mainInput.nextInt();
                        account.withdraw(amount);
                        break;
                    case 2:
                        //deposit
                        System.out.print("Deposit amount: ");
                        amount = mainInput.nextInt();
                        account.deposit(amount);
                        break;
                    case 3:
                        //check balance
                        System.out.println(" ");
                        System.out.println("Available funds: "+ account.getBalance());
                        System.out.println(" ");
                        break;
                    case 4:
                        //exit transactions
                        System.out.println(" ");
                        System.out.println("***Thank you for banking with us***");
                        System.out.println(" ");
                        break;
                    default:
                        break;
                }
                
            } while (option != 4);
            
        }
        else{
            System.out.println("***Thank you for choosing THOBEKA BANKING***");
        }
                 
    }
}