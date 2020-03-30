package country;

import java.util.ArrayList;

public class MySQLCountryDAO implements CountryDAO {


    @Override
    public ArrayList<Country> retrieveCountry() {
        return null;
    }

    @Override
    public Country getCountryByName(String countryName) {
        return null;
    }

    @Override
    public Country getCountryByCode(double code) {
        return null;
    }

    @Override
    public boolean saveCountryInToDB(Country country) {
        return false;
    }
}
