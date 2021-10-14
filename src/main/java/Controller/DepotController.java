package Controller;

import Model.Depot;
import Model.Product;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Daniel Lopes
 *
 * This class takes requests to interact with the Depot class
 */
public class DepotController {

    public Depot makeDepot(String companyName, String name, String productName) {

        /*
         This class returns a deposit with its set values.
        price, delivery cost and cash allowance is set randomly.
      
         */
        double price;
        double deliveryCost;
        double cash;

        double[] productPrice = randomDoublePriceGenerator();
        price = productPrice[indexRandomGenerator(productPrice)];
        //formatting the decimal part of the Double so it appears only two digits after (.)
        price = Double.parseDouble(new DecimalFormat("##.##").format(price));

        double[] deliveryPrice = randomDoublePriceGenerator();
        deliveryCost = deliveryPrice[indexRandomGenerator(deliveryPrice)];
        //formatting the decimal part of the Double so it appears only two digits after (.)
        deliveryCost = Double.parseDouble(new DecimalFormat("##.##").format(deliveryCost));

        double[] cashAvailable = randomDoubleCashAllowanceGenerator();
        cash = cashAvailable[indexRandomGenerator(cashAvailable)];
        //formatting the decimal part of the Double so it appears only two digits after (.)
        cash = Double.parseDouble(new DecimalFormat("##.##").format(cash));

        Product newProduct = new Product.ProductBuilder(companyName, productName, price).build();

        int nativeStock = randomStockLimitGenerator(15, 50); // getting a random number for Native Stock, between 15(min) & 50(max)
        int firstExternalCompanyStock = randomStockLimitGenerator(3, 40); // getting a random number for Native Stock, between 3(min) & 40(max)
        int secondExternalCompanyStock = randomStockLimitGenerator(3, 40); // getting a random number for Native Stock, between 3(min) & 40(max)

        Depot newDepot = new Depot.DepotBuilder(name, deliveryCost, cash, newProduct, nativeStock, firstExternalCompanyStock, secondExternalCompanyStock).build();

        return newDepot;
    }

    public int randomStockLimitGenerator(int minimum, int maximum) {

        //The random Number from this class will return a number for the amount of native and external product each depot will be allowed to have.
        int randomNum = ThreadLocalRandom.current().nextInt(minimum, maximum + 1);

        return randomNum;
    }

    public double[] randomDoublePriceGenerator() {

        //The price goes from 1 to 10, to ten slots (streamSize) with random numbers between 1 and 10
        double[] randoms = ThreadLocalRandom.current().doubles(10, 1, 10).toArray();

        return randoms;

    }

    public double[] randomDoubleCashAllowanceGenerator() {

        //The cash goes from 50 to 100, to fifty slots (streamSize) with random numbers between 50 and 100.
        double[] randoms = ThreadLocalRandom.current().doubles(50, 50, 100).toArray();

        return randoms;

    }
    
    public int indexRandomGenerator(double[] array) {
        /* As certain property values are set randomly I am using this function to get a random ID of any array passed*/

        Random generator = new Random();
        int randomIndex = generator.nextInt(array.length);

        return randomIndex;

    }

}
