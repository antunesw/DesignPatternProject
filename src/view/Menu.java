package view;

import country.Continent;
import country.Country;
import country.CountryDAO;
import country.MySQLCountryDAO;
import dbConnection.DB_Connect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Menu {
    private DB_Connect conn;
    private CountryDAO countryDAO = new MySQLCountryDAO();
    Scanner input = new Scanner(System.in);
    boolean exit;

    public void printMenuHeader() {

        System.out.println("+-------------------------------+");
        System.out.println("|      WELCOME TO THE MENU      |");
        System.out.println("+ ------------------------------+");
    }

    public void printMenu() {

        //  System.out.println("\n--MAKE A SELECTION--");
        System.out.println("1 - Show countries in the database ");
        System.out.println("2 - Search by country name");
        System.out.println("3 - Search by Country code ");
        System.out.println("4 - Insert a country in the database ");
        System.out.println("5 - Exit ");
    }

    public void runMenu() {
        printMenuHeader();
        while (!exit) {
            printMenu();
            int choice = getInput();
            performAction(choice);

        }
    }

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

        Iterator<Country> countryIterator = listAllCountries.iterator();
        if (listAllCountries.size() > 0) {
            while (countryIterator.hasNext()) {
                System.out.println(countryIterator.next());
            }
        } else {
            System.out.println("Not Found");
        }
    }

    public void getCountryByName() {
        String countryName = getCountryNameFromUser();
        ArrayList<Country> listAllCountries = countryDAO.getCountryByName(countryName);
        Iterator<Country> countryIterator = listAllCountries.iterator();

        if (listAllCountries.size() > 0) {

            while (countryIterator.hasNext()) {
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

        Country.CountryBuilder countryBuilder = new Country.CountryBuilder(code,name);

        countryDAO.saveCountryInToDB(countryBuilder.build());


    }


//    Country newCountry = null;
//
//    String countryCode;
//    String countryName;
//    Continent continentOfCountry;
//    String headOfState;
//    float surfaceArea;

    public String getHeadOfStateFromUser(){
         String headOfState = "";
        String headOfStateInput;
         System.out.println("Type in Head of State's name ");
         headOfStateInput = input.nextLine().replaceAll(""," ");
        while (!headOfStateInput.matches("[A-Za-z' ]+")) {
            System.out.println("Please Enter a valid input");
            System.out.println("Head of state: ");
            headOfStateInput = input.nextLine();
        }
        return  headOfState;
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
        System.out.println("Type in Country Code between 1 to 3 char");
        String countryCode = "";

        try {

            //if()else please validate for many letters try to do itcountryCode = input.nextLine().replaceAll(" ", "");
                if (countryCode.length() > 0 || countryCode.length() < 3) {
                    countryCode = input.nextLine().replaceAll(" ", "");

                    if (countryCode.matches("[A-Za-z0-9]+")) {
                        return countryCode;
                    }
                }else{getCodeFromUser();}
    }catch (Exception e){
            System.out.println("1 to 3 characters input only");

            getCodeFromUser();

            System.out.println(e);
        }
    return null;
    }

    public Continent getContinentFromUser() {
        Continent continent = null;
        String continentInput;
        System.out.println("Enter Continent");
        try {
            continentInput = input.nextLine().toUpperCase().replace(" ","_");
            continent = Continent.valueOf(continentInput);

        }catch (Exception e){
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


}



