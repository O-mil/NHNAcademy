public class Singleton {
    private static Singleton singleton;

    private Singleton() {}

    public static Singleton getSingleton() {
        if (singleton != null) {
            return singleton;
        } else {
            singleton = new Singleton();
            return singleton;
        }
    }
}

class Test {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getSingleton();
        System.out.println(singleton);
    }
}
