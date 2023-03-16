import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===음료 자판기===");
        System.out.println();

        System.out.println("<MENU>");
        System.out.println("1. 아메리카노: 1500원\n"
        + "2. 카페라떼: 2000원\n"
        + "3. 모카치노: 2500원\n"
        + "4. 핫 초코: 2000원\n"
        + "5. 아이스 아메리카노: 2000원\n"
        + "6. 아이스 카페라떼: 2500원\n"
        + "7 아이스 초코: 2000원\n"
        + "8. 복숭아 아이스티: 2000원");
        System.out.print("원하시는 음료 타입을 선택해주세요: ");

        int beverageType = sc.nextInt();
        System.out.println(beverageType + "번이 선택되었습니다.");
        System.out.println();

        System.out.println("<결제방식>");
        System.out.println("1. 현금\n" + "2. 카드\n" + "3. 온라인페이");
        System.out.print("원하시는 결제 방식을 선택해주세요: ");


        int paymentType = sc.nextInt();
        System.out.println(paymentType + "번이 선택되었습니다.");
        System.out.println();

        Payment payment = new Payment(paymentType, beverageType);

        Drink drink = new Drink(beverageType);

        System.out.println("이용해주셔서 감사합니다.");

        sc.close();
    }

}
