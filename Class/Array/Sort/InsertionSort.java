public class InsertionSort {
    static int[] array = {3, 6, 7, 3, 46, 34, 53, 3, 34, 67};

    public static void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int idx = i;
            int temp = array[i];

            while( (idx > 0) && (array[idx - 1] > temp) ) {
                array[idx] = array[idx -1];
                idx--;
            }
            array[idx] = temp;
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
