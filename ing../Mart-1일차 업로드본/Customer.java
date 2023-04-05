package exercise;

public class Customer {
    // 고객의 구매 목록
    private final BuyList buyList;
    // 고객의 장바구니
    private Basket basket;

    public Customer(BuyList buyList) {
        this.buyList = buyList;
    }

    // 장바구니를 챙김
    public void bring(Basket basket) {
        this.basket = basket;
    }

    // 식품을 담음
    public void pickFoods(FoodStand foodStand) throws NotEnoughFoodException {
        for (BuyList.Item item : buyList.getItems()) {
            String foodName = item.getName();
            int amount = item.getAmount();
            boolean found = false;

            // 구매 목록에 있는 제품을 식품 매대에서 찾음
            for (Food food : foodStand.getFoods()) {
                if (food.getName().equals(foodName)) {
                    found = true;

                    // 재고가 충분한지 확인하고 장바구니에 담음
                    if (food.getStock() >= amount) {
                        for (int i = 0; i < amount; i++) {
                            basket.add(food);
                        }
                        food.setStock(food.getStock() - amount);  // 재고 차감
                    } else {
                        throw new NotEnoughFoodException("Not enough " + foodName);
                    }
                    break;
                }
            }

            // 식품 매대에서 해당 제품을 찾지 못한 경우
            if (!found) {
                throw new NotEnoughFoodException("Not enough " + foodName);
            }
        }
    }

    // 계산을 함
    public void payTox(Counter counter) throws NotEnoughMoneyException {
        int totalPrice = 0;
        for (Food food : basket.getFoods()) {
            totalPrice += food.getPrice();
        }

        int toPay = totalPrice - counter.getDiscount();
        int balance = counter.pay(toPay);

        if (balance < 0) {
            throw new NotEnoughMoneyException("Not enough money");
        } else {
            System.out.println("Total price is " + totalPrice + "원.");
            System.out.println("You get " + counter.getDiscount() + "원 discount.");
            System.out.println("After discount, the price to pay is " + toPay + "원.");
            System.out.println("You paid " + (toPay + balance) + "원. Your balance is " + balance + "원.");
        }
    }
}
