package country;

import java.util.Iterator;

/**
 * @Author Willian Antunes de Sousa
 *Country class with Builder Pattern implemented
 *
 */


public class Country {
    //Variables related to those one found in the database.
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
    private Country(CountryBuilder countryBuilder) {
      // super();
        this.code = countryBuilder.code;
        this.name = countryBuilder.name;
        this.headOfState = countryBuilder.headOfState;
        this.surfaceArea = countryBuilder.surfaceArea;
        this.continent = countryBuilder.continent;
    }
    /**GETTERS AND SETTERS
     *
     */
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getHeadOfState() {
        return headOfState;
    }


    public Continent getContinent() {
        return continent;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }


    /**
     * returns the object in a String format
     * @return
     */
    @Override
    public String toString() {

        return "-----Country----" +'\n'+
                " code= " + code +'\n'+
                " name= " + name  +'\n'+
                " continent= " + continent+'\n'+
                " headOfState= " + headOfState +'\n'+
                " surfaceArea= " + surfaceArea +'\n' ;
    }

    public static class CountryBuilder {

        private String code;
        private  String name;
        private Continent continent;
        private String headOfState;
        private double surfaceArea;

        public  CountryBuilder setCode(String code) {
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



        public Country build(){

            return  new Country(this);
        }

    }
}
