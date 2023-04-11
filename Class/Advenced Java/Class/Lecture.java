import java.util.*;

public class Lecture<T> implements Iterable<T> {
    public static final Student Professor = null;
    public List<T> items;

    public Lecture() {
        this.items = new ArrayList<T>();
    }

    public int size() {
        return this.items.size();
    }

    public void add(T item) {
        this.items.add(item);
    }

    public Iterator iterator() {
        return new LectureIterator(this);
    }
}
