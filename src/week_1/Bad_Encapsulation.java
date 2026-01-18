//package week_1;
//
//class Wallet {
//    public double balance;
//    public boolean isBlocked;
//    public String ownerName;
//}
//
//class WalletService {
//    public void addMoney(Wallet wallet, double amount) {
//        wallet.balance += amount;
//    }
//    public void deductMoney(Wallet wallet, double amount) {
//        wallet.balance -= amount;
//    }
//}
//public class Bad_Encapsulation {
//    public static void main(String[] args) {
//
//        Wallet wallet = new Wallet();
//        wallet.ownerName = "Rahul";
//        wallet.balance = 500;
//        wallet.isBlocked = false;
//
//        WalletService service = new WalletService();
//
//        service.addMoney(wallet, 200);
//        service.deductMoney(wallet, 1000);   // negative balance
//
//        wallet.balance = 1_000_000;          // free money
//        wallet.isBlocked = false;            // unblock manually
//    }
//}
