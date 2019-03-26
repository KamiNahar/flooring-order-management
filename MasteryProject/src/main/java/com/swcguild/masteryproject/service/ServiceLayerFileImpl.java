/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.service;

import com.swcguild.masteryproject.DAO.OrderDAO;
import com.swcguild.masteryproject.DAO.ProductDAO;
import com.swcguild.masteryproject.DAO.StateTaxDAO;
import com.swcguild.masteryproject.DTO.OrderDTO;
import com.swcguild.masteryproject.DTO.ProductDTO;
import com.swcguild.masteryproject.DTO.StateDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kaminahar
 */
public class ServiceLayerFileImpl implements ServiceLayer {

    private OrderDAO orderDAO;
    private ProductDAO productDAO;
    private StateTaxDAO stateTaxDAO;

    public ServiceLayerFileImpl(OrderDAO orderDAO, ProductDAO productDAO, StateTaxDAO stateTaxDAO) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
        this.stateTaxDAO = stateTaxDAO;

    }

    private Map<String, OrderDTO> orders = new HashMap<>();
//TODO: Add localDate to displayOrder Paramaters
    @Override
    public OrderDTO displayOrder(LocalDate orderDate, int orderNumber) throws PersistenceException {
        OrderDTO orderDTO = orderDAO.getOrderById( orderDate, orderNumber);
                return orderDTO;

    }

    @Override
    public OrderDTO addOrder(String username, String state, String productType,
            BigDecimal area) throws InvalidDateException, InvalidProductTypeException, InvalidStateException, MinAreaValidationException, NameValidationException, PersistenceException {

        //gets the product and state info from DAO 
        //DAO gives you the array list of states and products.
        List<ProductDTO> productList = productDAO.getAllProducts();
        List<StateDTO> taxList = stateTaxDAO.getAllStates();

        //grabs the product name and cost&laborCostperSqFt from the dao and dto.
        ProductDTO ProductType = productDAO.getProductByName(productType);
        BigDecimal costPerSquareFoot = ProductType.getCostPerSqft();
        BigDecimal laborCostPerSquareFoot = ProductType.getLaborPerSqft();

        //grabs the state name and tax rate from the state dao and dto. 
        StateDTO stateName = stateTaxDAO.getStateByAbrv(state);
        if (stateName == null) {
            throw new InvalidStateException("Invalid data: Enter correct state abbreviation.");
        }
        BigDecimal stateTaxRate = stateName.getTaxRate();

        //calculations for the rest of the info needed to complete an order   
        BigDecimal materialCost = area.multiply(costPerSquareFoot);
        BigDecimal laborCost = area.multiply(laborCostPerSquareFoot);
        BigDecimal materialCostPlusLaborCost = materialCost.add(laborCost);
        BigDecimal totalTax = materialCostPlusLaborCost.multiply(stateTaxRate);
        BigDecimal subTotal = materialCostPlusLaborCost.add(totalTax);

        //once you have all the information you need to create an order,
        //you make a new orderDTO and pass in those types and then return addOrder method 
        //from orderDAO to actually create an order.
        //this object name has to be different from the orderDTO object. 
        OrderDTO newOrder = new OrderDTO();
        newOrder.setOrderDate(LocalDate.now());
        newOrder.setCustomerName(username);
        newOrder.setState(state);
        newOrder.setProductType(productType);
        newOrder.setArea(area);
        newOrder.setCostPerSquareFoot(costPerSquareFoot);
        newOrder.setLaborCostPerSquarefoot(laborCostPerSquareFoot);
        newOrder.setOrderTaxRate(stateTaxRate);
        newOrder.setMaterialCost(materialCost);
        newOrder.setLaborCost(laborCost);
        newOrder.setTotalTax(totalTax);
        newOrder.setSubTotal(subTotal);
        newOrder.setOrderDate(LocalDate.now());
        return orderDAO.addOrder(newOrder);

    }

  

    @Override
    public void removeOrder(LocalDate orderDate, int orderNumber) throws PersistenceException {
     orderDAO.removeOrder(orderDate, orderNumber);
    }

   
    
    @Override
    public List<ProductDTO> getAllProducts() throws PersistenceException {
        return productDAO.getAllProducts();
    }

    @Override
    public List<OrderDTO> getAllOrdersByDate(LocalDate orderDate) throws PersistenceException {

        return orderDAO.getAllOrdersByDate(orderDate);
    }

    @Override
    public void save() throws PersistenceException  {
   OrderDTO savedOrder = new OrderDTO();
     orderDAO.saveOrders(savedOrder);
    }

    @Override
    public OrderDTO editOrder(LocalDate orderDate, int orderNumber, OrderDTO editedOrderDTO) throws InvalidDateException, InvalidProductTypeException, InvalidStateException, MinAreaValidationException, NameValidationException, PersistenceException {
     return orderDAO.editOrder(orderDate, orderNumber, editedOrderDTO);
           
    }
//    
//    private void validateState(String stateInfo) throws InvalidStateException {
//    //for a string you use the dot operator to operate on strings.    
//        
//   if (stateInfo.equalsIgnoreCase(stateInfo) {
//       io.print("Please enter the state in all caps.")
//   }    
//    }

   private  void validatesStateInfo (String[] args) throws InvalidStateException { 
	
        String OH = "OH"; 
	String PA = "PA";
	String MI = "MI";
	String IN = "IN";
        String oh = "oh";
        String pa = "pa";
        String mi = "mi";
        String in = "in";

	// if we ignore the cases both the strings are equal. 
	boolean result1 = OH.equalsIgnoreCase(oh); 
	boolean result2 = PA.equalsIgnoreCase(pa);
	boolean result3 = MI.equalsIgnoreCase(mi);
	boolean result4 = IN.equalsIgnoreCase(in);
 	System.out.println("OH"); 
 	System.out.println("PA");
 	System.out.println("MI");
 	System.out.println("IN");
        
        //exclamation is used for false, if these results are false, throw
        //this exception. 
        if (!result1 || !result2 || !result3 || !result4) {
           throw new InvalidStateException (
           " Please select a state only from the options.");
           
        } 
 
   }
}

