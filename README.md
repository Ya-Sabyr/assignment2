# Assignment 2 - Banking System

**Name:** Your Name  
**Group:** Your Group

This project is a small Java banking system for Assignment 2 on physical and logical data structures. The whole implementation is in [src/Main.java](src/Main.java).

## Code Overview

- `BankAccount` stores `accountNumber`, `username`, and `balance`.
- `LinkedList<BankAccount> accounts` stores the main list of bank accounts.
- `MyStack` stores transaction history using an array.
- `MyStringQueue` stores bill payments in FIFO order.
- `MyRequestQueue` stores account opening requests before the admin approves them.
- `BankAccount[] accountArray` stores 3 predefined accounts for the physical data structure part.

## Where Each Task Is Implemented

| Task | Implementation | Explanation | Big-O |
| --- | --- | --- | --- |
| Task 1. Bank account storage using `LinkedList` | `BankAccount` at lines `4-14`, `accounts` at line `168`, `findAccount()` at `179-186`, `addAccount()` at `188-201`, `showAccounts()` at `203-213`, `searchAccount()` at `215-226` in [src/Main.java](src/Main.java) | Adds accounts, displays all accounts, and searches by username. | Add account: `O(1)`. Search: `O(n)`. Display all accounts in the current code: `O(n^2)` because it uses `LinkedList.get(i)` inside a loop. |
| Task 2. Deposit and withdraw | `deposit()` at `228-252` and `withdraw()` at `254-278` in [src/Main.java](src/Main.java) | Finds an account, updates the balance, and stores the action in transaction history. | Deposit: `O(n)`. Withdraw: `O(n)`. |
| Task 3. Transaction history (`Stack`) | `MyStack` at `16-55`, `history` at line `169`, `lastTransaction()` at `308-314`, `undoTransaction()` at `316-322` in [src/Main.java](src/Main.java) | Saves deposit, withdraw, and bill actions and supports peek/pop behavior. | `push`: `O(1)`, `pop`: `O(1)`, `peek`: `O(1)`. |
| Task 4. Bill payment queue (`Queue`) | `MyStringQueue` at `57-109`, `billQueue` at line `170`, `addBill()` at `280-288`, `processBill()` at `290-296`, `showBills()` at `298-306` in [src/Main.java](src/Main.java) | Adds bills in FIFO order, processes the next bill, and shows pending bills. | Add bill: `O(1)`, process next bill: `O(1)`, display queue: `O(k)`. |
| Task 5. Account opening queue | `MyRequestQueue` at `111-163`, `requestQueue` at line `171`, `addRequest()` at `324-337`, `processRequest()` at `339-347`, `showRequests()` at `349-357` in [src/Main.java](src/Main.java) | Stores new account requests and moves approved requests into the main `LinkedList`. | Add request: `O(1)`, process request: `O(1)`, display pending requests: `O(k)`. |
| Task 6. Physical data structure with array | `accountArray` at `173-177`, startup loading in `main()` at `484-487`, `showArrayAccounts()` at `359-364` in [src/Main.java](src/Main.java) | Creates 3 predefined accounts, prints them, and loads them into the main account list at program start. | Access by index: `O(1)`, print all array accounts: `O(n)`, copy array to list at startup: `O(n)`. |
| Part 3. Mini banking menu | `bankMenu()` at `366-394`, `atmMenu()` at `396-426`, `adminMenu()` at `428-456`, `enterMenus()` at `458-482`, `main()` at `484-526` in [src/Main.java](src/Main.java) | Integrates the bank, ATM, and admin workflows into one console program. | Menu selection itself is `O(1)` per choice; total complexity depends on the selected operation. |

## Brief Work Summary

The program combines logical data structures with a simple banking simulation. The main account storage is a `LinkedList`, transaction history is handled by a stack, bill payments and account requests are handled by queues, and predefined accounts are stored in an array. The menus connect these parts so the user can act as a bank client, ATM user, or admin.

## Run

```bash
javac src/Main.java
java -cp src Main
```

## Note

In this implementation, the stack and queues are custom array-based classes instead of Java built-in `Stack` or `Queue<LinkedList>`. Also, `showAccounts()` is slower than expected for a `LinkedList` because it accesses elements by index.
