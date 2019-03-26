/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.DAO;

import com.swcguild.masteryproject.DTO.StateDTO;
import com.swcguild.masteryproject.service.PersistenceException;
import java.util.List;

/**
 *
 * @author kaminahar
 */
public interface StateTaxDAO {

List<StateDTO> getAllStates()throws PersistenceException;
StateDTO getStateByAbrv(String stateAbrv)throws PersistenceException; 

}
