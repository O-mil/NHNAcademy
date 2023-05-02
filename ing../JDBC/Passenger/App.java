import java.util.*;

public class App {
    public static void main(String[] args) {
        PassengerList list = new PassengerList();
        List<Passenger> passengers = list.getData();

        for (Passenger p: passengers) {
            System.out.println(p);
        }
    }
}
