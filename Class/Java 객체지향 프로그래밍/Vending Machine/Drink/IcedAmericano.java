package Drink;
public class IcedAmericano extends IcedTemplate {

    @Override
    public void drink() {
        System.out.println("물을 넣습니다.");
    }

    @Override
    public void syrup() {
        System.out.println("샷을 넣습니다.");
    }
}
