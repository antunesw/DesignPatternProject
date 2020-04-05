package country;

/**
 * @Author Willian Antunes de Sousa
 */
// Enum class to ensure continent values are corresponded to those ones found in the databse

public enum Continent {

    AFRICA ("Africa"),
    ANTARCTICA("Antarctica"),
    ASIA("Asia"),
    EUROPE("Europe"),
    NORTH_AMERICA("North America"),
    SOUTH_AMERICA("South America"),
    OCEANIA("Oceania");

    private final String continent;

        Continent(String continent){
            this.continent = continent;
        }

    @Override
    public String toString() {
        return this.continent;
    }

}
