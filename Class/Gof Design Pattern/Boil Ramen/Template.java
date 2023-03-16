public abstract class Template {
    // final 선언으로 Override 방지
    public final void boilRamen() {
        boil();
        noodle();
        powder();
        topping();
        time();
        close();
    }

    // 기본으로 구현
    private void boil() {
        System.out.println("물 끓이는 중");
    }

    private void noodle() {
        System.out.println("면 넣기");
    }

    // 서브 클래스에서 직접 구현할 메소드
    public abstract void powder();
    public abstract void topping();
    public abstract void time();

    private void close() {
        System.out.println("라면 완성!");
    }
}
