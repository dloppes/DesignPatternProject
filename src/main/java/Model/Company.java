package Model;

import java.util.Arrays;

/**
 *
 * @author Daniel Lopes
 * 
 * This class has all the attributes necessary to create a Company object as well as a company Builder inner class.
 */
public class Company {

    private String companyName;
    private String companyGroup;
    private final Depot[] depotList;

    private Company(CompanyBuilder companyBuilder) {
        companyName = companyBuilder.companyName;
        companyGroup = companyBuilder.companyGroup;
        depotList = companyBuilder.depotList;
    }

    public String getCompanyGroup() {
        return companyGroup;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Depot[] getDepotList() {
        return depotList;
    }

    @Override
    public String toString() {
        return "Company{" + "companyName=" + companyName + ", companyGroup=" + companyGroup + ", depotList=" + Arrays.toString(depotList) + '}';
    }

    public static class CompanyBuilder implements Interfaces.CompanyInterface {

        // Same attributes as the company class
        private String companyName;
        private String companyGroup;
        private final Depot[] depotList;
       

        // Here we define the mandatory attributes
        // and a default value for the optional ones
        public CompanyBuilder(String name, String group) {
            companyName = name;
            companyGroup = group;
            depotList = new Depot[50];

        }

        @Override
        public CompanyBuilder setCompanyName(String name) {
            companyName = name;
            return this;
        }

        @Override
        public CompanyBuilder setCompanyGroup(String group) {
            companyGroup = group;
            return this;
        }

        // This is the method to return the actual
        // instance of visa. And this guy, is in charge
        // of the instantiation process
        public Company build() {
            return new Company(this);
        }

    }

}
