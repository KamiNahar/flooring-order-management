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
public class StateDTO {
   
    private String stateAbrv;
    private BigDecimal taxRate; 

    public StateDTO(String stateAbrv, BigDecimal taxRate) {
     
        this.stateAbrv = stateAbrv;
        this.taxRate = taxRate;
    }

    public String getStateAbrv() {
        return stateAbrv;
    }

    public void setStateAbrv(String stateAbrv) {
        this.stateAbrv = stateAbrv;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal tax) {
        this.taxRate = tax;
    }
    
}
