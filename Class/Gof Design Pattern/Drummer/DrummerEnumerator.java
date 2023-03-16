public class DrummerEnumerator implements Enumerator {
    DrummerList list;
    int index = 0;

    public DrummerEnumerator(DrummerList list) {
        this.list = list;
    }

    public boolean hasMoreElement() {
        if (this.index >= list.size()) {
            return false;
        } else {
            return true;
        }
    }

    public Object current() {
        return this.list.list[this.index++];
    }

}
