package Pay;
public class Online extends PayTemplate {

    @Override
    public void pay() {
        System.out.println("온라인으로 결제를 진행합니다.");
        System.out.println("true");
    }
}