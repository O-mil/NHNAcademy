package ingredient;

import recipe.Visitor;

// Visitor가 방문할 곳을 나타냄.
public abstract class Acceptor {
    protected String name;
    protected int quantity;
    protected String unit;

    public Acceptor(String name, int quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getName() {
        return this.name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getUnit() {
        return this.unit;
    }

    // 방문자를 받아들이기 위한 accept 메서드를 가짐.
    public abstract void accept (Visitor visitor);
}
