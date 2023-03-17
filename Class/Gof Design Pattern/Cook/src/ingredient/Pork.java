package ingredient;

import recipe.Visitor;

public class Pork extends Acceptor {
    public Pork(String name, int quantity, String unit) {
        super(name, quantity, unit);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}