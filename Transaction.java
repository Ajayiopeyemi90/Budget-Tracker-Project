import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.time.format.DateTimeParseException;

public class Transaction {
    private LocalDate date;
    private double amount;
    private String description;
    private String category;
    private String transactionType;
    private boolean isRecurring;

    public Transaction(LocalDate date, double amount, String description, String transactionType, String category) {
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.transactionType = transactionType;
        this.category = category;
        this.isRecurring = false;
    }
    
       //getter and setter for date 
    public LocalDate getDate(){
        return date;
    }
     public void setDate(LocalDate date){
        this.date = date;
    }
    
    // getter and setter for Amount
    private double getAmount(){
        return amount;
    }
    
     public void setAmount(double amount){
        this.amount = amount;
    }
    
    //  Getter and setter for DiscriptionDiscription
     private String getDescription(){
        return description;
    }
    
     public void setDescription(String description){
        this.description = description;
    }
    
       //  Getter and setter for BudgetType
    private String getTransactionType(){
        return transactionType;
    }
    
     public void setTransactionType(String transactionType){
        this.transactionType = transactionType;
    }
    
    //Getter and setter for category;
    public String getCategory() { 
        return category; }
    
    public void setCategory(String category) { 
        this.category = category; }
        
          //Getter and setter for RecurringCost
     public boolean getRecurring() {
        return isRecurring;
     }
     
     public void setRecurring(boolean isRecurring) {
        this.isRecurring = isRecurring;
     }
     
    @Override
    public String toString() {
        return "Date: " + date +
                "\nAmount: " + amount +
                "\nDescription: " + description +
                "\nType: " + transactionType +
                "\nCategory: " + category +
                "\nRecurring: " + (isRecurring ? "Yes" : "No");
    }

    public static class Tracker {
        private ArrayList<Transaction> transactions;
        private double income;
        private double expenses;
        private double recurringCost;

        public Tracker() {
            transactions = new ArrayList<>();
            income = 0;
            expenses = 0;
            recurringCost = 0;
        }

        public void addTransaction(LocalDate date, double amount, String description, String transactionType, String category, boolean isRecurring) {
            Transaction transaction = new Transaction(date, amount, description, transactionType, category);
            transactions.add(transaction);

            if (transactionType.equalsIgnoreCase("Income")) {
                income += amount;
            } else if (transactionType.equalsIgnoreCase("Expenses")) {
                expenses += amount;
                if (isRecurring) {
                    recurringCost += amount;
                }
            }
        }
        
        // Edit a transaction
    public void editTransaction(int index, LocalDate newDate, double newAmount, String newDescription, String newTransactionType, String newCategory, boolean isRecurring) {
        if (index >= 0 && index < transactions.size()) {
            Transaction oldTransaction = transactions.get(index);

            // Adjust totals for the old transaction
            if (oldTransaction.getTransactionType().equalsIgnoreCase("Income")) {
                income -= oldTransaction.getAmount();
            } else if (oldTransaction.getTransactionType().equalsIgnoreCase("Expenses")) {
                expenses -= oldTransaction.getAmount();
                if (oldTransaction.getRecurring()) {
                    recurringCost -= oldTransaction.getAmount();
                }
            }

            // Update the transaction details
            oldTransaction.setDate(newDate);
            oldTransaction.setAmount(newAmount);
            oldTransaction.setDescription(newDescription);
            oldTransaction.setTransactionType(newTransactionType);
            oldTransaction.setCategory(newCategory);
            oldTransaction.setRecurring(isRecurring);

            // Adjust totals for the new transaction
            if (newTransactionType.equalsIgnoreCase("Income")) {
                income += newAmount;
            } else if (newTransactionType.equalsIgnoreCase("Expenses")) {
                expenses += newAmount;
                if (isRecurring) {
                    recurringCost += newAmount;
                }
            }
        } else {
            System.out.println("Invalid transaction index.");
        }
    }

        public void displayTransaction() {
            System.out.println("Total Income: " + income);
            System.out.println("Total Expenses: " + expenses);
            System.out.println("Recurring Expenses: " + recurringCost);
            System.out.println("Savings: " + (income - expenses));
        }

