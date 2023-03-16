public class ShinRamen extends Template {

    @Override
    public void powder() {
        System.out.println("라면 스프 넣기");
    }

    @Override
    public void topping() {

    }

    @Override
    public void time() {
       System.out.println("3분");
       try {
        Thread.sleep(3000); //3초 대기
       } catch (InterruptedException e) {
        e.printStackTrace();
       }
    }
}
