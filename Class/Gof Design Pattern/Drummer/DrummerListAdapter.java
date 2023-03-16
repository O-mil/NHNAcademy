import java.util.Iterator;

public class DrummerListAdapter implements Iterable<Drummer> {
    DrummerList list;

    public DrummerListAdapter (DrummerList list) {
        this.list = list;
    }

    @Override
    public Iterator<Drummer> iterator() {
        return new DrummerEnumeratorAdapter(new DrummerEnumerator(this.list));
    }
}