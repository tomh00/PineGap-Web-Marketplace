package com.PineGap.Project;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class CheckoutController {
    private static HashMap<Integer, Order> orders = new HashMap<>();
    private int id = 0;
    private Order currentOrder;

    public static HashMap<Integer, Order> getOrders(){
        return orders;
    }

    // GET map to checkout page
    @GetMapping("/checkout")
    public String checkout(Model model) {
        LinkedHashMap< DataSet, Integer > shoppingCart = ShoppingCart.getShoppingCart();

        // add shopping cart items and cart total to model to be displayed on checkout page
        model.addAttribute("shoppingCart", shoppingCart);
        model.addAttribute("subtotal", Math.round(ShoppingCart.calculateSubtotal()*100.0)/100.0);
        return "checkout.html";
    }

    @PostMapping("/checkout")
    public @ResponseBody void orderConfirmed(Order order, HttpServletResponse servletResponse){
        order.setId(id);
        id++; // oder id is just the quantity of orders
        order.setSubtotal(Math.round(ShoppingCart.calculateSubtotal()*100.0)/100.0);
        order.setDiscount(); // discount is calculated according to the discount code received
        order.setTotal();
        order.setDisplayOfLast4DigitsOfCard();
        currentOrder = order;
        orders.put(currentOrder.getId(), currentOrder);
        try{servletResponse.sendRedirect("/orderconfirmed");}catch (IOException e){
            e.printStackTrace();
        }
    }

    @GetMapping("/orderconfirmed")
    public String displayConfirmation(Model model){
        LinkedHashMap< DataSet, Integer > shoppingCart = ShoppingCart.getShoppingCart();

        model.addAttribute("order", currentOrder);
        model.addAttribute("shoppingCart", shoppingCart);
        return "orderconfirmed.html";
    }

    @GetMapping("/changeOrderStateToCancelled/{id}")
    public String changeOrderStateToCanceleld(@PathVariable int id, Model model) {
        for(Order order: orders.values()){
            if(order.getId() == id){
                order.setOrderState(Order.OrderStates.CANCELLED);
            }
        }
        return "redirect/orderHistory";
    }

    @GetMapping("/changeOrderStateToDelivered/{id}")
    public String changeOrderStateToDelievered(@PathVariable int id, Model model) {
        for(Order order: orders.values()){
            if(order.getId() == id){
                order.setOrderState(Order.OrderStates.DELIVERED);
            }
        }
        return "redirect:/orderHistory";
    }

  
}