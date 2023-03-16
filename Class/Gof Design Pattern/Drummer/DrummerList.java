public class DrummerList implements Enumerable {
    Drummer[] list;
    int index = 0;

    public DrummerList(int size) {
        this.list = new Drummer[size];
    }

    public void add(Drummer drummer) {
        if (this.index >= this.list.length) {
            System.out.println("List full!");
        } else {
            this.list[index++] = drummer;
            this.index++;
        }
    }

    public int size() {
        return this.list.length;
    }

    public Enumerator enumerator() {
        return new DrummerEnumerator(this);
    }
}
