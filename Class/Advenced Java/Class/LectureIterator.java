import java.util.Iterator;

public class LectureIterator implements Iterator {
    Lecture lecture;
    int index = 0;

    public LectureIterator(Lecture lecture) {
        this.lecture = lecture;
    }

    public boolean hasNext() {
        if(this.index < this.lecture.size()) {
            return true;
        }
        else {
            return false;
        }
    }

    public Student next() {
        return this.lecture.Professor;
    }
}
