package Drink;
public abstract class IcedTemplate {

    public final void machine() {
        cup();
        drink();
        syrup();
        close();
    }

    private void cup() {
        System.out.println("플라스틱 컵을 받습니다.");
        System.out.println("얼음을 받습니다.");
    }

    public abstract void drink();
    public abstract void syrup();

    private void close() {
        System.out.println("음료 나왔습니다.");
    }
}
