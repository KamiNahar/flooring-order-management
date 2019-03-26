/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject;



import com.swcguild.masteryproject.controller.Controller;
import com.swcguild.masteryproject.service.InvalidDateException;
import com.swcguild.masteryproject.service.InvalidProductTypeException;
import com.swcguild.masteryproject.service.InvalidStateException;
import com.swcguild.masteryproject.service.MinAreaValidationException;
import com.swcguild.masteryproject.service.NameValidationException;
import com.swcguild.masteryproject.service.PersistenceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author kaminahar
 */
public class App {
    
public static void main(String[] args) throws PersistenceException, InvalidDateException, InvalidProductTypeException, InvalidStateException, MinAreaValidationException, NameValidationException { 
    
        ApplicationContext ctx = 
        new ClassPathXmlApplicationContext("ApplicationContext.xml");
        Controller controller = 
        ctx.getBean("controller", Controller.class);
        controller.run(); 
    }

}
