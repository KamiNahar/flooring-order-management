/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 *
 * @author kaminahar
 */
public class UserIOConsoleImpl implements UserIO {

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public double readDouble(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float readFloat(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Override
    public int readInt(String prompt) {
     Scanner sc = new Scanner(System.in); 
     print(prompt);
     String userInput = sc.nextLine(); 
     userInput = userInput.trim(); //trim literally trims the space(when user hits space bar)in the user's input
     return Integer.parseInt(userInput); //takes the user's string and makes it an integer
    }

    @Override
    public int readInt(String prompt, int min, int max) {
              try { 
        Scanner sc = new Scanner(System.in); 
        print(prompt);
        int result = 0;
        
        String userInput = sc.nextLine();
        result = Integer.parseInt(userInput);
        if (result >= min && result <= max) {
            return result;
        } else {
            print("Must be in range.Please choose a number between " + min + " and " + max + "." ); 
            return readInt(prompt,min,max);
        }
        } catch (Exception e) {
            print("Invalid input, please enter a number from the selection."); 
            return readInt(prompt,min,max);
        } 
          //return readInt(prompt,min,max);
    }

    @Override
    public long readLong(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String readString(String prompt) {
        Scanner sc = new Scanner(System.in);
        print(prompt); 
        return sc.nextLine();
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
      String input = "";
              print(prompt);

        try {
        Scanner sc = new Scanner(System.in); 
//        print(prompt);
//        String userInput = sc.nextLine(); //takes the input from the user
    input = sc.nextLine();
//        BigDecimal getMoney = new BigDecimal (userInput).setScale(2, RoundingMode.HALF_UP);//formats the user input and makes sure the number has two decimal places. 
               BigDecimal getMoney = new BigDecimal (input).setScale(2, RoundingMode.HALF_UP);//formats the user input and makes sure the number has two decimal places. 

return getMoney;  
        } catch (Exception e) {
          print("Invalid input: Please enter a number.");
          return readBigDecimal(prompt);
        }
    }

   
}
