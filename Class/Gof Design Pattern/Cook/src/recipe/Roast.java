package recipe;

import ingredient.Acceptor;

public class Roast {
    public void Visit(Acceptor acceptor) {
        System.out.println("Starting art...");
        System.out.println(acceptor.getName() + " " + acceptor.getQuantity() + acceptor.getUnit() + "을 굽습니다.");
        System.out.println("굽기 완료");
    }

}
