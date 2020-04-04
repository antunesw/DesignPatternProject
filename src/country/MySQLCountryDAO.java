package country;

import dbConnection.DB_Connect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLCountryDAO implements CountryDAO {


    DB_Connect db = new DB_Connect();

    @Override
    public ArrayList<Country> getListOfCountries() {

        ArrayList<Country> countriesList = new ArrayList<Country>();

        String query = "SELECT * FROM country";

        Continent continent;
        String code = "";
        String name = "";
        String headOfState = "";
        double surfaceArea = 0.0;
        Country country = null;
        try {
            ResultSet rs = db.select(query);

            while (rs.next()) {
                String countryContinent = rs.getString(3);
                if (countryContinent.isBlank()) {
                    continue;
                }
                code = rs.getString(1);
                name = rs.getString(2);
                headOfState = rs.getString(5);
                surfaceArea = rs.getDouble(4);
                continent = Continent.valueOf(rs.getString(3).replace(" ", "_").toUpperCase());

                country = new Country(code, name, continent, surfaceArea, headOfState);
                countriesList.add(country);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countriesList;

    }

    @Override
    public ArrayList<Country> getCountryByName(String countryName) {
        ArrayList<Country> countryList = new ArrayList<Country>();
        String query = "SELECT * FROM country WHERE Name LIKE '%"+countryName+"%';";
        Country country = null;
        Continent continent;
        String code = "";
        String headOfState = "";
        double surfaceArea = 0.0;
        try {
            ResultSet rs = db.select(query);

            while (rs.next()) {
                String continentName = rs.getString(3);
                if (continentName.isBlank()) {
                    continue;
                }
                code = rs.getString(1);
                headOfState = rs.getString(5);
                surfaceArea = rs.getDouble(4);
                continent = Continent.valueOf(rs.getString(3).replace(" ", "_").toUpperCase());

                country = new Country(code, countryName, continent, surfaceArea, headOfState);
                countryList.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countryList;

    }

    @Override
    public Country getCountryByCode(String code) {
        Country country = null;
//"SELECT * FROM country WHERE Name LIKE '%"+countryName+"%';";
        String query = "SELECT * FROM country WHERE Code LIKE '%"+code+"%';";

        Continent continent;
        String name = "";
        String headOfState = "";
        double surfaceArea = 0.0;
        try {
            ResultSet rs = db.select(query);

            if (rs.next()) {
                    String continentName = rs.getString(3);
                if (continentName.isBlank()){
                        return null;
                }
                name = rs.getString(2);
                headOfState = rs.getString(5);
                surfaceArea = rs.getDouble(4);
                continent = Continent.valueOf(rs.getString(3).replace(" ", "_").toUpperCase());

                country = new Country(code, name, continent, surfaceArea, headOfState);

            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;

    }

    @Override
    public boolean saveCountryInToDB(Country country) {


        return false;
    }
}
