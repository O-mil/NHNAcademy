package Pay;
public abstract class PayTemplate {

    public final void machine() {
        pay();
        close();
    }

    public abstract void pay();

    private void close() {
        System.out.println("결제가 완료되었습니다.");
    }

}