public class Sort {
    static int[] array = {3, 6, 7, 3, 46, 34, 53, 3, 34, 67};

    public static void sort(int[] array) {

    }

    public static void printArray(int[] array) {
        for(int i: array) {
            System.out.print(i + " ");
        }
    }

    public static void main(String[] args) {
        printArray(array);
        System.out.println();
        sort(array);
        printArray(array);
        System.out.println();
    }
}
