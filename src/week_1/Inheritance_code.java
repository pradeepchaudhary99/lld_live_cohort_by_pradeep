package week_1;

abstract class Wallet {

    protected double balance;
    protected boolean isBlocked;

    public Wallet(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Invalid balance");
        }
        this.balance = initialBalance;
        this.isBlocked = false;
    }

    public void addMoney(double amount) {
        ensureActive();
        balance += amount;
    }

    public void deductMoney(double amount) {
        ensureActive();
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    protected void ensureActive() {
        if (isBlocked) {
            throw new IllegalStateException("Wallet is blocked");
        }
    }
}

class DigitalWallet extends Wallet {
    public DigitalWallet(double initialBalance) {
        super(initialBalance);
    }
}

class CashbackWallet extends Wallet {
    public CashbackWallet(double initialBalance) {
        super(initialBalance);
    }
    @Override
    public void addMoney(double amount) {
        super.addMoney(amount);
        balance += amount * 0.05;  // 5% cashback
    }
}

public class Inheritance_code {
    public static void main(String[] args) {
        Wallet wallet1 = new DigitalWallet(1000);
        Wallet wallet2 = new CashbackWallet(1000);

        wallet1.addMoney(100);
        wallet2.addMoney(100);

        System.out.println(wallet1.getBalance()); // 1100
        System.out.println(wallet2.getBalance()); // 1105
    }
}