        public void listTransaction() {
            if (transactions.isEmpty()) {
                System.out.println("No transactions to display.");
                return;
            }
            System.out.printf("%-5s %-12s %-10s %-15s %-15s %-10s %-10s%n",
                    "S/N", "Date", "Amount", "Description", "Category", "Type", "Recurring");
            System.out.println("--------------------------------------------------------------------------");
            int index = 1;
            for (Transaction t : transactions) {
                System.out.printf("%-5d %-12s %-10.2f %-15s %-15s %-10s %-10s%n",
                        index++, t.date, t.amount, t.description, t.category, t.transactionType, t.isRecurring ? "Yes" : "No");
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        HashMap<String, String> users = new HashMap<>();
        Tracker tracker = new Tracker();

        // Email and password regex for validation
        String emailRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        boolean loggedIn = false;

        // User authentication
        while (!loggedIn) {
            System.out.println("\n--- Welcome to Budget Tracker ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume newline character

            if (choice == 1) {
                System.out.print("Enter your first name: ");
                String firstNname = input.nextLine();
                
                System.out.print("Enter your last name: ");
                String lastName = input.nextLine();
                
                
                System.out.print("Enter your email: ");
                String email = input.nextLine();

                // Validate email
                if (!emailPattern.matcher(email).matches()) {
                    System.out.println("Invalid email format. Please try again.");
                    continue;
                }

                if (users.containsKey(email)) {
                    System.out.println("Email already registered. Please log in.");
                } else {
                    System.out.print("Enter a password: ");
                    String password = input.nextLine();
                    users.put(email, password);
                    System.out.println("Registration successful! You can now log in.");
                }
            } else if (choice == 2) {
                System.out.print("Enter your email: ");
                String email = input.nextLine();
                System.out.print("Enter your password: ");
                String password = input.nextLine();

                if (users.containsKey(email) && users.get(email).equals(password)) {
                    System.out.println("Login successful! Welcome, " + email + ".");
                    loggedIn = true; // Set login flag to true
                } else {
                    System.out.println("Invalid email or password. Please try again.");
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        // Budget Tracker Menu
        while (true) {
            System.out.println("\n--- Budget Tracker Menu ---");
            System.out.println("1. Add Transaction");
            System.out.println("2. Edit Transaction");
            System.out.println("3. View Summary");
            System.out.println("4. List Transactions");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume newline character

            if (choice == 1) {
                try {
                    System.out.print("Enter date (yyyy-MM-dd): ");
                    LocalDate date = LocalDate.parse(input.nextLine());
                    
                    // input for amount
                    double amount;
                    do {System.out.print("Enter amount: ");
                        amount = input.nextDouble();
                        input.nextLine(); // Consume newline character
                            if (amount <= 0){
                                System.out.println("Enter a valid amount");
                        }
                    } while (amount <= 0);
                    
                    // input for description
                    System.out.print("Enter description: ");
                    String description;
                    do {description = input.nextLine().trim();
                         if (description.isEmpty()){
                             System.out.println("Enter description:");
                         }
                    }while (description.isEmpty());
                    
                     // input for transaction type
                    System.out.print("Enter transaction type (Income/Expenses): ");
                    String transactionType;
                    do {transactionType = input.nextLine().trim();
                         if (transactionType.isEmpty()){
                             System.out.println("Enter transaction type (Income/Expenses)");
                         }
                    }while (transactionType.isEmpty());
                    
                    // input for category
                    System.out.print("Enter category: ");
                    String category;
                    do {category = input.nextLine().trim();
                         if (category.isEmpty()){
                             System.out.println("Enter category: ");
                         }
                    }while (category.isEmpty());

                    System.out.print("Is this a recurring transaction? (true/false): ");
                    boolean isRecurring = input.nextBoolean();

                    tracker.addTransaction(date, amount, description, transactionType, category, isRecurring);
                    System.out.println("Transaction added successfully!");
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
                } catch (Exception e) {
                    System.out.println("An error occurred: " + e.getMessage());
                }

            } else if (choice == 2) {
                System.out.print("Enter the index of the transaction to edit: ");
                int index = input.nextInt();
                input.nextLine(); // Consume newline character

                try {
                    System.out.print("Enter new date (yyyy-MM-dd): ");
                    LocalDate newDate = LocalDate.parse(input.nextLine());
                    
                     double newAmount;
                    do {System.out.print("Enter new amount: ");
                        newAmount = input.nextDouble();
                        input.nextLine(); // Consume newline character
                            if (newAmount <= 0){
                                System.out.println("Enter a valid amount");
                        }
                    } while (newAmount <= 0);
                    
                    // input for description
                    System.out.print("Enter new description: ");
                    String newDescription;
                    do {newDescription = input.nextLine().trim();
                         if (newDescription.isEmpty()){
                             System.out.println("Enter new description:");
                         }
                    }while (newDescription.isEmpty());
                    
                     // input for transaction type
                    System.out.print("Enter new transaction type (Income/Expenses): ");
                    String newTransactionType;
                    do {newTransactionType = input.nextLine().trim();
                         if (newTransactionType.isEmpty()){
                             System.out.println("Enter new transaction type (Income/Expenses)");
                         }
                    }while (newTransactionType.isEmpty());
                    
                    // input for category
                    System.out.print("Enter new category: ");
                    String newCategory;
                    do {newCategory = input.nextLine().trim();
                         if (newCategory.isEmpty()){
                             System.out.println("Enter new category: ");
                         }
                    }while (newCategory.isEmpty());
                   
                    System.out.print("Is this a recurring transaction? (true/false): ");
                    boolean isRecurring = input.nextBoolean();

                    tracker.editTransaction(index, newDate, newAmount, newDescription, newTransactionType, newCategory, isRecurring);
                    System.out.println("Transaction edited successfully!");
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid transaction index.");
                } catch (Exception e) {
                    System.out.println("An error occurred: " + e.getMessage());
                }

            } else if (choice == 3) {
                tracker.displayTransaction();

            } else if (choice == 4) {
                tracker.listTransaction();

            } else if (choice == 5) {
                System.out.println("Exiting... Thank you!");
                break;

            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        input.close();
    }
}
