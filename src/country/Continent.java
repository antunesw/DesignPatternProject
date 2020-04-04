package country;

public enum Continent {

    AFRICA ("AFRICA"),
    ANTARCTICA("ANTARCTICA"),
    ASIA("ASIA"),
    EUROPE("EUROPE"),
    NORTH_AMERICA("NORTH AMERICA"),
    SOUTH_AMERICA("SOUTH AMERICA"),
    OCEANIA("OCEANIA");

    private final String message;

        Continent(String message){
            this.message = message;
        }

    @Override
    public String toString() {
        return this.message;
    }

//        public static Continent getEnum(String  continentString){
//            for
//        }

}
