public class Drink {
    Hot hot = new Hot();
    Iced iced = new Iced();

    public Drink(int beverageType) {

        if (beverageType <= 4) {
            hot.Hot();
        } else {
            iced.Iced();
        }
    }
}