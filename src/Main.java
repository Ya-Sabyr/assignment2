import java.util.LinkedList;
import java.util.Scanner;

class BankAccount {
    String accountNumber;
    String username;
    double balance;

    BankAccount(String accountNumber, String username, double balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.balance = balance;
    }
}

class MyStack {
    String[] arr;
    int top;
    int size;

    MyStack(int size) {
        this.size = size;
        arr = new String[size];
        top = -1;
    }

    void push(String value) {
        if (top == size - 1) {
            System.out.println("Stack is full");
            return;
        }
        top++;
        arr[top] = value;
    }

    String pop() {
        if (isEmpty()) {
            return "Stack is empty";
        }
        String value = arr[top];
        top--;
        return value;
    }

    String peek() {
        if (isEmpty()) {
            return "Stack is empty";
        }
        return arr[top];
    }

    boolean isEmpty() {
        return top == -1;
    }
}

class MyStringQueue {
    String[] arr;
    int front;
    int rear;
    int size;

    MyStringQueue(int size) {
        this.size = size;
        arr = new String[size];
        front = 0;
        rear = -1;
    }

    void add(String value) {
        if (rear == size - 1) {
            System.out.println("Queue is full");
            return;
        }
        rear++;
        arr[rear] = value;
    }

    String poll() {
        if (isEmpty()) {
            return "Queue is empty";
        }
        String value = arr[front];
        front++;
        return value;
    }

    String peek() {
        if (isEmpty()) {
            return "Queue is empty";
        }
        return arr[front];
    }

    boolean isEmpty() {
        return front > rear;
    }

    void show() {
        if (isEmpty()) {
            System.out.println("No bills");
            return;
        }

        for (int i = front; i <= rear; i++) {
            System.out.println(arr[i]);
        }
    }
}

class MyRequestQueue {
    BankAccount[] arr;
    int front;
    int rear;
    int size;

    MyRequestQueue(int size) {
        this.size = size;
        arr = new BankAccount[size];
        front = 0;
        rear = -1;
    }

    void add(BankAccount value) {
        if (rear == size - 1) {
            System.out.println("Request queue is full");
            return;
        }
        rear++;
        arr[rear] = value;
    }

    BankAccount poll() {
        if (isEmpty()) {
            return null;
        }
        BankAccount value = arr[front];
        front++;
        return value;
    }

    BankAccount peek() {
        if (isEmpty()) {
            return null;
        }
        return arr[front];
    }

    boolean isEmpty() {
        return front > rear;
    }

    void show() {
        if (isEmpty()) {
            System.out.println("No requests");
            return;
        }

        for (int i = front; i <= rear; i++) {
            System.out.println(arr[i].username + " - " + arr[i].accountNumber + " - " + arr[i].balance);
        }
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);

    static LinkedList<BankAccount> accounts = new LinkedList<>();
    static MyStack history = new MyStack(100);
    static MyStringQueue billQueue = new MyStringQueue(100);
    static MyRequestQueue requestQueue = new MyRequestQueue(100);

    static BankAccount[] accountArray = {
            new BankAccount("A101", "Ali", 150000),
            new BankAccount("A102", "Sara", 220000),
            new BankAccount("A103", "Dana", 180000)
    };

    static BankAccount findAccount(String username) {
        for (BankAccount acc : accounts) {
            if (acc.username.equalsIgnoreCase(username)) {
                return acc;
            }
        }
        return null;
    }

    static void addAccount() {
        System.out.print("Enter account number: ");
        String number = sc.nextLine();

        System.out.print("Enter username: ");
        String name = sc.nextLine();

        System.out.print("Enter balance: ");
        double balance = sc.nextDouble();
        sc.nextLine();

        accounts.add(new BankAccount(number, name, balance));
        System.out.println("Account added");
    }

