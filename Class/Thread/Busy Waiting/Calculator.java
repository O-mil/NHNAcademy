//1. Lab 17-1 예제를 바쁜 대기 방식으로 수정하여 구현
public class Calculator {
    private int memory;
    private boolean wait = true;

    public int getMemory() {
        return memory;
    }

    public void setMemory(int value) {     // synchronized: 이 매소드는 무조건 상호배제를 구현한다. -> "Thread safe 하다" 라고 말한다.
         //synchronized {
        while(!this.wait) {
            System.out.println(Thread.currentThread().getName() + " wait");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.wait = false;
        this.memory = value;

        try {
            Thread.sleep(2000);
            //this.memory = value;

            System.out.println(Thread.currentThread().getName() + ": " + this.memory);
            this.wait = !wait;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.wait = true;
        }
        //}
    }
}

class User extends Thread {
    private Calculator calculator;
    int memory;

    public User(String name, int memory) {
        this.setName(name);
        this.memory = memory;
    }

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void run() {
        calculator.setMemory(this.memory);
    }
}

class Test {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        User user1 = new User("User1", 100);
        User user2 = new User("User2", 50);

        user1.setCalculator(calculator);
        user1.start();

        user2.setCalculator(calculator);
        user2.start();
    }
}
