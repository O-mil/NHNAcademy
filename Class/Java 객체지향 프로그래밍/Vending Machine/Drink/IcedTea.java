package Drink;
public class IcedTea extends IcedTemplate {

    @Override
    public void drink() {
        System.out.println("물을 넣습니다.");
    }

    @Override
    public void syrup() {
        System.out.println("복숭아 티를 넣습니다.");
    }
}
