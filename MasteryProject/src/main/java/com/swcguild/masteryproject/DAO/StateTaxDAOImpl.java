/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.DAO;

import com.swcguild.masteryproject.DTO.ProductDTO;
import com.swcguild.masteryproject.DTO.StateDTO;
import com.swcguild.masteryproject.service.PersistenceException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author kaminahar
 */
public class StateTaxDAOImpl implements StateTaxDAO {
private Map<String, StateDTO> taxes = new HashMap<>(); 

    public static final String STATETAX_FILE = "StateTax.txt";
    public static final String DELIMITER = "::";
    
    private void loadStateTaxes() throws PersistenceException{
        Scanner scanner;
        
        try{
            scanner = new Scanner(new BufferedReader(new FileReader(STATETAX_FILE)));
        }catch(FileNotFoundException e){
            throw new PersistenceException("Error: Could not load product data into memory.", e);
        }
        
        String currentLine;
        String[] currentTokens;
      
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            String stateAbrv = currentTokens[0];
            BigDecimal taxRate = new BigDecimal(currentTokens[1]);
            
            
        StateDTO currentStateDTO = new StateDTO(stateAbrv, taxRate);
        taxes.put(currentStateDTO.getStateAbrv(), currentStateDTO);
        }

        scanner.close();
    }
 

    @Override
    public List<StateDTO> getAllStates() throws PersistenceException {
        loadStateTaxes();
        return new ArrayList<StateDTO>(taxes.values());
    }

//    @Override
//    public StateDTO getStateByAbrv(String stateAbrv) throws PersistenceException {
////to ignore upper or lower case  of user string input :
////the list gets all the states
////for every stateDTO object it will compare what is passed in (which is the stateAbrv aka statename)
////and if the state name is equal to what is passed in(ignoring the casing) it should return 
////stateDTO object.
//List<StateDTO> StateAbrv = this.getAllStates();
//for (StateDTO currentState : StateAbrv)  {
//    if (currentState.getStateAbrv().equalsIgnoreCase(stateAbrv)){
//        return currentState; 
//            }
//}
//    
//        
//    }


    
 @Override
    public StateDTO getStateByAbrv(String stateAbrv) throws PersistenceException {
    return taxes.get(stateAbrv); //gets the taxes by state name from the taxes hashmap
    }



    }
