//2. 잠자는 이발사 문제(일명 Sleeping Barber 문제)를 생산자-소비자 관점에서 Java로 구현
class Chairs {
    private static int value = 0;
    private int max;
    private boolean sleep = false;

    public Chairs(int max) {
        this.max = max;
    }

    synchronized void service() throws Exception {
        while (value == 0) {
            sleep = true;
            System.out.println("이발사 자는 중");
            wait();
        }
        if (value-- == max)
            notify();
    }

    synchronized void enter() throws Exception {
        while (value == max) {
            System.out.println("빈 의자 없음");
            wait();
        }
        value++;

        if (sleep == true) {
            sleep = false;
            System.out.println("이발사 일어남");
            notify();
        }
    }

    static synchronized int val() {
        return value;
    }
}

class Barber extends Thread {
    private Chairs cs;
    private int MAX = 60;

    public Barber(Chairs c) {
        cs = c;
    }

    public void run() {
        for (int i = 0; i < MAX; i++) {
            try {
                cs.service();
                System.out.println("손님 머리 자르는 중, " + Chairs.val() + "명 기다리는 중");
                sleep((int)(1400 * Math.random()));
            } catch (Exception e) { }
        }
    }
}

class Customer extends Thread {
    private Chairs cs;
    private int MAX = 60;

    public Customer(Chairs s) {
        cs = s;
    }

    public void run() {
        for ( int i = 0; i < MAX; i++) {
            try {
                cs.enter();
                System.out.println("새로운 손님이 들어왔습니다." + " 총 " + cs.val() + " 명 기다리는 중");
                sleep((int)(1400 * Math.random()));
            } catch (Exception e) { }
        }
    }
}

public class SleepBarber {
    public static void main(String[] args) {
        Chairs cs = new Chairs(4);
        Barber b = new Barber(cs);
        Customer c = new Customer(cs);
        b.start();
        c.start();
    }
}
