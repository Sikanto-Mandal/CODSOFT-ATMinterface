import java.util.Scanner;

public class SavingsAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String pin;

    public SavingsAccount(String accountNumber, String accountHolderName, double initialBalance, String pin) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }

    public void setPin(String newPin) {
        this.pin = newPin;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }
}

public class ATM {
    private SavingsAccount savingsAccount;
    private String bankName;

    public ATM(SavingsAccount savingsAccount, String bankName) {
        this.savingsAccount = savingsAccount;
        this.bankName = bankName;
    }

    public double checkBalance() {
        return savingsAccount.getBalance();
    }

    public void deposit(double amount) {
        if (amount > 0) {
            savingsAccount.deposit(amount);
            System.out.println("Deposit successful. Deposited: $" + amount);
            System.out.println("New Balance: $" + savingsAccount.getBalance());
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0) {
            double currentBalance = savingsAccount.getBalance();
            if (amount <= currentBalance) {
                savingsAccount.withdraw(amount);
                System.out.println("Withdrawal successful. Withdrawn: $" + amount);
                System.out.println("New Balance: $" + savingsAccount.getBalance());
            } else {
                System.out.println("Insufficient balance for withdrawal.");
            }
        } else {
            System.out.println("Invalid withdrawal amount. Please enter a positive amount.");
        }
    }

    public void changePin(String newPin) {
        if (newPin.length() == 4) {
            savingsAccount.setPin(newPin);
            System.out.println("PIN changed successfully.");
        } else {
            System.out.println("Invalid PIN. Please enter a 4-digit PIN.");
        }
    }

    public static void main(String[] args) {
        SavingsAccount userAccount = new SavingsAccount("1234567890", "Sikanto Mandal", 1000.0, "1234");
        ATM atm = new ATM(userAccount, "My Bank");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to " + atm.bankName + "!");
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Change PIN");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("Your balance is: $" + atm.checkBalance());
                    break;
                case 2:
                    System.out.print("Enter the deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter the withdrawal amount: $");
                    double withdrawalAmount = scanner.nextDouble();
                    atm.withdraw(withdrawalAmount);
                    break;
                case 4:
                    System.out.print("Enter a new 4-digit PIN: ");
                    String newPin = scanner.next();
                    atm.changePin(newPin);
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
