public class Test {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle(10, 5);
        Shape square = new Square(5);

        System.out.println(rectangle.getArea());
        System.out.println(square.getArea());
    }
}