package view;

import java.sql.Connection;
import java.sql.Statement;

public class Main {

    private Statement stmt;
    private Connection conn;

    public static void main(String[] args) {

//        Country country = new CountryBuilder().setCode("123").setHeadOfState("Morty").setName("Bira").setSurfaceArea(1234).getCountry();
//        System.out.println(country);
      Menu menu = new Menu();
     menu.runMenu();
//      menu.printMenuHeader();
//       menu.printMenu();

       // menu.printListOfCountries();
////        MySQLCountryDAO test = new MySQLCountryDAO();
////        System.out.println(test.getCountry());

    }
}


