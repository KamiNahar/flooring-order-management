/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.DAO;

import com.swcguild.masteryproject.DTO.OrderDTO;
import com.swcguild.masteryproject.service.InvalidDateException;
import com.swcguild.masteryproject.service.InvalidProductTypeException;
import com.swcguild.masteryproject.service.InvalidStateException;
import com.swcguild.masteryproject.service.MinAreaValidationException;
import com.swcguild.masteryproject.service.NameValidationException;
import com.swcguild.masteryproject.service.PersistenceException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author kaminahar
 */
public class OrderDAOImpl implements OrderDAO {

    //lists a group of characters using the key 
//    private Map<Integer, OrderDTO> orders = new HashMap<>(); 
    private Map<LocalDate, Map<Integer, OrderDTO>> orders = new HashMap<>();;

    //public static final String ORDER_FILE = "Order.txt";
    public static final String DELIMITER = "::";

    private void loadOrders(LocalDate orderDate) throws PersistenceException {
        Map<Integer, OrderDTO> ordersByDate = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String fileDate = formatter.format(orderDate);
        String fileName = "Orders" + fileDate + ".txt";
        Scanner scanner = null;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
        } catch (FileNotFoundException e) {
                    orders.put(orderDate, ordersByDate);
                    return;
                          
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            int orderNumber = Integer.parseInt(currentTokens[0]);
            String customerName = currentTokens[1];
            String state = currentTokens[2];
            BigDecimal orderTaxRate = new BigDecimal(currentTokens[3]);
            String productType = currentTokens[4];
            BigDecimal area = new BigDecimal(currentTokens[5]);
            BigDecimal costPerSquareFoot = new BigDecimal(currentTokens[6]);
            BigDecimal laborCostPerSquareFoot = new BigDecimal(currentTokens[7]);
            BigDecimal materialCost = new BigDecimal(currentTokens[8]);
            BigDecimal laborCost = new BigDecimal(currentTokens[9]);
            BigDecimal totalTax = new BigDecimal(currentTokens[10]);
            BigDecimal subTotal = new BigDecimal(currentTokens[11]);

            OrderDTO currentOrder = new OrderDTO();
            currentOrder.setOrderDate(orderDate);
            currentOrder.setOrderNumber(orderNumber);
            currentOrder.setCustomerName(customerName);
            currentOrder.setState(state);
            currentOrder.setProductType(productType);
            currentOrder.setArea(area);
            currentOrder.setCostPerSquareFoot(costPerSquareFoot);
            currentOrder.setLaborCostPerSquarefoot(laborCostPerSquareFoot);
            currentOrder.setOrderTaxRate(orderTaxRate);
            currentOrder.setMaterialCost(materialCost);
            currentOrder.setLaborCost(laborCost);
            currentOrder.setTotalTax(totalTax);
            currentOrder.setSubTotal(subTotal);

            ordersByDate.put(orderNumber, currentOrder);
        }
        orders.put(orderDate, ordersByDate);
        scanner.close();
    }


    private void writeOrders() throws PersistenceException {
        
        //date formatter makes sure to put the date in this specific format
        //then you create a file with that name to place all the orders in 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        for (LocalDate orderDate : this.orders.keySet()) {
            String fileDate = formatter.format(orderDate);
            String fileName = "Orders" + fileDate + ".txt";
            //printWriter writes the orders and its info to that file. 
            PrintWriter out;
            try {
                out = new PrintWriter(new FileWriter(fileName));
            } catch (IOException e) {
                throw new PersistenceException("Could not save order data.", e);
            }
            //declare a list of orders
            //for each date that there is in the hashmap,
            //it is going to write the orders to the file according to the hashmap
            List<OrderDTO> orderList = new ArrayList<OrderDTO>(this.orders.get(orderDate).values());
            for (OrderDTO currentOrderDTO : orderList) {
                out.println(currentOrderDTO.getOrderNumber() + DELIMITER
                        + currentOrderDTO.getCustomerName() + DELIMITER
                        + currentOrderDTO.getState() + DELIMITER
                        + currentOrderDTO.getOrderTaxRate() + DELIMITER
                        + currentOrderDTO.getProductType() + DELIMITER
                        + currentOrderDTO.getArea() + DELIMITER
                        + currentOrderDTO.getCostPerSquareFoot() + DELIMITER
                        + currentOrderDTO.getLaborCostPerSquarefoot() + DELIMITER
                        + currentOrderDTO.getMaterialCost() + DELIMITER
                        + currentOrderDTO.getLaborCost() + DELIMITER
                        + currentOrderDTO.getTotalTax() + DELIMITER
                        + currentOrderDTO.getSubTotal());

                //.put writes it to the hashmap
                
                // orders.put(currentOrderDTO.getOrderNumber(), currentOrderDTO);
                out.flush();
            }
            out.close();
        }

    }

    public LocalDate getDate() {
        //returns todays date in the format yyyymmdd.
        return LocalDate.now();
    }


    public String createFile(LocalDate date) {
        String fileDate = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        String fileName = "Orders" + fileDate + ".txt";
        String workingDirectory = System.getProperty("user.dir");
        String absoluteFilePath = "";
        absoluteFilePath = workingDirectory + File.separator + fileName;
        File file = new File(absoluteFilePath);
        return fileName;
    }

    @Override
    public OrderDTO addOrder(OrderDTO orderDTO) throws PersistenceException {
        LocalDate date = getDate();
        int nextOrderNumber = 0;
        
        //checks to see if the the big map has the date as the key if not it creates the file with 
        //that date
      if (orders.containsKey(orderDTO.getOrderDate()) == false) {
            this.loadOrders(date);
      }
        Map<Integer, OrderDTO> ordersByDate = orders.get(orderDTO.getOrderDate());
        for (int id : ordersByDate.keySet()) {
            if (id > nextOrderNumber) {
                nextOrderNumber = id;
            }
        }

        nextOrderNumber = nextOrderNumber + 1;
        orderDTO.setOrderNumber(nextOrderNumber);
        ordersByDate.put(nextOrderNumber, orderDTO);
        return orderDTO;
    
    }

    @Override
    public List<OrderDTO> getAllOrdersByDate(LocalDate orderDate) throws PersistenceException {
        //if the orders hashmap contains key orderDate then load the ordersbyDate
        if (orders.containsKey(orderDate)) {
            loadOrders(orderDate);
        }
        //Get the child hasmap(ordersByDate) from the parent hashmap and get the order date.
        Map<Integer, OrderDTO> ordersByDate = orders.get(orderDate);
        //return a list of all the orders for that date. the values are each order
        return new ArrayList<>(orders.get(orderDate).values());
    }

  
    @Override
    public OrderDTO getOrderById(LocalDate orderDate, int orderNumber) throws PersistenceException {
        if (orders.containsKey(orderDate) == false) {
            loadOrders(orderDate);
        }
        Map<Integer, OrderDTO> ordersByDate = orders.get(orderDate);
        return ordersByDate.get(orderNumber);
    }


    
    @Override
    public OrderDTO editOrder(LocalDate orderDate, int orderNumber, OrderDTO editedOrderDTO) throws PersistenceException {
        if (orders.containsKey(orderDate) == false) {
            loadOrders(orderDate);
        }
        Map<Integer, OrderDTO> ordersByDate = orders.get(orderDate);
        ordersByDate.put(orderNumber, editedOrderDTO);
        return editedOrderDTO;
    }

    
    @Override
    public void saveOrders(OrderDTO orderDTO) throws PersistenceException {
        writeOrders();
    }

    @Override
    public void removeOrder(LocalDate orderDate, int orderNumber) throws PersistenceException {
        if (orders.containsKey(orderDate)) {
            loadOrders(orderDate);
        }
        Map<Integer, OrderDTO> ordersByDate = orders.get(orderDate);
        ordersByDate.remove(orderNumber);

    }

}
