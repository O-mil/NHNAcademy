import java.util.Iterator;

public class Word implements Iterable {

    File[] files;
    int index = 0;

    public Word (int size) {
        this.files = new File[size];
    }

    public void add(File file) {
        this.files[this.index++] = file;
    }

    @Override
    public WordIterator iterator() {
        return new WordIterator(this);
    }

}