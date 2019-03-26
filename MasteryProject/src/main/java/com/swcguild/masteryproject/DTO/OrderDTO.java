 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author kaminahar
 */

//OrderNumber,
//CustomerName,
//State,
//TaxRate,
//ProductType,
//Area,
//CostPerSquareFoot,
//LaborCostPerSquareFoot,
//MaterialCost,
//LaborCost,
//Tax,
//Total



public class OrderDTO {
    
//you will be asking user to enter this info
    private LocalDate orderDate;
    private int orderNumber; 
    private String customerName;
    private String state;  
    private String productType;
    private BigDecimal area;
    
//this info you will get from files
    private BigDecimal orderTaxRate; 
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquarefoot;

//this you will calculate using methods in your service layer.
    private BigDecimal materialCost; 
    private BigDecimal laborCost;
    private BigDecimal totalTax; 
    private BigDecimal subTotal;

    
//    public OrderDTO (LocalDate orderDate,int orderNumber, String customerName, String state, 
//    BigDecimal orderTaxRate, String productType, BigDecimal area,
//    BigDecimal costPerSquareFoot, BigDecimal laborCostPerSquareFoot,
//    BigDecimal materialCost, BigDecimal laborCost, BigDecimal totalTax, BigDecimal subTotal) {
//        this.orderDate = orderDate;
//        this.orderNumber = orderNumber;
//        this.customerName = customerName;
//        this.state = state;
//        this.orderTaxRate = orderTaxRate;
//        this.productType = productType;
//        this.area = area;
//        this.costPerSquareFoot = costPerSquareFoot;
//        this.laborCostPerSquarefoot = laborCostPerSquareFoot; 
//        this.materialCost = materialCost; 
//        this.laborCost = laborCost;
//        this.totalTax = totalTax;
//        this.subTotal = subTotal;
//    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }



    public String getCustomerName() {
        return customerName;
    }
  
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getOrderTaxRate() {
        return orderTaxRate;
    }

    public void setOrderTaxRate(BigDecimal orderTaxRate) {
        this.orderTaxRate = orderTaxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquarefoot() {
        return laborCostPerSquarefoot;
    }

    public void setLaborCostPerSquarefoot(BigDecimal laborCostPerSqft) {
        this.laborCostPerSquarefoot = laborCostPerSqft;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }    
  
}
