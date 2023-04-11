public class BankAccount {
    public int balance;
    public int getBalance;

    public void withDraw(int amount) {
        if (amount > this.balance) {
            System.out.println("Ins... fund");
            return;
        }
        this.balance -= amount;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    public int getBalance() {
        return this.balance;
    }

}

class Test {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        System.out.println(account);
    }
}
