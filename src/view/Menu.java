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
        System.out.println("Type in Country Code: ");
       String countryCode = checkUserInput();

      //  String code = getCodeFromUser();
        Country country = countryDAO.getCountryByCode(countryCode);
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
        String headOfState = getHeadOfStateFromUser();
        Continent continent = getContinentFromUser();

        Country.CountryBuilder countryBuilder = new Country.CountryBuilder(code, name,continent,surfaceArea,headOfState);


        countryDAO.saveCountryInToDB(countryBuilder.build());
        runMenu();

    }

    //getting the head of state's name from the user and validating it
    public String getHeadOfStateFromUser() {

        System.out.println("Type in Head of State's name ");
       String headOfState = checkUserInput();
       while (!headOfState.matches("[A-Za-z]+")){

           System.out.println("Not Valid Please try again: ");
           getHeadOfStateFromUser();
        }

        return headOfState;
    }

        //getting surface area from user and doing some validation
        public double getSANameFromUser() {
        System.out.println("Type in surface Area: ");

        String countryArea = checkUserInput();

        while (!countryArea.matches("[0-9]+")) {

            System.out.println("Not Valid input type again: ");
            getSANameFromUser();
        }
        return Double.parseDouble(countryArea);

    }
    //getting Code from user and checking if it matches the validation set in codeValidation method
    public String getCodeFromUser() {

        System.out.println("Type in Country Code: ");

        String countryCode;
        countryCode = checkUserInput();
        boolean check = codeValidation(countryCode);
        getCountryByCode();
        while (!check){
            System.out.println("Invalid input must be 3 char long ");

            getCodeFromUser();
        }
        return countryCode;

   }
    //getting continent name from user
    public Continent getContinentFromUser() {
        Continent continent = null;
        String continentInput;
        System.out.println("Enter Continent");
        try {
            continentInput = checkUserInput().toUpperCase().replaceAll(" ","");
            continent = Continent.valueOf(continentInput);

        } catch (Exception e) {
            System.out.println(e);
            getContinentFromUser();
        }


        return continent;
    }

    public String getCountryNameFromUser() {

        System.out.println("Enter the country name ");
        String countryName = checkUserInput();

        if(countryName != null){
            if (countryName.matches("[A-Za-z ]+")){
                    return countryName;
            }
        }

        return getCountryNameFromUser();
    }

    public   String checkUserInput(){
         String validate ;

        try {
            validate = input.nextLine();
            return validate;

        }catch (Exception e){
            e.printStackTrace();
            checkUserInput();
        }
            return checkUserInput();
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


