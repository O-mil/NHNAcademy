import java.util.Scanner;

public class Payment extends Price {
    public static int price;

    Scanner sc = new Scanner(System.in);

    public Payment(int paymentType, int beverageType) {

        // 음료 가격
        if (beverageType == 1) {
            price = getA();
        } else if (beverageType == 3 || beverageType == 6) {
            price = getC();
        } else {
            price = getB();
        }

        // 결제 방식
        if (paymentType == 1) {
            Cash();
        } else if (paymentType == 2) {
            Card();
        } else {
            Online();
        }
    }

    public void Cash() {
        System.out.print("금액을 넣어주세요: ");
        int received = sc.nextInt();

        if (received < price) {
            System.out.println("금액이 부족합니다. 돈을 반환합니다.");
            System.exit(0);
        } else {
            System.out.println("거스름돈은 " + (received - price) + "원 입니다.");
            System.out.println();
        }
    }

    public void Card() {
        System.out.println("카드를 삽입해주세요.");
        System.out.println("결제중...");
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        System.out.println("true");
        System.out.println("결제가 완료되었습니다.");
        System.out.println();
    }

    public void Online() {
        System.out.println("카드사 연결중...");
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        System.out.println("true");
        System.out.println("결제가 완료되었습니다.");
        System.out.println();
    }

}

// protected int Americano;    1//1500
// protected int cafeLatte;    2//2000
// protected int mocha;        3//2500
// protected int hotChoco;     4//2000
// protected int icedAmericano;5//2000
// protected int icedCafeLatte;6//2500
// protected int icedChoco;    7//2000
// protected int icedTea;      8//2000