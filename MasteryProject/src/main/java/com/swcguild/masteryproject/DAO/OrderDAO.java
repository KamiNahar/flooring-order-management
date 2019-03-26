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
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author kaminahar
 */

//CRUD = Create.Read.Update.Delete
public interface OrderDAO {

//create order
  
    
OrderDTO addOrder(OrderDTO orderDTO) 
      throws 
         InvalidDateException, InvalidProductTypeException,
         InvalidStateException, MinAreaValidationException,
         NameValidationException, PersistenceException;
//read order
List<OrderDTO> getAllOrdersByDate(LocalDate orderDate) throws PersistenceException;
OrderDTO getOrderById(LocalDate orderDate, int orderNumber) throws PersistenceException;
//update order
OrderDTO editOrder(LocalDate orderDate, int orderNumber, OrderDTO editedOrderDTO) throws PersistenceException;
//        throws 
//         InvalidDateException, InvalidProductTypeException,
//         InvalidStateException, MinAreaValidationException,
//         NameValidationException, PersistenceException;
void saveOrders(OrderDTO orderDTO) throws PersistenceException;
//delete order
void removeOrder(LocalDate orderDate, int orderNumber)throws PersistenceException;

}
