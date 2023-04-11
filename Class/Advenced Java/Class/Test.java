public class Test {
    public static void main(String[] args) {
        Lecture<Professor> lecture = new Lecture<>();   //Lecture에 들어갈 수  있는 3개짜리 배열을 만듬
        lecture.add(new Professor(1, "Buzz", "closed"));
        lecture.add(new Professor(2, "other Buzz", "closed"));
        

        for ( int i = 0; i < lecture.Professor.length; i++) {
            lecture.items[i].toString();  // 반복문 돌려 출력
        }

        for (Professor s: lecture) {
            System.out.println(s.toString());    //위에랑 같은데 다른 방식
        }
    }
}