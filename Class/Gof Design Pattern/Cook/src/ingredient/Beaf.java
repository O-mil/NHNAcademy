package ingredient;

import recipe.Visitor;

public class Beaf extends Acceptor {

    public Beaf(String name, int quantity, String unit) {
        super(name, quantity, unit);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
