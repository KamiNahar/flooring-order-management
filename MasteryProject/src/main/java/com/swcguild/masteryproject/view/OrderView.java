/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.view;

import com.swcguild.masteryproject.DTO.OrderDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author kaminahar
 */
public class OrderView {
    
private UserIO io;
 
public OrderView (UserIO io ) {
      this.io = io;
      
} 

public int printMenuAndGetSelection() {
  
    io.print("Main Menu");
    io.print("1. Display Orders "); 
    io.print("2. Display Order ");
    io.print("3. Add Order ");
    io.print("4. Edit Order ");
    io.print("5. Remove Order ");
    io.print("6. Exit ");
            
    return io.readInt("Please select an option from the menu above.", 1, 6);  
}

public void displayOrdersBanner() {
    io.print("==========Display Orders==========");
}

public void displayOrderBanner() {
    io.print("==========Display Order==========");
}

public void displayAddOrderBanner() {
    io.print("==========Add Order==========");   
}

public void displayEditOrderBanner() {
    io.print("==========Edit Order==========");  

}


public void displayRemoveOrderBanner() {
    io.print("==========Remove Order==========");   
}

public int askForOrderNumber() {
  return io.readInt("Enter order ID number.");  
}


public LocalDate askForOrderDate() { 
   io.readString("Enter order date (MMddyyyy)"); 
    return LocalDate.now(); //localdate now gives you today's date not the user date. 
} 

public void orderUpdatedBanner() {
    io.print("Order updated. ");
}

public boolean saveOrder() {
    boolean saveOrder = true; 
    
    String askUserToSave = io.readString("Do you want to save this order? (Y/N) ");
    if (askUserToSave.equalsIgnoreCase("Y")) {
        saveOrder = true;
    }
    if (askUserToSave.equalsIgnoreCase("N")) {
        saveOrder = false; 
    }
    
    return saveOrder; 
}

public void orderRemovedBanner() {
    io.print("Order removed. ");
}



public void displayOrderList (List<OrderDTO> orders){
    for (OrderDTO currentOrderDTO : orders) {
	        io.print
	                ( "Order Number: " + currentOrderDTO.getOrderNumber()+ ", "
	                + "Customer Name: " + currentOrderDTO.getCustomerName()+ ", "
                        + "State: " + currentOrderDTO.getState()+ ", "
                        + "Tax Rate: " + currentOrderDTO.getOrderTaxRate()+ ", "
                        + "Product Type: " + currentOrderDTO.getProductType()+ ", "
                        + "Area: " + currentOrderDTO.getArea()+ ", "
                        + "Cost Per Square Foot: " + currentOrderDTO.getCostPerSquareFoot()+ ", "
                        + "Labor Cost Per Square Foot: " + currentOrderDTO.getLaborCostPerSquarefoot()+ ", "
                        + "Material Cost: " + currentOrderDTO.getMaterialCost()+ ", "
                        + "Labor Cost: " + currentOrderDTO.getLaborCost()+ ", "  
                        + "Total Tax: " + currentOrderDTO.getTotalTax()+ ", "
                        + "Sub Total: " + currentOrderDTO.getSubTotal());
	    }
	    
            io.readString("Please hit enter to continue.");
	
} 

public void displayOrder(OrderDTO orderDTO) {
            if (orderDTO != null) {

                io.print("Order Number: " + orderDTO.getOrderNumber());
                io.print("Customer Name: " + orderDTO.getCustomerName());
                io.print("State: " + orderDTO.getState());
                io.print("Product Type: " + orderDTO.getProductType());
                
//                io.print(orderDTO.getOrderTaxRate()); 
//                io.print(orderDTO.getArea());
//                io.print(orderDTO.getCostPerSquareFoot());
//                io.print(orderDTO.getLaborCostPerSqft());
//                io.print(orderDTO.getMaterialCost());
//                io.print(orderDTO.getLaborCost());
//                io.print(orderDTO.getTotalTax());
//                io.print(orderDTO.getSubTotal());
           
            } else {
                io.print("This order is not found.");
            }
            io.readString("Please hit enter to continue.");
} 


//public String askWhichState (){
//    
//    String s1 = io.readString("\nSelect a state and enter the state abbreviation. \nState Abbreviations are: \nOH\n" +
//"PA\n" +
//"MI\n" +
//"IN\n");
// return s1.toLowerCase();
//}


public String askWhichState () {   
return io.readString("\nSelect a state and enter the state abbreviation. \nState Abbreviations are: \nOH\n" +
"PA\n" +
"MI\n" +
"IN\n");
}

public BigDecimal askStateTaxRate() {
 return io.readBigDecimal("\nEnter the state tax rate based on the state.\nState tax rates are: \nOH::6.25\n" +
"PA::6.75\n" +
"MI::5.75\n" +
"IN::6.00\n");   
}

//for any need method you want to use in other methods you know you will be calling 
//later, make sure to have a return type. The return type depends on what you need from
//the method. 
public String  askProductType() {
return io.readString("\nEnter the product types. \nProduct types are : \nCarpet\n" +
"Laminate\n" +
"Tile\n" +
"Wood\n");
}

public BigDecimal askCostPerSquareFoot() {
return io.readBigDecimal("\nEnter the cost per square foot. \nSelect based on the product: \nCarpet::2.25\n" +
"Laminate::1.75\n" +
"Tile::3.50\n" +
"Wood::5.15\n");  
}

public BigDecimal askLaborCostPerSquareFoot() {
 return io.readBigDecimal("\nEnter the labor cost per square foot. \nSelect based on the product: \nCarpet::2.10\n" +
"Laminate::2.10\n" +
"Tile::4.15\n" +
"Wood::4.75\n");   
}



    public void errorMsgForStateInfo(String msg) {
        io.print(msg);
    }

public void displayExitMessageBanner(){
      io.print("Goodbye. ");
}

    public String askForCustomerName() {
     return io.readString("Enter your name.");
    }

    public BigDecimal askArea() {
return io.readBigDecimal("Enter the area.");
    }

}