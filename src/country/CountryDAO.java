package country;

import java.util.ArrayList;

public interface CountryDAO {

    public ArrayList<Country>getListOfCountries();

    public ArrayList<Country> getCountryByName(String countryName);

    public Country getCountryByCode(String code);

    public boolean saveCountryInToDB(Country country);


}
