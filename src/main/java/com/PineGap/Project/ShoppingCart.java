package com.PineGap.Project;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ShoppingCart {

    /* STRICTLY TEMPORARY CODE WE NEED TO INSTANTIATE DIFFERENT SHOPPING CART OBJECTS,
         OTHERWISE ALL USERS WILL HAVE THE SAME SHOPPING CART!
    */

    // hashmap of dataset objects along with the quantity that the customer wants
    private static LinkedHashMap < DataSet, Integer > shoppingCart = new LinkedHashMap < DataSet, Integer > ();

   public static ArrayList<String> dataPointsSelected = new ArrayList<String>();


    public static LinkedHashMap<DataSet, Integer> getShoppingCart(){
        return shoppingCart;
    }

    public static int getShoppingCartSize(){
       return shoppingCart.size();
    }

    public static double calculateSubtotal(){
        double subtotal = 0;
       
        for(DataSet dataSet: shoppingCart.keySet()){
            subtotal += dataSet.getPricePerDataPoint() * shoppingCart.get(dataSet);
        }
        return subtotal;
    }
    
}
