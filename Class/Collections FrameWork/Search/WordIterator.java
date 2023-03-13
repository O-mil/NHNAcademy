import java.util.Iterator;

public class WordIterator implements Iterator {
    Word word;
    int index = 0;

    public WordIterator (Word word) {
        this.word = word;
    }

    @Override
    public boolean hasNext() {
        if (this.index < word.files.length) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object next() {
        return this.word.files[this.index++];
    }

}