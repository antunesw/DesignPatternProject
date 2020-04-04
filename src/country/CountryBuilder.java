package country;

public class CountryBuilder {

    private String code;
    private  String name;
    private Continent continent;
    private String headOfState;
    private double surfaceArea;

    public CountryBuilder setCode(String code) {
        this.code = code;
        return this;
    }

    public CountryBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CountryBuilder setContinent(Continent continent) {
        this.continent = continent;
        return this;
    }

    public CountryBuilder setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
        return this;

    }

    public CountryBuilder setSurfaceArea(double surfaceArea) {
        this.surfaceArea = surfaceArea;
        return this;
    }
    public Country getCountry(){

        return  new Country(code,name,continent,surfaceArea,headOfState);
    }

}
