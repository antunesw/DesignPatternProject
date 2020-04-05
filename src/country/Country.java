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


    //private constructor that makes sure a country will be created through the countryBuilder
    private Country(CountryBuilder countryBuilder) {

        this.code = countryBuilder.code;
        this.name = countryBuilder.name;
        this.headOfState = countryBuilder.headOfState;
        this.surfaceArea = countryBuilder.surfaceArea;
        this.continent = countryBuilder.continent;
    }
    /**GETTERS
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

    //this class is in charge of instantiating a new country
    public static class CountryBuilder {
    //Same attributes as the Country class
        private String code;
        private  String name;
        private Continent continent;
        private String headOfState;
        private double surfaceArea;

       //the code and name parameters are necessary when creating the country
        public CountryBuilder(String code ,String name){
           this.code = code;
           this.name = name;

       }

        /**
         *SETTERS
         */
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


    //returns the country object with values set
        public Country build(){

            return  new Country(this);
        }

    }
}
