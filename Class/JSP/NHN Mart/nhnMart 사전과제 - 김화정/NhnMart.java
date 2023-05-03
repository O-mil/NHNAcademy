package exercise;

class NhnMartShell {
    public static void main(String[] args) {
        NhnMart mart = new NhnMart();
        mart.prepareMart();

        BuyList buyList = inputBuyListFromShell();

        Customer jordan = new Customer(buyList);
        // 장바구니를 챙긴다.
        jordan.bring(mart.provideBasket());
        // 식품을 담는다.
        jordan.pickFoods(mart.getFoodStand());
        // 카운터에서 계산한다.
        jordan.payTox(mart.getCounter());
    }

    private static BuyList inputBuyListFromShell() {
        Scanner scanner = new Scanner(System.in);
        BuyList buyList = new BuyList();
        boolean continueShopping = true;
        while (continueShopping) {
            System.out.println("NHN 마트에 오신 것을 환영합니다. 사고 싶은 물건을 골라주세요.");
            System.out.print("> ");
            String input = scanner.nextLine();
            String[] inputArr = input.split(" ");
            if (inputArr.length != 2) {
                System.out.println("잘못된 입력입니다.");
                continue;
            }
            String itemName = inputArr[0];
            int amount;
            try {
                amount = Integer.parseInt(inputArr[1]);
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다.");
                continue;
            }
            if (amount <= 0) {
                System.out.println("잘못된 입력입니다.");
                continue;
            }
            buyList.add(new BuyList.Item(itemName, amount));
            System.out.println("계속 쇼핑하시겠습니까? (Y/N)");
            System.out.print("> ");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("N")) {
                continueShopping = false;
            }
        }
        return buyList;
    }
}

public class NhnMart {
    private final FoodStand foodStand = new FoodStand();

    public void prepareMart() {
        fillFoodStand();
    }

    private void fillFoodStand() {
        for (int i = 0; i < 2; i++) {
            foodStand.add(new Food("양파", 1_000));
        }
        for (int i = 0; i < 5; i++) {
            foodStand.add(new Food("계란(30개)", 5_000));
        }
        for (int i = 0; i < 10; i++) {
            foodStand.add(new Food("파", 500));
        }
        for (int i = 0; i < 20; i++) {
            foodStand.add(new Food("사과", 2_000));
        }
    }

    public Basket provideBasket() {
        return new Basket();
    }

    public FoodStand getFoodStand() {
        return foodStand;
    }

    public Counter getCounter() {
        return new Counter();
    }
}