    static void showAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts");
            return;
        }

        for (int i = 0; i < accounts.size(); i++) {
            BankAccount acc = accounts.get(i);
            System.out.println((i + 1) + ". " + acc.username + " - Balance: " + acc.balance);
        }
    }

    static void searchAccount() {
        System.out.print("Enter username: ");
        String name = sc.nextLine();

        BankAccount acc = findAccount(name);

        if (acc == null) {
            System.out.println("Account not found");
        } else {
            System.out.println("Found: " + acc.username + ", Balance: " + acc.balance);
        }
    }

    static void deposit() {
        System.out.print("Enter username: ");
        String name = sc.nextLine();

        BankAccount acc = findAccount(name);

        if (acc == null) {
            System.out.println("Account not found");
            return;
        }

        System.out.print("Enter money: ");
        double money = sc.nextDouble();
        sc.nextLine();

        if (money <= 0) {
            System.out.println("Wrong amount");
            return;
        }

        acc.balance = acc.balance + money;
        history.push("Deposit " + money + " to " + acc.username);

        System.out.println("New balance: " + acc.balance);
    }

    static void withdraw() {
        System.out.print("Enter username: ");
        String name = sc.nextLine();

        BankAccount acc = findAccount(name);

        if (acc == null) {
            System.out.println("Account not found");
            return;
        }

        System.out.print("Enter money: ");
        double money = sc.nextDouble();
        sc.nextLine();

        if (money <= 0) {
            System.out.println("Wrong amount");
        } else if (money > acc.balance) {
            System.out.println("Not enough balance");
        } else {
            acc.balance = acc.balance - money;
            history.push("Withdraw " + money + " from " + acc.username);
            System.out.println("New balance: " + acc.balance);
        }
    }

    static void addBill() {
        System.out.print("Enter bill name: ");
        String bill = sc.nextLine();

        billQueue.add(bill);
        history.push("Bill added: " + bill);

        System.out.println("Added: " + bill);
    }

    static void processBill() {
        if (billQueue.isEmpty()) {
            System.out.println("No bills");
        } else {
            System.out.println("Processing: " + billQueue.poll());
        }
    }

    static void showBills() {
        if (billQueue.isEmpty()) {
            System.out.println("No bills");
            return;
        }

        System.out.println("Bills in queue:");
        billQueue.show();
    }

    static void lastTransaction() {
        if (history.isEmpty()) {
            System.out.println("No transactions");
        } else {
            System.out.println("Last transaction: " + history.peek());
        }
    }

    static void undoTransaction() {
        if (history.isEmpty()) {
            System.out.println("No transactions");
        } else {
            System.out.println("Removed: " + history.pop());
        }
    }

    static void addRequest() {
        System.out.print("Enter account number: ");
        String number = sc.nextLine();

        System.out.print("Enter username: ");
        String name = sc.nextLine();

        System.out.print("Enter balance: ");
        double balance = sc.nextDouble();
        sc.nextLine();

        requestQueue.add(new BankAccount(number, name, balance));
        System.out.println("Request added");
    }

    static void processRequest() {
        if (requestQueue.isEmpty()) {
            System.out.println("No requests");
        } else {
            BankAccount acc = requestQueue.poll();
            accounts.add(acc);
            System.out.println("Request processed for " + acc.username);
        }
    }

    static void showRequests() {
        if (requestQueue.isEmpty()) {
            System.out.println("No requests");
            return;
        }

        System.out.println("Pending requests:");
        requestQueue.show();
    }

    static void showArrayAccounts() {
        System.out.println("Array accounts:");
        for (int i = 0; i < accountArray.length; i++) {
            System.out.println((i + 1) + ". " + accountArray[i].username + " - Balance: " + accountArray[i].balance);
        }
    }

    static void bankMenu() {
        int choice = 0;

        while (choice != 5) {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Account request");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Add bill");
            System.out.println("5. Back");
            System.out.print("Choose: ");
            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                addRequest();
            } else if (choice == 2) {
                deposit();
            } else if (choice == 3) {
                withdraw();
            } else if (choice == 4) {
                addBill();
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Wrong choice");
            }
        }
    }

    static void atmMenu() {
        int choice = 0;

        while (choice != 3) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Back");
            System.out.print("Choose: ");
            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter username: ");
                String name = sc.nextLine();
                BankAccount acc = findAccount(name);

                if (acc == null) {
                    System.out.println("Account not found");
                } else {
                    System.out.println("Balance: " + acc.balance);
                }
            } else if (choice == 2) {
                withdraw();
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Wrong choice");
            }
        }
    }

    static void adminMenu() {
        int choice = 0;

        while (choice != 5) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Show requests");
            System.out.println("2. Process request");
            System.out.println("3. Show bill queue");
            System.out.println("4. Process bill");
            System.out.println("5. Back");
            System.out.print("Choose: ");
            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                showRequests();
            } else if (choice == 2) {
                processRequest();
            } else if (choice == 3) {
                showBills();
            } else if (choice == 4) {
                processBill();
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Wrong choice");
            }
        }
    }

    static void enterMenus() {
        int menuChoice = 0;

        while (menuChoice != 4) {
            System.out.println("\n1. Bank");
            System.out.println("2. ATM");
            System.out.println("3. Admin");
            System.out.println("4. Back");
            System.out.print("Choose: ");
            menuChoice = sc.nextInt();
            sc.nextLine();

            if (menuChoice == 1) {
                bankMenu();
            } else if (menuChoice == 2) {
                atmMenu();
            } else if (menuChoice == 3) {
                adminMenu();
            } else if (menuChoice == 4) {
                break;
            } else {
                System.out.println("Wrong choice");
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < accountArray.length; i++) {
            accounts.add(accountArray[i]);
        }

        int choice = 0;

        while (choice != 8) {
            System.out.println("\n=== Banking System ===");
            System.out.println("1. Add account");
            System.out.println("2. Show accounts");
            System.out.println("3. Search account");
            System.out.println("4. Show last transaction");
            System.out.println("5. Undo last transaction");
            System.out.println("6. Show array accounts");
            System.out.println("7. Enter menus");
            System.out.println("8. Exit");
            System.out.print("Choose: ");
            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                addAccount();
            } else if (choice == 2) {
                showAccounts();
            } else if (choice == 3) {
                searchAccount();
            } else if (choice == 4) {
                lastTransaction();
            } else if (choice == 5) {
                undoTransaction();
            } else if (choice == 6) {
                showArrayAccounts();
            } else if (choice == 7) {
                enterMenus();
            } else if (choice == 8) {
                System.out.println("Goodbye");
            } else {
                System.out.println("Wrong choice");
            }
        }
    }
}