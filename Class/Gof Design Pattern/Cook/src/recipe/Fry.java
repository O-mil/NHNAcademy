package recipe;

import ingredient.Acceptor;

public class Fry {
    public void Visit(Acceptor acceptor) {
        System.out.println("Starting art...");
        System.out.println("기름을 끓이는 중");
        System.out.println(acceptor.getName() + " " + acceptor.getQuantity() + acceptor.getUnit() + "을 기름에 넣습니다.");
        System.out.println("건지기");
    }
}
