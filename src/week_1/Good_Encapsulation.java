//package week_1;
//
//class Wallet {
//
//    private double balance;
//    private boolean isBlocked;
//    private final String ownerName;
//
//    public Wallet(String ownerName, double initialBalance) {
//        if (initialBalance < 0) {
//            throw new IllegalArgumentException("Initial balance cannot be negative");
//        }
//        this.ownerName = ownerName;
//        this.balance = initialBalance;
//        this.isBlocked = false;
//    }
//
//    public double getBalance() {
//        return balance;
//    }
//
//    public boolean isBlocked() {
//        return isBlocked;
//    }
//
//    public String getOwnerName() {
//        return ownerName;
//    }
//
//    public void addMoney(double amount) {
//        ensureWalletIsActive();
//        if (amount <= 0) {
//            throw new IllegalArgumentException("Amount must be positive");
//        }
//        balance += amount;
//    }
//
//    public void deductMoney(double amount) {
//        ensureWalletIsActive();
//        if (amount <= 0) {
//            throw new IllegalArgumentException("Amount must be positive");
//        }
//        if (amount > balance) {
//            throw new IllegalArgumentException("Insufficient balance");
//        }
//        balance -= amount;
//    }
//
//    public void block() {
//        isBlocked = true;
//    }
//
//    private void ensureWalletIsActive() {
//        if (isBlocked) {
//            throw new IllegalStateException("Wallet is blocked");
//        }
//    }
//}
//
//public class Good_Encapsulation {
//    public static void main(String[] args) {
//        Wallet wallet = new Wallet("Rahul", 500);
//        wallet.addMoney(200);      //
//        wallet.deductMoney(300);   //
//        wallet.block();
//        wallet.addMoney(100);      //  Exception
//    }
//}
//
///*
//*   “The wallet now owns its data and its rules.”
//    “No one can put the wallet into an invalid state.”
//    “Encapsulation turns objects into self-protecting units.”
//*
//*/