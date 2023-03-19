package Drink;
public class IcedChoco extends IcedTemplate {

    @Override
    public void drink() {
        System.out.println("우유를 넣습니다.");
    }

    @Override
    public void syrup() {
        System.out.println("초코를 넣습니다.");
    }
}
