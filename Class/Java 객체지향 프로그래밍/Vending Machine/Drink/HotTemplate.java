package Drink;
public abstract class HotTemplate {
    public final void machine() {
        cup();
        syrup();
        drink();
        close();
    }

    private void cup() {
        System.out.println("종이컵을 받습니다.");
    }
    public abstract void syrup();
    public abstract void drink();

    private void close() {
        System.out.println("음료 나왔습니다.");
    }
}
