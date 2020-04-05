package country;

/**
 * @Author Willian Antunes de Sousa
 */

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
    double surfaceArea = 0;


    @Override
    public ArrayList<Country> getListOfCountries() {

        ArrayList<Country> countriesList = new ArrayList<Country>();
        //Sql query that is going to be passed as argument and it going to retrieve countries related to the names passes as argument
        String query = "SELECT * FROM country";

    // ResultSet rs gets the query with countries coming from the database
        try {
            //rs is holding the results of SQL select query
            ResultSet rs = db.select(query);
            //while loop with rs next moving through the rows retrieving the data and storing on to the corresponding variables
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
            //country object is built
                Country.CountryBuilder cBuilder = new Country.CountryBuilder(code, name,continent,surfaceArea,headOfState);
              // country object is added to the ArrayList
                countriesList.add(cBuilder.build());
            }
            //handling any error that may occur
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //countriesList Array is returned
        return countriesList;

    }

    @Override
    public ArrayList<Country> getCountryByName(String countryName) {

        ArrayList<Country> countriesList = new ArrayList<Country>();
        //Sql query that is going to be passed as argument and it going to retrieve countries related to the names passes as argument
        String query = "SELECT * FROM country WHERE Name LIKE '%" + countryName + "%';";

        try {
            //rs is holding the results of SQL select query
            ResultSet rs = db.select(query);
            //while loop with rs next moving through the rows retrieving the data and storing on to the corresponding variables
            while (rs.next()) {
                String continentName = rs.getString(3);
                if (continentName.isBlank()) {
                    continue;
                }
                code = rs.getString(1);
                headOfState = rs.getString(5);
                surfaceArea = rs.getDouble(4);
                continent = Continent.valueOf(rs.getString(3).replace(" ", "_").toUpperCase());

                //country object is built
                Country.CountryBuilder cBuilder = new Country.CountryBuilder(code, countryName,continent,surfaceArea,headOfState);
                // country object is added to the ArrayList
                countriesList.add(cBuilder.build());
            }
            //handling any error that may occur
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //countriesList Array is returned
        return countriesList;

    }

    /**
     * @param code
     * @return
     */
    @Override
    public Country getCountryByCode(String code) {

        //Sql query that is going to be passed as argument and it going to retrieve countries related to the names passes as argument
        String query = "SELECT * FROM country WHERE Code LIKE '%" + code + "%';";

        try {
            //rs is holding the results of SQL select query
            ResultSet rs = db.select(query);
            //while loop with rs next moving through the rows retrieving the data and storing on to the corresponding variables
            if (rs.next()) {
                String continentName = rs.getString(3);
                if (continentName.isBlank()) {
                    return null;
                }
                name = rs.getString(2);
                headOfState = rs.getString(5);
                surfaceArea = rs.getDouble(4);
                continent = Continent.valueOf(rs.getString(3).replace(" ", "_").toUpperCase());

                //country object is built
                Country.CountryBuilder cBuilder = new Country.CountryBuilder(code, name,continent,surfaceArea,headOfState).setContinent(continent);
                //Country object is returned
                return cBuilder.build();
            } else {
                return null;
            }
            //handling any error that may occur
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveCountryInToDB(Country country) {

        code = country.getCode();
        name = country.getName();
        continent = country.getContinent();
        surfaceArea = country.getSurfaceArea();
        headOfState = country.getHeadOfState();

        //INSERT QUERY is executed if everything goes well it returns true if not false
        return db.saveData("INSERT INTO country (code, name, continent, surfaceArea, headOfState)" +
                " VALUES ('" + code + "', '" + name + "', '" + continent + "', '" + surfaceArea + "', '" + headOfState + "');");
    }
}
