import java.math.BigDecimal;

class Test {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("Jason", new BigDecimal(100));
        account.printAccount();
        BankAccount account2 = new LimitedBankAccount("James", new BigDecimal(1000), new BigDecimal(1000));
        account2.printAccount();
    }
}
