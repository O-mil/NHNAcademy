public class Test {
    public static void main(String[] args) {
        Template template = new ShinRamen();
        template.boilRamen();

        System.out.println("================================");

        template = new NeoguriRamen();
        template.boilRamen();
    }
}
