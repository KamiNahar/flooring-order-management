/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.service;

/**
 *
 * @author kaminahar
 */
public class MinAreaValidationException extends Exception{

    public MinAreaValidationException(String message) {
        super(message);
    }

    public MinAreaValidationException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
