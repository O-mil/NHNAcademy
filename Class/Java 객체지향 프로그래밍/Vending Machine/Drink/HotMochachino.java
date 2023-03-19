package Drink;
public class HotMochachino extends HotTemplate {

    @Override
    public void syrup() {
        System.out.println("초코를 넣습니다.");
        System.out.println("샷을 넣습니다.");
    }

    @Override
    public void drink() {
        System.out.println("따듯한 우유를 넣습ㄴ다.");
    }

}
