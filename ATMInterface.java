import java.util.Scanner;

public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATMSystem atmSystem = new ATMSystem();

        System.out.println("Welcome to the ATM!");
        System.out.print("User ID: ");
        String userId = scanner.nextLine();
        System.out.print("PIN: ");
        String pin = scanner.nextLine();

        if (atmSystem.login(userId, pin)) {
            boolean quit = false;
            while (!quit) {
                System.out.println("\nSelect an option:");
                System.out.println("1. Transaction History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");

                int option = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (option) {
                    case 1:
                        atmSystem.showTransactionHistory();
                        break;
                    case 2:
                        System.out.print("Enter the amount to withdraw: $");
                        double withdrawAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline character
                        atmSystem.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter the amount to deposit: $");
                        double depositAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline character
                        atmSystem.deposit(depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter the recipient's user ID: ");
                        String recipientId = scanner.nextLine();
                        System.out.print("Enter the amount to transfer: $");
                        double transferAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline character
                        atmSystem.transfer(recipientId, transferAmount);
                        break;
                    case 5:
                        quit = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid user ID or PIN. Exiting the ATM.");
        }
    }
}

class ATMSystem {
    private String userId;
    private String pin;
    private double balance;
    private StringBuilder transactionHistory;

    public ATMSystem() {
        // Initialize with default values
        userId = "Kamakhya";
        pin = "12345";
        balance = 0.0;
        transactionHistory = new StringBuilder();
    }

    public boolean login(String userId, String pin) {
        return this.userId.equals(userId) && this.pin.equals(pin);
    }

    public void showTransactionHistory() {
        if (transactionHistory.length() == 0) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Transaction History:");
            System.out.println(transactionHistory.toString());
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return;
        }

        if (balance < amount) {
            System.out.println("Insufficient funds.");
            return;
        }

        balance -= amount;
        String transaction = "Withdrawal: -$" + amount;
        transactionHistory.append(transaction).append("\n");

        System.out.println("Successfully withdrew $" + amount);
        System.out.println("Current Balance: $" + balance);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }

        balance += amount;
        String transaction = "Deposit: +$" + amount;
        transactionHistory.append(transaction).append("\n");

        System.out.println("Successfully deposited $" + amount);
        System.out.println("Current Balance: $" + balance);
    }

    public void transfer(String recipientId, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid transfer amount.");
            return;
        }

        if (balance < amount) {
            System.out.println("Insufficient funds.");
            return;
        }

        balance -= amount;
        String transaction = "Transfer to " + recipientId + ": -$" + amount;
        transactionHistory.append(transaction).append("\n");

        System.out.println("Successfully transferred $" + amount + " to " + recipientId);
        System.out.println("Current Balance: $" + balance);
    }
}
