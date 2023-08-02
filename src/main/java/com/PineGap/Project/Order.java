package com.PineGap.Project;

public class Order {
    private int id;
    private String fName;
    private String lName;
    private String addressL1;
    private String addressL2;
    private String province;
    private String email;
    private String cardName;
    private String cardNumber;
    private String displayOfLast4DigitsOfCard;
    private double subtotal;
    private String discountCode;
    private double discount;
    private double total;
    enum OrderStates {
        NEW,
        CANCELLED,
        DELIVERED
    }

    public OrderStates currOrderState = OrderStates.NEW;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName(){return fName;}
    public void setfName(String fName){this.fName = fName;}
    public String getlName(){return lName;}
    public void setlName(String lName){this.lName = lName;}
    public String getAddressL1(){return addressL1;}
    public void setAddressL1(String addressL1){this.addressL1 = addressL1;}
    public String getAddressL2(){return addressL2;}
    public void setAddressL2(String addressL2){this.addressL2 = addressL2;}
    public String getProvince(){return province;}
    public void setProvince(String province){this.province = province;}
    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}
    public String getCardName(){return cardName;}
    public void setCardName(String cardName){this.cardName = cardName;}
    public String getCardNumber(){return cardNumber;}
    public void setCardNumber(String cardNumber) {this.cardNumber = cardNumber;}
    public OrderStates getOrderState(){return currOrderState; }
    public void setOrderState(OrderStates state){this.currOrderState = state;}

    public String getDisplayOfLast4DigitsOfCard() {
        return displayOfLast4DigitsOfCard;
    }

    // method to create a display of last 4 digits of customer's card
    public void setDisplayOfLast4DigitsOfCard() {
        String last4Digits = "";
        last4Digits = cardNumber.substring(cardNumber.length()-4);

        displayOfLast4DigitsOfCard = "**** **** **** " + last4Digits;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getDiscountCode() {
        return discountCode;
    }
    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount() {
        switch (discountCode){
            case "PINEVALLEY10":
            case "CONOR10":
            case "TOM10":
                discount = subtotal * .1;
                break;
            case "PINEVALLEY20":
            case "FERAS20":
                discount = subtotal * .2;
                break;
            case "PINEVALLEY30":
            case "ETHAN30":
                discount = subtotal * .3;
                break;
            default:
                discount = 0;
        }
    }

    public double getTotal() {
        return total;
    }
    public void setTotal(){
        total = subtotal - discount;
    }
}
