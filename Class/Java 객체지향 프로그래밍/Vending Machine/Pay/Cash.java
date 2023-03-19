package Pay;
import java.util.Scanner;

public class Cash extends PayTemplate {

    int menuNo;

    public void cash(int menuNo) {
        this.menuNo = menuNo;
    }

    @Override
    public void pay() {
        int[] price = {1500, 2000, 1700, 1500, 1500, 2000, 2500, 1700};

        int pay = price[this.menuNo];

        Scanner sc = new Scanner(System.in);
        System.out.println(pay + "원을 넣어주세요.");
        int receive = sc.nextInt();

        if (receive < pay) {
            System.out.println("금액이 부족합니다. 돈을 반환합니다.");
            System.exit(0);
        } else {
            System.out.println("거스름돈은 " + (pay - receive) + "원 입니다.");
        }
        sc.close();
        }

}