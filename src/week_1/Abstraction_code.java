//package week_1;
//
//
//interface Wallet {
//    void addMoney(double amount);
//    void deductMoney(double amount);
//    double getBalance();
//    boolean isBlocked();
//}
//
//// Interface defines what a wallet can do, not how..
//// Stack, Queue
////
//
//class DigitalWallet implements Wallet {
//
//    private double balance;
//    private boolean isBlocked;
//
//    public DigitalWallet(double initialBalance) {
//        if (initialBalance < 0) {
//            throw new IllegalArgumentException("Invalid balance");
//        }
//        this.balance = initialBalance;
//        this.isBlocked = false;
//    }
//
//    public void addMoney(double amount) {
//        ensureActive();
//        balance += amount;
//    }
//
//    public void deductMoney(double amount) {
//        ensureActive();
//        if (amount > balance) {
//            throw new IllegalArgumentException("Insufficient balance");
//        }
//        balance -= amount;
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
//    private void ensureActive() {
//        if (isBlocked) {
//            throw new IllegalStateException("Wallet is blocked");
//        }
//    }
//}
//
//
//
//public class Abstraction_code {
//
//}
