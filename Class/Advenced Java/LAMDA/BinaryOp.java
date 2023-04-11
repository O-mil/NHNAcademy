public interface BinaryOp {
    int apply(int i, int j);
}

class Adder implements BinaryOp {
    public int apply(int i, int j) {
        return i + j;
    }
}

class Multifly implements BinaryOp {
    public int apply(int i, int j) {
        return i * j;
    }
}

class Test {
    public static int calc(BinaryOp binder, int i, int j) {
        return binder.apply(i, j);
    }

    public static void main(String[] args) {
        System.out.println(calc((x, y) -> (x + y), 1, 2));      //print함수는 void를 출력 못함 (변수형, return값 있어야 함)
    }
}