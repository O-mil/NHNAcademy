import java.util.Iterator;

public class Lecture implements Iterable {

    Student[] students;
    Profesor[] profesors;

    int index = 0;

    public Lecture (int size) {
        this.students = new Student[size];
        this.profesors = new Profesor[size];
    }

    public void add (Student student) {
        if ( this.index >= students.length) {
            System.out.println("Class is full!");
        } else {
            this.students[this.index++] = student;
        }
    }

    public void add2 (Profesor profesor) {
        if (this.index >= profesors.length) {
            System.out.println("Class is full!");
        } else {
            this.profesors[this.index++] = profesor;
        }
    }

    @Override
    public Iterator iterator() {
        return (Iterator) new LectureIterator(this);
    }
    
}
