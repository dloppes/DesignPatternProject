package Model;

/**
 *
 * @author Daniel Lopes
 *
 * This class will effectively workout the trades. 1) Receives 03 companies as
 * parameters 2) Loops through each one of the depots and buy and sell
 * accordingly to criteria. 3) A record of transactions is saved
 */
public class Trade {

    private final int nativeProductStockMin;
    private final int externalProductStockMax;

    public Trade() {
        // those values are unchangeable, therefore it is set as final.
        nativeProductStockMin = 15;
        externalProductStockMax = 40;
    }

    public void tradeSimulation(Company Starbucks, Company Butlers, Company Insomnia) {

        /*This is the method for the actual iteration.
        As the process happens multiple times, I simply take two parameters and do the procedure as many time as needed.
        I check if first buyer can effectively purchase, if returns negative I check the second buyer*/
 /* Compnay Starbucks as seller and Butlers and Insomnia as buyers*/
        for (Depot starbucksDepot : Starbucks.getDepotList()) {
            for (Depot ButlersDepot : Butlers.getDepotList()) {
                //if Starbucks is allowed to sell and Butlers is allowed to by , the trade is allowed to happen
                while (starbucksDepot.getCurrentNumberOfNativeProducts() > nativeProductStockMin && isAllowedToBuyValidatorFirstExternalStock(ButlersDepot, starbucksDepot)) {
                    //for the Starbucks I have to set number of native stock -1 & the amount of cash =(product price + delivery price)
                    starbucksDepot.setCurrentNumberOfNativeProducts(starbucksDepot.getCurrentNumberOfNativeProducts() - 1);
                    starbucksDepot.setDepotCash(starbucksDepot.getDepotCash() + (starbucksDepot.getProduct().getProductPrice() + starbucksDepot.getDeliveryPrice()));

                    /*for the Butlers add the external product into the array, increase the number of external product 
                    & deduct cash (Starbucks product price + Starbucks delivery price)*/
                    ButlersDepot.getExternalProducts().add(starbucksDepot.getProduct());
                    ButlersDepot.setFirstExternalCompanyStock(ButlersDepot.getFirstExternalCompanyStock() + 1);
                    ButlersDepot.setDepotCash(ButlersDepot.getDepotCash() - (starbucksDepot.getProduct().getProductPrice() + starbucksDepot.getDeliveryPrice()));

                }
            }
            for (Depot InsomniaDepot : Insomnia.getDepotList()) {

                //if Starbucks is allowed to sell and Insomnia is allowed to by , the trade is allowed to happen
                while (starbucksDepot.getCurrentNumberOfNativeProducts() > nativeProductStockMin && isAllowedToBuyValidatorFirstExternalStock(InsomniaDepot, starbucksDepot)) {
                    //for the Starbucks I have to set number of native stock -1 & the amount of cash =(product price + delivery price)
                    starbucksDepot.setCurrentNumberOfNativeProducts(starbucksDepot.getCurrentNumberOfNativeProducts() - 1);
                    starbucksDepot.setDepotCash(starbucksDepot.getDepotCash() + (starbucksDepot.getProduct().getProductPrice() + starbucksDepot.getDeliveryPrice()));

                    /*for the Insomnia add the external product into the array, increase the number of external product 
                    & deduct cash (Starbucks product price + Starbucks delivery price)*/
                    InsomniaDepot.getExternalProducts().add(starbucksDepot.getProduct());
                    InsomniaDepot.setFirstExternalCompanyStock(InsomniaDepot.getFirstExternalCompanyStock() + 1);
                    InsomniaDepot.setDepotCash(InsomniaDepot.getDepotCash() - (starbucksDepot.getProduct().getProductPrice() + starbucksDepot.getDeliveryPrice()));

                }

            }

        }

        /* Company Butlers as Seller and Starbucks and Insomnia as buyers*/
        for (Depot butlersDepot : Butlers.getDepotList()) {
            for (Depot starbucksDepot : Starbucks.getDepotList()) {
                //if Butlers is allowed to sell and Starbucks is allowed to by , the trade is allowed to happen
                while (butlersDepot.getCurrentNumberOfNativeProducts() > nativeProductStockMin && isAllowedToBuyValidatorFirstExternalStock(starbucksDepot, butlersDepot)) {
                    //for Butlers I have to set number of native stock -1 & the amount of cash =(product price + delivery price)
                    butlersDepot.setCurrentNumberOfNativeProducts(butlersDepot.getCurrentNumberOfNativeProducts() - 1);
                    butlersDepot.setDepotCash(butlersDepot.getDepotCash() + (butlersDepot.getProduct().getProductPrice() + butlersDepot.getDeliveryPrice()));

                    /*for Starbucks add the external product into the array, increase the number of external product 
                    & deduct cash (Butlers product price + butlers delivery price)*/
                    starbucksDepot.getExternalProducts().add(butlersDepot.getProduct());
                    starbucksDepot.setFirstExternalCompanyStock(starbucksDepot.getFirstExternalCompanyStock() + 1);
                    starbucksDepot.setDepotCash(starbucksDepot.getDepotCash() - (butlersDepot.getProduct().getProductPrice() + butlersDepot.getDeliveryPrice()));

                }
            }
            for (Depot InsomniaDepot : Insomnia.getDepotList()) {

                //if Butlers is allowed to sell and Insomnia is allowed to by , the trade is allowed to happen
                while (butlersDepot.getCurrentNumberOfNativeProducts() > nativeProductStockMin && isAllowedToBuyValidatorSecondExternalStock(InsomniaDepot, butlersDepot)) {
                    //for Butlers I have to set number of native stock -1 & the amount of cash =(product price + delivery price)
                    butlersDepot.setCurrentNumberOfNativeProducts(butlersDepot.getCurrentNumberOfNativeProducts() - 1);
                    butlersDepot.setDepotCash(butlersDepot.getDepotCash() + (butlersDepot.getProduct().getProductPrice() + butlersDepot.getDeliveryPrice()));

                    /*for Insomnia add the external product into the array, increase the number of external product 
                    & deduct cash (Butlers product price + Butlers delivery price)*/
                    InsomniaDepot.getExternalProducts().add(butlersDepot.getProduct());
                    InsomniaDepot.setSecondExternalCompanyStock(InsomniaDepot.getSecondExternalCompanyStock() + 1);
                    InsomniaDepot.setDepotCash(InsomniaDepot.getDepotCash() - (butlersDepot.getProduct().getProductPrice() + butlersDepot.getDeliveryPrice()));

                }

            }

        }

        /* Company Insomnia as Seller and Starbucks and Butlers as buyers*/
        for (Depot insomniaDepot : Insomnia.getDepotList()) {
            for (Depot starbucksDepot : Starbucks.getDepotList()) {
                //if Insomnia is allowed to sell and Starbucks is allowed to by , the trade is allowed to happen
                while (insomniaDepot.getCurrentNumberOfNativeProducts() > nativeProductStockMin && isAllowedToBuyValidatorSecondExternalStock(starbucksDepot, insomniaDepot)) {
                    //for Butlers I have to set number of native stock -1 & the amount of cash =(product price + delivery price)
                    insomniaDepot.setCurrentNumberOfNativeProducts(insomniaDepot.getCurrentNumberOfNativeProducts() - 1);
                    insomniaDepot.setDepotCash(insomniaDepot.getDepotCash() + (insomniaDepot.getProduct().getProductPrice() + insomniaDepot.getDeliveryPrice()));

                    /*for Starbucks add the external product into the array, increase the number of external product 
                    & deduct cash (Insomnia product price + Insomnia delivery price)*/
                    starbucksDepot.getExternalProducts().add(insomniaDepot.getProduct());
                    starbucksDepot.setSecondExternalCompanyStock(starbucksDepot.getSecondExternalCompanyStock() + 1);
                    starbucksDepot.setDepotCash(starbucksDepot.getDepotCash() - (insomniaDepot.getProduct().getProductPrice() + insomniaDepot.getDeliveryPrice()));

                }
            }

            for (Depot butlersDepot : Butlers.getDepotList()) {

                //if Insomnia is allowed to sell and Butlers is allowed to by , the trade is allowed to happen
                while (insomniaDepot.getCurrentNumberOfNativeProducts() > nativeProductStockMin && isAllowedToBuyValidatorSecondExternalStock(butlersDepot, insomniaDepot)) {
                    //for Butlers I have to set number of native stock -1 & the amount of cash =(product price + delivery price)
                    insomniaDepot.setCurrentNumberOfNativeProducts(insomniaDepot.getCurrentNumberOfNativeProducts() - 1);
                    insomniaDepot.setDepotCash(insomniaDepot.getDepotCash() + (insomniaDepot.getProduct().getProductPrice() + insomniaDepot.getDeliveryPrice()));

                    /*for Butlers add the external product into the array, increase the number of external product 
                    & deduct cash (Insomnia product price + Insomnia delivery price)*/
                    butlersDepot.getExternalProducts().add(insomniaDepot.getProduct());
                    butlersDepot.setSecondExternalCompanyStock(butlersDepot.getSecondExternalCompanyStock() + 1);
                    butlersDepot.setDepotCash(butlersDepot.getDepotCash() - (insomniaDepot.getProduct().getProductPrice() + insomniaDepot.getDeliveryPrice()));

                }

            }

        }
    }

    public boolean isAllowedToSellValidator(Depot depot) {
        /* has reached its minimum allowed native stock (15)?
        If so, depot is not allowed to sell anymore.
         */
        return depot.getCurrentNumberOfNativeProducts() > nativeProductStockMin;

    }

    public boolean isAllowedToBuyValidatorFirstExternalStock(Depot buyer, Depot seller) {
        /*
        has not reached external product limit(40)?
        has funds to purchase (totalOf = delivery + productCost)?
        if depot buyer still have space available in stock to buy and enougb money he is allowed to proceed with purchase.
         */

        return buyer.getFirstExternalCompanyStock() != externalProductStockMax && buyer.getDepotCash() >= (seller.getDeliveryPrice() + seller.getProduct().getProductPrice());
    }

    public boolean isAllowedToBuyValidatorSecondExternalStock(Depot buyer, Depot seller) {
        /*
        has not reached external product limit(40)?
        has funds to purchase (totalOf = delivery + productCost)?
        if depot buyer still have space available in stock to buy and enougb money he is allowed to proceed with purchase.
         */

        return buyer.getSecondExternalCompanyStock() != externalProductStockMax && buyer.getDepotCash() >= (seller.getDeliveryPrice() + seller.getProduct().getProductPrice());
    }

}
