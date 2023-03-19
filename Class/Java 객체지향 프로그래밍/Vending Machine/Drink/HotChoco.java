package Drink;
public class HotChoco extends HotTemplate {

   @Override
   public void syrup() {
      System.out.println("초코를 넣습니다.");
   }

   @Override
   public void drink() {
      System.out.println("따뜻한 우유를 넣습니다.");
   }
}
