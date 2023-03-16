public class Test {

    public static void printList(Enumerable list) {
        Enumerator e = list.enumerator();
        while (e.hasMoreElement()) {
            System.out.println(e.current());
        }
    }
    public static void main(String[] args) {
        DrummerList list = new DrummerList(3);
        list.add(new Drummer.Builder(1, "John Bonnhm")
            .numberOfBass(1)
            .countOfSymbol(6)
            .teamName("Led Zeppelin")
            .build());

        list.add(new Drummer.Builder(2, "Lars Ulrich")
            .numberOfBass(2)
            .countOfSymbol(13)
            .teamName("Metallica")
            .build());

        list.add(new Drummer.Builder(3, "Karl Palmer")
            .numberOfBass(1)
            .countOfSymbol(5)
            .teamName("ELP")
            .build());
        printList(list);
    }
}
