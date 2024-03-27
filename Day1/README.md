OOP/Collections
Exercise 00: Models
Turn-in directory: ex00
Files to turn-in: User.java, Transaction.java, Program.java

Task: Develop basic domain models - User and Transaction classes.

User class:

Fields: Identifier, Name, Balance
Transaction class:

Fields: Identifier, Recipient (User type), Sender (User type), Transfer category, Transfer amount
Ensure user balance is non-negative, and transactions are valid.

Exercise 01 – ID Generator
Turn-in directory: ex01
Files to turn-in: UserIdsGenerator.java, User.java, Program.java

Task: Create UserIdsGenerator class to generate unique user IDs.

Use autoincrement principle for ID generation. 
Ensure uniqueness of user IDs.
Example usage in Program file.

Exercise 02 – List of Users
Turn-in directory: ex02
Files to turn-in: UsersList.java, UsersArrayList.java, User.java, Program.java, etc.

Task: Implement storing users functionality.

Define UsersList interface with methods to add, retrieve users by ID/index, and get number of users.
Implement UsersArrayList class using an array.
Handle UserNotFoundException for non-existent user IDs.

Exercise 03 – List of Transactions
Turn-in directory: ex03
Files to turn-in: TransactionsList.java, TransactionsLinkedList.java, User.java, Program.java, etc.

Task: Implement storing transactions functionality.

Define TransactionsList interface with methods to add, remove transactions by ID, and transform into array.
Implement TransactionsLinkedList class using a linked list.
Handle TransactionNotFoundException for non-existent transaction IDs.

Exercise 04 – Business Logic
Turn-in directory: ex04
Files to turn-in: TransactionsService.java, Program.java, etc.

Task: Implement business logic for user interactions.

TransactionsService class with functionalities like adding users, retrieving user balances, performing transactions, etc.
Handle IllegalTransactionException for invalid transactions.

Exercise 05 – Menu
Turn-in directory: ex05
Files to turn-in: Menu.java, Program.java, etc.

Task: Create a console menu for the application.

Each menu item accompanied by a command number.
Support production and dev modes.
Handle exceptions gracefully and provide user-friendly error messages.
Example scenario in Program file with detailed user interactions.