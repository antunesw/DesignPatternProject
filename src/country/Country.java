package country;

import java.util.Iterator;

public class Country {

    private String code;
    private  String name;
    private Continent continent;
    private String headOfState;
    private double surfaceArea;

    /**
     * Class class constructor with the following parameters
     * @param code
     * @param name
     * @param headOfState
     * @param surfaceArea
     */
    public Country(String code,String name,Continent continent, double surfaceArea,String headOfState) {

        this.code = code;
        this.name = name;
        this.headOfState = headOfState;
        this.surfaceArea = surfaceArea;
        this.continent = continent;
    }
    /**GETTERS AND SETTERS
     *
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    /**
     * returns the object in a String format
     * @return
     */
    @Override
    public String toString() {

        return "-----Country----" +'\n'+
                " code= " + code +
                " name= " + name  +
                " continent= " + continent +
                " headOfState= " + headOfState +
                " surfaceArea= " + surfaceArea +'\n' ;
    }
    Iterator<Country> countryIterator;
}
