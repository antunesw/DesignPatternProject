package country;

import dbConnection.DB_Connect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLCountryDAO implements CountryDAO {

    DB_Connect db = DB_Connect.getInstance();
    Continent continent;
    String code = "";
    String name = "";
    String headOfState = "";
    double surfaceArea = 0.0;


    @Override
    public ArrayList<Country> getListOfCountries() {

        ArrayList<Country> countriesList = new ArrayList<Country>();

        String query = "SELECT * FROM country";


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

                Country.CountryBuilder cBuilder = new Country.CountryBuilder(code,name).setContinent(continent).setHeadOfState(headOfState).setSurfaceArea(surfaceArea);
                countriesList.add(cBuilder.build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countriesList;

    }

    @Override
    public ArrayList<Country> getCountryByName(String countryName) {

        ArrayList<Country> countriesList = new ArrayList<Country>();

        String query = "SELECT * FROM country WHERE Name LIKE '%" + countryName + "%';";

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

                Country.CountryBuilder cBuilder = new Country.CountryBuilder(code,countryName).setContinent(continent).setHeadOfState(headOfState).setSurfaceArea(surfaceArea);
                countriesList.add(cBuilder.build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countriesList;

    }

    /**
     * @param code
     * @return
     */
    @Override
    public Country getCountryByCode(String code) {

        String query = "SELECT * FROM country WHERE Code LIKE '%" + code + "%';";

        try {
            ResultSet rs = db.select(query);

            if (rs.next()) {
                String continentName = rs.getString(3);
                if (continentName.isBlank()) {
                    return null;
                }
                name = rs.getString(2);
                headOfState = rs.getString(5);
                surfaceArea = rs.getDouble(4);
                continent = Continent.valueOf(rs.getString(3).replace(" ", "_").toUpperCase());

                Country.CountryBuilder cBuilder = new Country.CountryBuilder(code,name).setContinent(continent).setHeadOfState(headOfState).setSurfaceArea(surfaceArea);
                return cBuilder.build();
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    return null;
    }

    @Override
    public boolean saveCountryInToDB(Country country) {

        code= country.getCode();
        name = country.getName();
       continent = country.getContinent();
       surfaceArea = country.getSurfaceArea();
       headOfState = country.getHeadOfState();

        //INSERT QUERY is executed if everything goes well it returns true if not false
        return db.saveData("INSERT INTO country (code, name, continent, surfaceArea, headOfState)" +
                " VALUES ('" + code + "', '" + name + "', '" + continent + "', '" + surfaceArea + "', '" + headOfState + "');");
    }
}
