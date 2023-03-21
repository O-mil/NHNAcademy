import java.util.Arrays;

public class Insertion {
    static void insertSort(int[] arr) {

        // 첫 번째 값은 정렬이 됐다고 생각하고 두 번째 인덱스 부터 정렬 시작
       for (int i = 1; i < arr.length; i++) {

            // 삽입될 위치를 저장하기 위한 idx
            int idx = i;
            // 현재 정렬대상 값
            int tmp = arr[i];

            // 1. 이미 정렬된 배열에서 현재 정렬대상 값이 삽입될 위치(인덱스)를 찾는다.
            // 2. 현재 정렬대상 값보다 큰 값은 뒤로 한 칸식 이동된다.
            while( (idx > 0) && (arr[idx - 1] > tmp) ) {
                arr[idx] = arr[idx -1];
                idx--;
            }

            // 삽입될 위치에 tmp 삽입
            arr[idx] = tmp;
        }

    }

    public static void main(String[] args) {

        int[] arr = {3, 1, 2, 6, 7 , 5, 4};

        // 삽입정렬
        insertSort(arr);

        // 결과 출력
        System.out.println(Arrays.toString(arr));

    }
}
