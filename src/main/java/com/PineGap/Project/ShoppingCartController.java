package com.PineGap.Project;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShoppingCartController {

    @GetMapping("/shoppingcart")
    public String shoppingcart(Model model){

         /* STRICTLY TEMPORARY CODE WE NEED TO INSTANTIATE DIFFERENT SHOPPING CART OBJECTS,
         OTHERWISE ALL USERS WILL HAVE THE SAME SHOPPING CART!
         */
        model.addAttribute("shoppingCart", ShoppingCart.getShoppingCart());

        model.addAttribute("subtotal", ShoppingCart.calculateSubtotal());

        return "shoppingcart.html";
    }

    @RequestMapping(value = "/shoppingcart/add/{id}")
    public String addToCart(@PathVariable int id, Model model) {

        /* STRICTLY TEMPORARY CODE WE NEED TO INSTANTIATE DIFFERENT SHOPPING CART OBJECTS,
         OTHERWISE ALL USERS WILL HAVE THE SAME SHOPPING CART!
         */
        
        LinkedHashMap < DataSet, Integer > shoppingCart = ShoppingCart.getShoppingCart();

        // TODO: only add some datapoints with probability 1 / n !
        for(DataSet dataSet: DataSet.getDataSets()){
            if(dataSet.getId() - 1 == id - 1){
                // first time adding to cart, the minimum amount of datapoints should be added
                if(ShoppingCart.getShoppingCart().get(dataSet) == null){
                    shoppingCart.put(dataSet, dataSet.getMinimum());
                } else if(shoppingCart.get(dataSet) + 1 <= dataSet.getMaximum()) {
                        // otherwise just add 1 to the current quantity up to a maximum
                        shoppingCart.replace(dataSet, shoppingCart.get(dataSet) + 1);
                    }
                break;
            }
        }

        return "redirect:/shoppingcart";
    }

    @GetMapping("/shoppingcart/decreaseQuantity/{id}")
    public String decreaseQuantityItem(@PathVariable int id, Model model){

        LinkedHashMap < DataSet, Integer > shoppingCart = ShoppingCart.getShoppingCart();

        /* STRICTLY TEMPORARY CODE WE NEED TO INSTANTIATE DIFFERENT SHOPPING CART OBJECTS,
         OTHERWISE ALL USERS WILL HAVE THE SAME SHOPPING CART!
         */
  
        for(DataSet dataSet: ShoppingCart.getShoppingCart().keySet()){
            // dont allow quantity to go below the minimum
            if(dataSet.getId() - 1 == id - 1 && shoppingCart.get(dataSet) > dataSet.getMinimum() ){
                shoppingCart.replace(dataSet, shoppingCart.get(dataSet) - 1);
                break;
            }
        }
       return "redirect:/shoppingcart";
    }

    @GetMapping("/shoppingcart/remove/{id}")
    public String removeItemFromShoppingcart(@PathVariable int id, Model model){

        LinkedHashMap < DataSet, Integer > shoppingCart = ShoppingCart.getShoppingCart();

        /* STRICTLY TEMPORARY CODE WE NEED TO INSTANTIATE DIFFERENT SHOPPING CART OBJECTS,
         OTHERWISE ALL USERS WILL HAVE THE SAME SHOPPING CART!
         */
        for(DataSet dataSet: ShoppingCart.getShoppingCart().keySet()){
            if(dataSet.getId() - 1 == id - 1){
                shoppingCart.remove(dataSet);
                break;
            }
        }
       return "redirect:/shoppingcart";
    }
    
}
