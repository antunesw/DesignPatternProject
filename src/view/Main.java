package view;

import country.Continent;
import country.Country;
import country.CountryBuilder;
import country.MySQLCountryDAO;
import dbConnection.DB_Connect;

import javax.print.DocFlavor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    private Statement stmt;
    private Connection conn;

    public static void main(String[] args) {

        Country country = new CountryBuilder().setCode("123").setHeadOfState("Morty").setName("Bira").setSurfaceArea(1234).getCountry();
        System.out.println(country);
      //  Menu menu = new Menu();
////        menu.printMenuHeader();
////        menu.printMenu();
//        menu.runMenu();
////        MySQLCountryDAO test = new MySQLCountryDAO();
////        System.out.println(test.getCountry());

    }
}


