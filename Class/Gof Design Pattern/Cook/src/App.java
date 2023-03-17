import ingredient.*;
import recipe.*;

public class App {
    public static void main(String[] args) throws Exception {
        Pork pork = new Pork("돼지고기", 200, "g");
        Fry fry = new Fry();
        //fry.Visit(pork);  // fry가 pork에 방문
        pork.accept(pork);   // pork가 fry의 방문을 받음
        System.out.println();

        Roast roast = new Roast();
        roast.Visit(pork);
        pork.accept(roast);
    }
}
