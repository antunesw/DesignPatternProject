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
    public Country(String code,String name,Continent continent, double surfaceArea,String headOfState) {
      //  super();
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

}
