import java.util.Scanner;

public class BankAccount{
    //instance variables
    private String accountNumber;
    private String firstName;
    private String lastName;
    private String accountHolder;
    private float balance;
    private String accountType;
    
    //constructor
    public BankAccount(){
        this.accountNumber ="1234567890";
        this.accountHolder = "default account";
        this.balance = 100;
        this.accountType = "cheque";
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
    public void setDetails(String fname,String lname){
        this.firstName = fname;
        this.lastName = lname;
        this.accountHolder = fname + " " + lname;
        this.accountNumber = "1234567890";
        this.balance = 100;
        
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

    public static void main(String[] args) {
        System.out.println(" ");
        System.out.println("***WELCOME TO THOBEKA BANKING, WHERE WE TAKE YOUR MONEY SERIOUSLY***");
        System.out.println("");
        System.out.println("1. New account");
        System.out.println("2. Existing account");
        System.out.println("3. Quit");
        int choice;
        BankAccount account = new BankAccount(); 
        @SuppressWarnings("resource")
        Scanner mainInput = new Scanner(System.in);
        System.out.print("Enter your choice: ");
        choice = mainInput.nextInt();
        mainInput.nextLine();

        if(choice ==1){
            String firstName;
            String lastName;
            int option =0;
            float amount;

            System.out.print("Enter your first Name: ");
            firstName = mainInput.nextLine();
            System.out.print("Enter your Last Name: ");
            lastName = mainInput.nextLine();

            account.setDetails(firstName,lastName);
            System.out.println(" ");
            System.out.println("|------------------------------|");
            System.out.println("Account Details");
            System.out.println("Account Number: "+account.getAccountNumber());
            System.out.println("Account Holder Name: "+account.getAccountHolder());
            System.out.println("Account Type: " + account.getAccountType());
            System.out.println("Balance: "+account.getBalance()); 
            System.out.println("|------------------------------|"); 
            System.out.println(" ");
            
            do {
                System.out.println("|------------------------------|");
                System.out.println("Transact");
                System.out.println("1. Withdraw");
                System.out.println("2. Deposit");
                System.out.println("3. Balance");
                System.out.println("4. Quit");
                System.out.print("Choose transaction: ");
                option = mainInput.nextInt();
                mainInput.nextLine();
                switch(option){
                    case 1:
                        System.out.print("Withdraw amount: ");
                        amount = mainInput.nextInt();
                        account.withdraw(amount);
                        break;
                    case 2:
                        
                        System.out.print("Deposit amount: ");
                        amount = mainInput.nextInt();
                        account.deposit(amount);
                        break;
                    case 3:
                        System.out.println(" ");
                        System.out.println("Available funds: "+ account.getBalance());
                        System.out.println(" ");
                        break;
                    case 4:
                        System.out.println(" ");
                        System.out.println("Thank you for banking with us");
                        System.out.println(" ");
                        break;
                    default:
                        break;
                }
                
            } while (option != 4);
            
        }
        else if (choice ==2) {
            
            System.out.println("Feature coming soon");
        }
        else{

            System.out.println("Thank you for choosing THOBEKA BANKING");
        }
                 
    }
}