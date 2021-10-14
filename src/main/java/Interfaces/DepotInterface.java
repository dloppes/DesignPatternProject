/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Model.Depot;

/**
 *
 * @author Daniel Lopes
 */
public interface DepotInterface {

    public Depot.DepotBuilder setCurrentNumberOfNativeProductStock(int currentNumber);
    
     public Depot.DepotBuilder setDepotCash(double newAmount);


}
