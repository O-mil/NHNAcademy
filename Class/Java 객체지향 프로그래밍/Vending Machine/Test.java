import java.util.Scanner;

import Drink.HotAmericano;
import Drink.HotCafeLatte;
import Drink.HotChoco;
import Drink.HotMochachino;
import Drink.HotTemplate;
import Drink.IcedAmericano;
import Drink.IcedCafeLatte;
import Drink.IcedChoco;
import Drink.IcedTea;
import Drink.IcedTemplate;
import Pay.Cash;
import Pay.Credit;
import Pay.Online;
import Pay.PayTemplate;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HotTemplate hotTemplate = new HotAmericano();
        IcedTemplate icedTemplate = new IcedAmericano();

        System.out.println("=======Vending Machine=======");
        System.out.println("<MENU>");
        System.out.println("1. IcedAmericano\n" +
            "2. IcedCafeLatte\n" +
            "3. IcedChoco\n" +
            "4. IcedTea\n" +
            "5. HotAmericano\n" +
            "6. HotCafeLatte\n" +
            "7. HotMochachino\n" +
            "8. HotChoco");

        System.out.print("원하시는 메뉴를 선택해주세요: ");
        int menuNo = sc.nextInt();

        Cash cash = new Cash();
        cash.cash(menuNo);

        System.out.println();
        System.out.println("<Payment>");
        System.out.println("1. Cash\n" +
            "2. Credit\n" +
            "3. Online");
        System.out.print("원하시는 결제 방법을 선택해주세요: ");
        int payNo = sc.nextInt();

        // 결제
        if (payNo == 1) {
            PayTemplate payTemplate = new Cash();
            payTemplate.machine();
        } else if (payNo == 2) {
            PayTemplate payTemplate = new Credit();
            payTemplate.machine();
        } else {
            PayTemplate payTemplate = new Online();
            payTemplate.machine();
        }

        // 음료 만들기
        switch(menuNo) {
            case 1:
                icedTemplate.machine();
                break;
            case 2:
                icedTemplate = new IcedCafeLatte();
                icedTemplate.machine();
                break;
            case 3:
                icedTemplate = new IcedChoco();
                icedTemplate.machine();
                break;
            case 4:
                icedTemplate = new IcedTea();
                icedTemplate.machine();
                break;
            case 5:
                hotTemplate.machine();
                break;
            case 6:
                hotTemplate = new HotCafeLatte();
                hotTemplate.machine();
                break;
            case 7:
                hotTemplate = new HotMochachino();
                hotTemplate.machine();
                break;
            case 8:
                hotTemplate = new HotChoco();
                hotTemplate.machine();
                break;
            default:
                System.out.println("메뉴 없음");
        }
        sc.close();
    }
}