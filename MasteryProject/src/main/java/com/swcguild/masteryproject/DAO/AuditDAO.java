/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.DAO;

import com.swcguild.masteryproject.service.PersistenceException;

/**
 *
 * @author kaminahar
 */
public interface AuditDAO {
  
void writeAuditEntry(String entry) throws PersistenceException; 
    
}
