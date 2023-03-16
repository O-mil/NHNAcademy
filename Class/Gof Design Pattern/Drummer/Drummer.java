public class Drummer {
    private final int drummerNo;
    private final String name;

    private final int numberOfBass;
    private final int countOfSymbol;
    private final String teamName;

    private Drummer(Builder builder) {
        this.drummerNo = builder.drummerNo;
        this.name = builder.name;
        this.numberOfBass = builder.numberOfBass;
        this.countOfSymbol = builder.countOfSymbol;
        this.teamName = builder.teamName;

    }

    public static class Builder {
        private int drummerNo;
        private String name;

        private int numberOfBass;
        private int countOfSymbol;
        private String teamName;

        public Builder(int drummerNo, String name) {
            this.drummerNo = drummerNo;
            this.name = name;
        }

        public Builder numberOfBass(int value) {
            this.numberOfBass = value;
            return this;
        }

        public Builder countOfSymbol(int value) {
            this.countOfSymbol = value;
            return this;
        }

        public Builder teamName(String name) {
            this.teamName = name;
            return this;
        }

        public Drummer build() {
            return new Drummer(this);
        }
    }
}