package Controller;

import Model.Company;

/**
 *
 * @author Daniel Lopes
 * 
 * This class takes requests to interact with the Company class
 */
public class CompanyController {

    public Company makeCompany(String name, String group) {
        //accessing company builder inner class and creating object company.
        Company myCompany = new Company.CompanyBuilder(name, group).build();
        return myCompany;
    }

}
