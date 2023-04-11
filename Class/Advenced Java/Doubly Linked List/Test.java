import java.util.Comparator;

public class Test {
	public static void main(String[] args) {
		LinkedList<Student> list = new LinkedList<>();

		list.add(new Student("김자바", 92));
		list.add(new Student("이시플", 72));
		list.add(new Student("조시샵", 98));
		list.add(new Student("파이손", 51));

		list.sort(customComp);

		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	// 사용자 설정 comparator(비교기)
	static Comparator<Student> customComp = new Comparator<Student>() {
		@Override
		public int compare(Student o1, Student o2) {
			return o2.score - o1.score;
		}
	};
}

class Student {
	String name;
	int score;

	Student(String name, int score){
		this.name = name;
		this.score = score;
	}

	public String toString() {
		return "이름 : " + name + "\t성적 : " + score;
	}
}