package recipe;
import ingredient.Acceptor;

public interface Visitor {
    public void visit(Acceptor acceptor);
}
