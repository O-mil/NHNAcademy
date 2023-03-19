package Pay;
public class Credit extends PayTemplate {

    @Override
    public void pay() {
        System.out.println("신용카드로 결제를 진행합니다. 카드를 꽂아주세요.");
        System.out.println("true");
    }


}
