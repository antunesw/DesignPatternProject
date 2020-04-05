package country;

/**
 * @Author Willian Antunes de Sousa
 */
// Enum class to ensure continent values are corresponded to those ones found in the databse

public enum Continent {

    AFRICA ("AFRICA"),
    ANTARCTICA("ANTARCTICA"),
    ASIA("ASIA"),
    EUROPE("EUROPE"),
    NORTH_AMERICA("NORTH AMERICA"),
    SOUTH_AMERICA("SOUTH AMERICA"),
    OCEANIA("OCEANIA");

    private final String continent;

        Continent(String continent){
            this.continent = continent;
        }

    @Override
    public String toString() {
        return this.continent;
    }

}
