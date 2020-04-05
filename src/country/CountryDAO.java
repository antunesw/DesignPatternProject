package country;

import java.util.ArrayList;

/**Author Willian Antunes de Sousa
 *
 */
public interface CountryDAO {
    //Interface responsible for defining methods methods that will be presented to the user
   //returns ArrayList of all  countries in the database
    public ArrayList<Country>getListOfCountries();
    //receives the country name to be found as argument and returns ArrayList of countries with the given name if found
    public ArrayList<Country> getCountryByName(String countryName);
    //receives the country code to be found as argument and returns the country attached to this code if found
    public Country getCountryByCode(String code);
    //receives the country to be added as argument and returns true if added or false if not added
    public boolean saveCountryInToDB(Country country);


}
