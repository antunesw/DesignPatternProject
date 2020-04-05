package view;
/**
 * @Author Willian Antunes de Sousa
 */

import country.Continent;
import country.Country;
import country.CountryDAO;
import country.MySQLCountryDAO;
import dbConnection.DB_Connect;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

//Menu Class is bridge between classes
public class Menu {
    //Instances needed
    private DB_Connect conn;
    private CountryDAO countryDAO = new MySQLCountryDAO();
    Scanner input = new Scanner(System.in);
   // used for the menu options
    boolean exit;

    //Simple Header menu is displayed when program starts
    public void printMenuHeader() {

        System.out.println("+-------------------------------+");
        System.out.println("|      WELCOME TO THE MENU      |");
        System.out.println("+ ------------------------------+");
    }
    //menu presenting the options
    public void printMenu() {

        System.out.println("1 - Show countries in the database ");
        System.out.println("2 - Search by country name");
        System.out.println("3 - Search by Country code ");
        System.out.println("4 - Insert a country in the database ");
        System.out.println("5 - Exit ");
    }

    // method responsible for the interaction among options presented to the user and input received
    public void runMenu() {
        printMenuHeader();
        while (!exit) {
            printMenu();
            int choice = getInput();
            performAction(choice);

        }
    }

    // method used for checking if the user input meets the criteria
    public int getInput() {
        Scanner input = new Scanner(System.in);
        int choice = -1;

        while (choice < 0 || choice > 5)
            try {

                System.out.println("\n |//Select  option//|");

                choice = Integer.parseInt(input.nextLine());
                if (choice < 0 || choice > 5) {
                    System.out.println("invalid option try again");
                    runMenu();
                }

            } catch (NumberFormatException e) {

                System.out.println("Invalid please try again");
                runMenu();
            }
        return choice;
    }

  // method for directing users to the chosen option
    public void performAction(int choice) {

        switch (choice) {
            case 1:
                System.out.println("Option 1 Selected ");
                printListOfCountries();
                break;
            case 2:
                getCountryByName();
                System.out.println("Option2 Selected ");
                break;
            case 3:
                getCountryByCode();
                System.out.println("Option2 Selected ");


                break;
            case 4:
                saveCountryInToDB();
                System.out.println("Option2 Selected ");


                break;
            case 5:
                System.out.println("Bye!");
                conn.closing();
                exit = true;
                break;
            default:
                System.out.println("NOT A OPTION");


        }

    }

    public void printListOfCountries() {

        ArrayList<Country> listAllCountries = countryDAO.getListOfCountries();
        //Iterator object is used  to Access elements on the  ArrayList
        Iterator<Country> countryIterator = listAllCountries.iterator();

        if (listAllCountries.size() > 0) {
           //loops while the list has elements to iterate
            while (countryIterator.hasNext()) {
                // gets country in the list and prints it.
                System.out.println(countryIterator.next());
            }
        } else {
            System.out.println("Not Found");
        }
    }

    public void getCountryByName() {
        //get name of the country from user and storing in a variable
        String countryName = getCountryNameFromUser();
        //ArrayList for storing the countries
        ArrayList<Country> listAllCountries = countryDAO.getCountryByName(countryName);
        //Iterator object is used  to Access elements on the  ArrayList
        Iterator<Country> countryIterator = listAllCountries.iterator();

        if (listAllCountries.size() > 0) {
            //loops while the list has elements to iterate
            while (countryIterator.hasNext()) {
                // gets country in the list and prints it.
                System.out.println(countryIterator.next());
            }
        } else {
            System.out.println("country not found");
        }


    }

    public void getCountryByCode() {
        String code = getCodeFromUser();
        Country country = countryDAO.getCountryByCode(code);
        if (country != null) {
            System.out.println(country.toString());
        } else {
            System.out.println("country code not found.");
        }

    }

    public void saveCountryInToDB() {
        System.out.println("##ADD a new country to the database###");

        String code = getCodeFromUser();
        String name = getCountryNameFromUser();
        Double surfaceArea = getSANameFromUser();
        Continent continent = getContinentFromUser();
        String headOfState = getHeadOfStateFromUser();

        Country.CountryBuilder countryBuilder = new Country.CountryBuilder(code, name);
        if (surfaceArea == null){
            countryBuilder.setHeadOfState("UNKNOWN");
        }
        if (continent == null){
            countryBuilder.setContinent(Continent.valueOf("ASIA"));
        }if (headOfState == null){
            countryBuilder.setHeadOfState("WILL");
        }
        countryDAO.saveCountryInToDB(countryBuilder.build());
        runMenu();

    }

    public String getHeadOfStateFromUser() {
        String headOfState = "";
        String headOfStateInput;
        System.out.println("Type in Head of State's name ");
        headOfStateInput = input.nextLine().replaceAll("", " ");
        while (!headOfStateInput.matches("[A-Za-z' ]+")) {
            System.out.println("Please Enter a valid input");
            System.out.println("Head of state: ");
            headOfStateInput = input.nextLine();
        }
        return headOfState;
    }

    public double getSANameFromUser() {
        System.out.println("Type in surface area ");
        double area = 0;
        boolean exit = false;

        try {
            while (!exit) {


                area = Double.parseDouble(input.nextLine().replaceAll(" ", ""));
                exit = true;
            }
        } catch (NumberFormatException e) {
            System.out.println("integer and floating point numbers only ");

        }
        return area;
    }

    public String getCodeFromUser() {
        System.out.println("Type in Country Code: ");
        String countryCode = input.nextLine();
        boolean check= codeValidation(countryCode);
        while (!check){
            System.out.println("Invalid input ");
            runMenu();

        }
        return countryCode;

    }

    public Continent getContinentFromUser() {
        Continent continent = null;
        String continentInput;
        System.out.println("Enter Continent");
        try {
            continentInput = input.nextLine().toUpperCase().replace(" ", "_");
            continent = Continent.valueOf(continentInput);

        } catch (Exception e) {
            System.out.println(e);
            runMenu();
        }


        return continent;
    }

    public String getCountryNameFromUser() {

        System.out.println("Enter the country name ");
        String countryName = null;


        try {


            countryName = input.nextLine().replaceAll(" ", "");


        } catch (Exception e) {
            System.out.println(e);
        }
        return countryName;
    }

    public static boolean codeValidation(String code) {

            if ( code.length() != 3) {
                    return false;
            } if (!(code.matches("[A-Za-z0-9]+"))){
                    return false;
            }
            return true;


    }
}


