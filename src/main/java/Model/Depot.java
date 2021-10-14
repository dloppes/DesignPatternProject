package Model;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Daniel Lopes
 *
 * This class has all the attributes necessary to create a depot object as well
 * as a depot Builder inner class.
 */
public class Depot {

    private final String depotName;
    private Product product;
    private final double deliveryPrice;
    private double depotCash;
    private ArrayList<Product> externalProducts;
    private int firstExternalCompanyStock;
    private int secondExternalCompanyStock;
    private int currentNumberOfNativeProducts;

    private Depot(DepotBuilder depotBuilder) {
        //values are assigned via depotBuilder
        depotName = depotBuilder.depotName;
        product = depotBuilder.product;
        deliveryPrice = depotBuilder.deliveryPrice;
        depotCash = depotBuilder.depotCash;
        externalProducts = depotBuilder.externalProducts;
        currentNumberOfNativeProducts = depotBuilder.currentNumberOfNativeProducts;
        firstExternalCompanyStock = depotBuilder.firstExternalCompanyStock;
        secondExternalCompanyStock = depotBuilder.secondExternalCompanyStock;

    }

    public int getFirstExternalCompanyStock() {
        return firstExternalCompanyStock;
    }

    public void setFirstExternalCompanyStock(int firstExternalCompanyStock) {
        this.firstExternalCompanyStock = firstExternalCompanyStock;
    }

    public int getSecondExternalCompanyStock() {
        return secondExternalCompanyStock;
    }

    public void setSecondExternalCompanyStock(int secondExternalCompanyStock) {
        this.secondExternalCompanyStock = secondExternalCompanyStock;
    }

    public String getDepotName() {
        return depotName;
    }

    public Product getProduct() {
        return product;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public int getCurrentNumberOfNativeProducts() {

        return currentNumberOfNativeProducts;
    }

    public double getDepotCash() {
        return depotCash;
    }

    public ArrayList<Product> getExternalProducts() {
        return externalProducts;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setCurrentNumberOfNativeProducts(int currentNumberOfNativeProducts) {
        this.currentNumberOfNativeProducts = currentNumberOfNativeProducts;
    }

    public void setDepotCash(double depotCash) {
        depotCash = Double.parseDouble(new DecimalFormat("##.##").format(depotCash));

        this.depotCash = depotCash;
    }

    public void setExternalProducts(ArrayList<Product> externalProducts) {
        this.externalProducts = externalProducts;
    }

    @Override
    public String toString() {
        return "Depot " + getDepotName() + " \n" + 
        "Native Product: " + getCurrentNumberOfNativeProducts() 
                + " | Product Name: " + getProduct().getProductName() 
                + " | Depot Cash:" + getDepotCash() 
                +   " | First Company - External Product: " + getFirstExternalCompanyStock() 
                + " | Second Company - External Product: "  + getSecondExternalCompanyStock() ;
        
    }

    public static class DepotBuilder implements Interfaces.DepotInterface {

        private String depotName;
        private Product product;
        private double deliveryPrice;
        private double depotCash;
        private int currentNumberOfNativeProducts;
        private ArrayList<Product> externalProducts;
        private int firstExternalCompanyStock;
        private int secondExternalCompanyStock;

        public DepotBuilder(String name, double deliveryCost, double cash, Product product, int nativeStock, int firstExternalStock, int secondExternalStock) {

            depotName = name;
            this.product = product;
            deliveryPrice = deliveryCost;
            depotCash = cash;
            currentNumberOfNativeProducts = nativeStock;
            firstExternalCompanyStock = firstExternalStock;
            secondExternalCompanyStock = secondExternalStock;
            externalProducts = new ArrayList<>();

        }

        @Override
        public DepotBuilder setDepotCash(double newAmount) {
            depotCash = newAmount;
            return this;
        }

        @Override
        public DepotBuilder setCurrentNumberOfNativeProductStock(int currentNumber) {
            currentNumberOfNativeProducts = currentNumber;
            return this;
        }

        public Depot build() {
            return new Depot(this);
        }

    }

}
