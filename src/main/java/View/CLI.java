package View;

import Controller.ViewController;
import Model.Company;
import java.util.Scanner;

/**
 * @author Daniel Lopes
 */
public class CLI {

    /*
    Assign  view Controller to this class, so it can interact with other controllers in the background
    declare the 3 companies (Tesco, Lidl, Aldi) 
    
     */
    ViewController cliViewController;
    private final Company starbucksCompany;
    private final Company butlersCompany;
    private final Company insomniaCompany;
    private final Scanner menuScanner = new Scanner(System.in);
   

    public CLI() {
        /*
        Instantiate view controller as well as companies (tesco, lidl and aldi)
        this is happening in the background as soon as program starts.
         */
        cliViewController = new ViewController();

        insomniaCompany = cliViewController.makeInsomniaCompany();
        butlersCompany = cliViewController.makeButlersCompany();
        starbucksCompany = cliViewController.makeStarbucksCompany();

        cliViewController.makeTrades(starbucksCompany, butlersCompany, insomniaCompany);

        mainMenu();

    }

    private void mainMenu() {

        System.out.println("Welcome to the main menu!");
        System.out.println("Select one of the options below:");
        System.out.println("1) See all transactions.");
        System.out.println("2) Transactions for a particular company");

        String option = menuScanner.next();

        switch (option) {
            case "1":
                cliViewController.detailedTransactionOfAllCompanies(insomniaCompany, butlersCompany, starbucksCompany);
                mainMenu();
                break;
            case "2":
                System.out.println("Which company would you like to have information from?");
                System.out.println("Please insert the correspodent number (1 to 3)");
                System.out.println("1) Starbucks | 2) Insomnia | 3) Butlers");
                String companyName = menuScanner.next();
                switch (companyName) {
                    case "1":
                        particularCompanyMenu(starbucksCompany);
                        break;

                    case "2":
                        particularCompanyMenu(insomniaCompany);

                    case "3":
                        particularCompanyMenu(butlersCompany);

                    default:
                        System.out.println("Please select a valid number (1 to 3 ONLY)!!!");
                        System.out.println("You will be sent to the main menu)!!!");
                        System.out.println("---------------------------------------");
                        mainMenu();
                        break;
                }
                break;
            default:
                System.out.println("Please select a valid number (1 or 2 only)!!!");
                mainMenu();
                break;
        }

    }

    public void particularCompanyMenu(Company company) {

        System.out.println("What would you like to see from " + company.getCompanyName() + " Company?");
        System.out.println("1) See all transactions.");
        System.out.println("2) See own product stock.");
        System.out.println("3) See Foreign products stock.");
        System.out.println("4) See Cash balance.");
        System.out.println("5) Return to the main menu.");

        String companymenuOption = menuScanner.next();

        switch (companymenuOption) {
            case "1":
                cliViewController.showAllCompanyTransactions(company);
                System.out.println("------------------------------------------");
                mainMenu();
                break;
            case "2":
                cliViewController.showOwnProductStock(company);
                System.out.println("------------------------------------------");
                mainMenu();
                break;
            case "3":
                cliViewController.showForeignProductStock(company);
                System.out.println("------------------------------------------");
                mainMenu();
                break;
            case "4":
                cliViewController.showCashBalance(company);
                System.out.println("------------------------------------------");
                mainMenu();
                break;
            case "5":
                System.out.println("Okay. You will be sent to the main menu");
                System.out.println("------------------------------------------");
                mainMenu();
                break;
            default:
                System.out.println("Please only numbers from 1 to 5 are accepted!");
                System.out.println("You will be sent to the main menu. Please try again.");
                System.out.println("-----------------------------------------------------");
                mainMenu();

        }

    }

}
