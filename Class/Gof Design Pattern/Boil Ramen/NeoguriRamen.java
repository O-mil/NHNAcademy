public class NeoguriRamen extends Template {

    @Override
    public void powder() {
        System.out.println("라면 스프 넣기");
    }

    @Override
    public void topping() {
        System.out.println("다시마 넣기");
    }

    @Override
    public void time() {
        System.out.println("4분");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
