package org.omil;

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
}
