/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Daniel Lopes
 */
public class Product {
    
    private String productName;
    private double productPrice;
    private String companyName;
    
    public Product(ProductBuilder productBuilder){
        
        productName = productBuilder.productName;
        productPrice = productBuilder.productPrice;
        companyName = productBuilder.companyName;
        
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    @Override
    public String toString() {
        return "Product{" + "companyName=" + companyName + ", productName=" + productName + ", productPrice=" + productPrice  + '}';
    }
    
    public static class ProductBuilder {
         
        private String productName;
        private double productPrice;
        private String companyName;
        
         public ProductBuilder(String companyName, String name, double price) {

             this.companyName = companyName;
            productName = name;
            productPrice = price;
          
        }
         
         
          public Product build() {
            return new Product(this);
        }
     }
    
}
