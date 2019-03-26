/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.service;

import com.swcguild.masteryproject.DTO.OrderDTO;
import com.swcguild.masteryproject.DTO.ProductDTO;
import com.swcguild.masteryproject.DTO.StateDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kaminahar
 */
public interface ServiceLayer {
    
OrderDTO displayOrder(LocalDate orderDate, int orderNumber) throws PersistenceException;
OrderDTO addOrder(
    String username, 
    String state,
    String productType,
    BigDecimal area) throws 
         InvalidDateException, InvalidProductTypeException,
         InvalidStateException, MinAreaValidationException,
         NameValidationException, PersistenceException; 
OrderDTO editOrder(LocalDate orderDate, int orderNumber, OrderDTO editedOrderDTO) throws InvalidDateException, InvalidProductTypeException,
                           InvalidStateException, MinAreaValidationException,
                           NameValidationException, PersistenceException;
void removeOrder(LocalDate orderDate, int orderNumber) throws PersistenceException; 
List<ProductDTO> getAllProducts()  throws PersistenceException;

    public List<OrderDTO> getAllOrdersByDate(LocalDate orderDate) throws PersistenceException;
void save()throws PersistenceException ;
}
