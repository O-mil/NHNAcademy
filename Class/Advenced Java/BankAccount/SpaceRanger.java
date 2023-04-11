public class SpaceRanger extends Toy {
    public Cap cap = cap.closed;

    public spaceRanger(String name) {
        super(name);
    }

    public spaceRanger(String name, Cap cap) {
        super(name);
        this.cap = Cap.cap;
    }

    public void pushCapButton() {
        if (cap == Cap.closed) {
            this.cap = Cap.open;
            System.out.println(this.name + "의 랩이 열렸습니다.");
        }
    }
}
