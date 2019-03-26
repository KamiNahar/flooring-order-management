/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.controller;

import com.swcguild.masteryproject.DAO.OrderDAO;
import com.swcguild.masteryproject.DTO.OrderDTO;
import com.swcguild.masteryproject.DTO.ProductDTO;
import com.swcguild.masteryproject.service.InvalidDateException;
import com.swcguild.masteryproject.service.InvalidProductTypeException;
import com.swcguild.masteryproject.service.InvalidStateException;
import com.swcguild.masteryproject.service.MinAreaValidationException;
import com.swcguild.masteryproject.service.NameValidationException;
import com.swcguild.masteryproject.service.PersistenceException;
import com.swcguild.masteryproject.service.ServiceLayer;
import com.swcguild.masteryproject.view.OrderView;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author kaminahar
 */
public class Controller {

    OrderView view;
    ServiceLayer serviceLayer;
  

    public Controller(OrderView view, ServiceLayer serviceLayer) {
        this.view = view;
        this.serviceLayer = serviceLayer;
     
    }

    public void run() throws PersistenceException, InvalidDateException, InvalidProductTypeException, InvalidStateException, MinAreaValidationException, NameValidationException {

        boolean isRunning = true;
       // int menuSelection = 0;
        while (isRunning) {

           int menuSelection = getMenuSelection();

            switch (menuSelection) {

                case 1:
                    displayOrders();
                    break;
                case 2:
                    displayOrder();
                    break;
                case 3:
                    addOrder();
                    break;
                case 4:
                    editOrder();
                    break;
                case 5:
                    removeOrder();
                    break;
                case 6:
                    isRunning = false;
                    break;
                default:

            }

        }
        exitMessage();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void displayOrders() throws PersistenceException {
        view.displayOrdersBanner();
        LocalDate orderDate = view.askForOrderDate();
        List<OrderDTO> orders = serviceLayer.getAllOrdersByDate(orderDate);
        view.displayOrderList(orders);
    }

    private void displayOrder() throws PersistenceException {
        view.displayOrderBanner();
        LocalDate orderDate = view.askForOrderDate();
        int orderNumber = view.askForOrderNumber();
        OrderDTO orderDTO = serviceLayer.displayOrder(orderDate, orderNumber);
        view.displayOrder(orderDTO);
    }


    
    private void addOrder() throws PersistenceException, InvalidDateException, InvalidProductTypeException, InvalidStateException, MinAreaValidationException, NameValidationException {
        view.displayAddOrderBanner();
        //in order to create an order you need 12 pieces of info to make a complete order
        //for orderNumber, customer name, state, product type and area you ask the user for that info
        //the rest of the info needed you get that from the product and state text files.
        //First step you need to ask the user for the orderNumber, their name, state, product type and area
        //then create a variable and set it to equal the methods created in view to ask for that info
        //setting it up as a variable allows you to later use the variable types in the method you want to pass it in 
        //for example in this case:
        //you will be passing these variable types (orderNumber, their name, state, product type and area)
        //in the addOrder method parameters in your service layer. 
        //next you need a place to store the user input you just got (also in your addorder method in service layer). 
        //in youre service layer once you store the input you make a new orderDTO with that and actually create it.
        //confirm with user if they want to save the order if yes it will create a new order
        //if no then program will go back to the main menu.
        String username = view.askForCustomerName();
        String state = view.askWhichState();
        String productType = view.askProductType();
        BigDecimal area = view.askArea();
        boolean confirmUserSaveOrderInput = view.saveOrder();
        if (confirmUserSaveOrderInput = true) {
            serviceLayer.addOrder(username, state, productType, area);
            serviceLayer.save(); 
        
    }
    }  

         

    private void editOrder() throws PersistenceException, InvalidDateException, InvalidProductTypeException, InvalidStateException, MinAreaValidationException, NameValidationException {
        view.displayEditOrderBanner(); //displays the edit banner
        try {
        LocalDate orderDate = view.askForOrderDate(); //asks user to put in date
        int orderNumber = view.askForOrderNumber(); //asks user for the order number
        OrderDTO order = serviceLayer.displayOrder(orderDate, orderNumber); //gets the specific order using getOrderbyId method inside of the displayOrder in orderDAO
        String username = view.askForCustomerName();
        String state = view.askWhichState();
        String productType = view.askProductType();
        BigDecimal area = view.askArea();
        order.setCustomerName(username);
        order.setState(state);
        order.setProductType(productType);
        order.setArea(area);
        serviceLayer.editOrder(orderDate, orderNumber, order);
             } catch (InvalidStateException E) {
            view.errorMsgForStateInfo(E.getMessage());
             }
        serviceLayer.save();
             
    }

    private void removeOrder() throws PersistenceException {
        view.displayRemoveOrderBanner();
        LocalDate orderDate = view.askForOrderDate();
        int orderNumber = view.askForOrderNumber();
        serviceLayer.removeOrder(orderDate, orderNumber);
        serviceLayer.save();
        view.orderRemovedBanner();
    }

    private void exitMessage() {
        view.displayExitMessageBanner();
    }

}
