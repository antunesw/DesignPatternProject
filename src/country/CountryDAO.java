package country;

import java.util.ArrayList;

public interface CountryDAO {

    public ArrayList<Country>retrieveCountry();

    public Country getCountryByName(String countryName);

    public Country getCountryByCode(double code);

    public boolean saveCountryInToDB(Country country);


}
