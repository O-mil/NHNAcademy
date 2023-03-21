public class BS {
    static int[] array = {3, 6, 7, 3, 46, 34, 53, 3, 34, 67};

    public static void sort(int[] array) {
        for ( int i = 0; i < array.length - 1; i++) {
            for ( int j = 0; j < array.length - i - 1; j++) {
                if ( array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array [j + 1] = temp;
                }
            }
        }
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
