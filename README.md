# Budget-Tracker-Project

# Budget Tracker Application

## Overview

The Budget Tracker is a Java console application that helps users manage their financial transactions. It allows you to add, edit, view, and list transactions, providing a comprehensive summary of your income, expenses, and savings. The application supports categorizing transactions and tracking recurring expenses.

---

## Features

1. **User Authentication:**

   - Users can register with their email and password.
   - Login is required to access the budget tracker functionalities.
   - Email validation is performed using regex.

2. **Add Transactions**  
   - Record financial transactions with details such as date, amount, description, type (Income or Expenses), category, and recurrence status.

3. **Edit Transactions**  
   - Modify existing transactions to update their details.

4. **View Financial Summary**  
   - Get an overview of total income, expenses, recurring expenses, and savings.

5. **List Transactions**  
   - Display all recorded transactions in a tabular format.

6. **Recurring Expense Tracking**  
   - Identify transactions marked as recurring expenses.

---

## How to Use

### 1. Running the Application
- Compile and run the application using the following commands in your terminal:
  ```bash
  javac Transaction.java
  java Transaction
  ```

### 2. Main Menu Options
After starting the application, you will see the following menu:
```
--- Budget Tracker Menu ---
1. Add Transaction
2. Edit Transaction
3. View Summary
4. List Transactions
5. Exit
Enter your choice:
```
- Enter the corresponding number to perform the desired operation.

### 3. Adding a Transaction
- Provide the details for the transaction, including:
  - Date (in `yyyy-MM-dd` format).
  - Amount (positive value).
  - Description (e.g., "Groceries").
  - Transaction Type (`Income` or `Expenses`).
  - Category (e.g., `Salary`, `Tax`).
  - Recurrence status (`true` or `false`).

### 4. Editing a Transaction
- Enter the index of the transaction you wish to edit.
- Update the details for the selected transaction.

### 5. Viewing the Financial Summary
- Displays:
  - Total Income
  - Total Expenses
  - Recurring Expenses
  - Total Savings
  - Alerts if your expenses exceed income.

### 6. Listing Transactions
- Displays all transactions in a tabular format, showing:
  - Date
  - Amount
  - Description
  - Category
  - Type
  - Recurrence Status

---

## Code Highlights
### 1. `Transaction` Class
- Encapsulates all details related to a transaction, such as date, amount, type, and category.
- Includes getter and setter methods for managing transaction properties.

### 2. `Tracker` Class
- Manages the list of transactions.
- Tracks totals for income, expenses, and recurring costs.
- Implements methods to add, edit, display, and list transactions.

### 3. Input Validation
- Ensures inputs like date, amount, and description are valid before processing.
- Prevents invalid data entry to maintain data integrity.

---

## Error Handling
- Halling invalid login details such as password or E-mail or both 
- Handles invalid inputs such as incorrect date formats or out-of-range indices.
- Guides users with error messages for retrying.

### Author: 
- Name: Ajayi Opeyemi Joseph
- Email: ajayiopeyemi90@yahoo.com
- Facebook: https://www.facebook.com/Horpeyemi90
- LinkedIn: https://www.linkedin.com/in/opeyemi-ajayi-117305124
---
