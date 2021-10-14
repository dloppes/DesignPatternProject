package Controller;

import Model.Company;
import Model.Depot;
import Model.Trade;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Daniel Lopes
 */
/**
 * This class will deal with all requests and forward them to CompanyController
 * and DepotController Ultimately it will return the result of the requests to
 * be displayed on the screen (if necessary)
 */
public class ViewController {

    /* declare company Controller and Depot Controller so it can have access to all its methods*/
    private final CompanyController companyController;
    private final DepotController depotController;
   

    public ViewController() {

        //instantiating the controllers that have been declared previously.
        companyController = new CompanyController();
        depotController = new DepotController();

    }

    public void makeTrades(Company starbucksCompany, Company butlersCompany, Company insomniaCompany) {

        Trade trade = new Trade();
        trade.tradeSimulation(starbucksCompany, butlersCompany, insomniaCompany);

    }

    public Company makeStarbucksCompany() {

        Company starbucks = makeCompany("Starbucks", "A", "Coffee");

        return starbucks;
    }

    public Company makeButlersCompany() {

        Company butlers = makeCompany("Butlers", "B", "Chocolate");

        return butlers;
    }

    public Company makeInsomniaCompany() {

        Company insomnia = makeCompany("Insomnia", "C", "Tea");

        return insomnia;
    }

    public Company makeCompany(String name, String group, String productName) {

        /*This method deals with the makecompanies function in the controller class through companyController*/
        int number = 1; //number of depot in front of the group it belongs to e.o. A1 or B1
        Company newCompany = companyController.makeCompany(name, group);

        //looping through depotList array (already set number as 50 slots) and insert a depot per each iteration
        for (int i = 0; i < newCompany.getDepotList().length; i++) {
            //calling method that creates depots and inserting depot created into the company array
            newCompany.getDepotList()[i] = makeDepot(name, group + number, productName);
            number++; //through each iteration the number is increased so there is no depot name duplicated
        }

        return newCompany;

    }

    public Depot makeDepot(String companyName, String group, String productName) {
        /*This method deals with the makeDepot function in the controller class through depotController*/
        Depot newDepot = depotController.makeDepot(companyName, group, productName);

        return newDepot;

    }

    public void showCashBalance(Company company) {

        System.out.println("Follow the cash balance for each depot of " + company.getCompanyName());
        System.out.println("--------------------------------------------------------------------");
        
        ArrayList<String> cashBalance = new ArrayList<>();

        for (Depot depotList : company.getDepotList()) {

            System.out.println("Depot: " + depotList.getDepotName() + " |" + " Cash Balance: " + depotList.getDepotCash());
            cashBalance.add("Depot: " + depotList.getDepotName() + " |" + " Cash Balance: " + depotList.getDepotCash() + "\n");

        }
        
        saveReportToFile("cashBalance.txt", cashBalance);

    }

    public void showForeignProductStock(Company company) {
        
        ArrayList<String> foreignStockReport = new ArrayList<>();

        System.out.println("Follow the products that " + company.getCompanyName() + " bought from other companies.");
        System.out.println("------------------------------------------------------------------------------------");
        
        for (Depot depotList : company.getDepotList()) {

            System.out.println("Depot Name: " + depotList.getDepotName() + "\n"
                    + "First Company External Product: " + depotList.getFirstExternalCompanyStock()
                    + " | Second Company External Product: " + depotList.getSecondExternalCompanyStock());
           
            foreignStockReport.add("Depot Name: " + depotList.getDepotName() + "\n"
                    + "First Company External Product: " + depotList.getFirstExternalCompanyStock()
                    + " | Second Company External Product: " + depotList.getSecondExternalCompanyStock() + "\n");

        }
        
        saveReportToFile("foreignStockReport.txt", foreignStockReport);
    }

    public void showOwnProductStock(Company company) {

         ArrayList<String> nativeStockReport = new ArrayList<>();
        
        System.out.println("Follow the products that " + company.getCompanyName() + " has left in stock.");
        System.out.println("------------------------------------------------------------------------------------");

        int total = 0;
        for (Depot depotList : company.getDepotList()) {

            System.out.println("Depot: " + depotList.getDepotName() + " | stock:" + depotList.getCurrentNumberOfNativeProducts());
            total += depotList.getCurrentNumberOfNativeProducts();
            
            nativeStockReport.add("Depot: " + depotList.getDepotName() + " | stock:" + depotList.getCurrentNumberOfNativeProducts() + "\n");
           

        }
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("The sum of all remaining native stock is: " + total);
        
         saveReportToFile("nativeStockReport.txt", nativeStockReport);

    }

    public void showAllCompanyTransactions(Company company) {
        
        ArrayList<String> allcompaniesReport = new ArrayList<>();

        System.out.println("Here`s all transactions by " + company.getCompanyName());
        System.out.println("------------------------------------------------------");

        for (Depot depotList : company.getDepotList()) {

            System.out.println(depotList.toString());
            allcompaniesReport.add(depotList.toString() + "\n");

        }
        
         saveReportToFile("allcompaniesReport.txt", allcompaniesReport);

    }

    public void detailedTransactionOfAllCompanies(Company companyA, Company companyB, Company companyC) {
        
         ArrayList<String> detailedTransactionOfAllCompaniesReport = new ArrayList<>();

        System.out.println("Here`s all transactions by depots from " + companyA.getCompanyName());
        System.out.println("------------------------------------------------------");

        for (Depot companyADepots : companyA.getDepotList()) {
            System.out.println(companyADepots.toString());
            detailedTransactionOfAllCompaniesReport.add(companyADepots.toString() + "\n");
        }

        System.out.println("------------------------------------------------------");
        System.out.println("Here`s all transactions by depots from " + companyB.getCompanyName());
        System.out.println("------------------------------------------------------");

        for (Depot companyBDepots : companyB.getDepotList()) {
            System.out.println(companyBDepots.toString());
            detailedTransactionOfAllCompaniesReport.add(companyBDepots.toString() + "\n");
        }

        System.out.println("------------------------------------------------------");
        System.out.println("Here`s all transactions by depots from " + companyC.getCompanyName());
        System.out.println("------------------------------------------------------");

        for (Depot companyCDepots : companyC.getDepotList()) {
            System.out.println(companyCDepots.toString());
            detailedTransactionOfAllCompaniesReport.add(companyCDepots.toString() + "\n");
        }
        
        saveReportToFile("detailedTransactionOfAllCompaniesReport.txt", detailedTransactionOfAllCompaniesReport);

    }
    
    public void saveReportToFile(String fileName, ArrayList<String> documentToSave) {

       
        try {
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file,false);
            PrintWriter pw = new PrintWriter(writer);
            
            pw.print(documentToSave.toString());
           pw.close();
           writer.close();
            System.out.println("------------------------------------------------------");
            System.out.println("A copy of this report has been saved as: " + fileName);
           
        } catch (IOException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
}
