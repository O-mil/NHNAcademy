public class MergeSort {
    static int[] array = {3, 6, 7, 3, 46, 34, 53, 3, 34, 67};
    static int[] temp = new int[array.length]; 

    public static void sort(int start, int end) {
        if ( start < end) {
            int mid = (start+end) / 2;
            sort(start, mid);
            sort(mid+1, end);
            int p = start;
            int q = mid + 1;
            int idx = p;
            
            while (p <= mid || q <= end) {
                if (q > end || (p <= mid && array[p] < array[q])) {
                    temp[idx++] = array[p++];
                } else {
                    temp[idx++] = array[q++];
                }
            }
            for (int i = start; i <= end; i++) {
                array[i] = temp[i];
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
        sort(0, array.length - 1);
        printArray(array);
        System.out.println();
    }
}
