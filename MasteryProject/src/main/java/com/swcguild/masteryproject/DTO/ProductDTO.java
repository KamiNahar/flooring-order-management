/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.DTO;

import java.math.BigDecimal;

/**
 *
 * @author kaminahar
 */
public class ProductDTO {
    private String productName; 
    private BigDecimal costPerSqft; 
    private BigDecimal laborPerSqft;

    public ProductDTO(String productName, BigDecimal costPerSqft, BigDecimal laborPerSqft) {
        this.productName = productName;
        this.costPerSqft = costPerSqft;
        this.laborPerSqft = laborPerSqft;
    }

    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getCostPerSqft() {
        return costPerSqft;
    }

    public void setCostPerSqft(BigDecimal costPerSqft) {
        this.costPerSqft = costPerSqft;
    }

    public BigDecimal getLaborPerSqft() {
        return laborPerSqft;
    }

    public void setLaborPerSqft(BigDecimal laborPerSqft) {
        this.laborPerSqft = laborPerSqft;
    }
    
}

