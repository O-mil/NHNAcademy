//ADT(추상데이터타입)
//이게 스택임 (코드 오류 날 수도 있다하심;;)
public class Stack {
    int[] array;
    int index = 0;

    public Stack() {
        this.array = new int[10];
    }

    public void push(int i) {
        this.array[index] = i;
        index++;
    }

    public int pop() {
        int result = this.array[index--];
        return result;
    }
}
