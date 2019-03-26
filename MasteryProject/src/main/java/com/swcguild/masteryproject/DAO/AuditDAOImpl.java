/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.DAO;

import com.swcguild.masteryproject.service.PersistenceException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author kaminahar
 */
public class AuditDAOImpl implements AuditDAO {
private static final String FILENAME = "orderAudit.txt";
    @Override
    public void writeAuditEntry(String entry) throws PersistenceException {
        PrintWriter out; 
        try {
        out = new PrintWriter(new FileWriter(FILENAME, true));
        } catch (IOException ex) {
            throw new PersistenceException("Could not write to log.", ex);
        } 
        
        LocalDateTime timestamp = LocalDateTime.now();
        //converts the time stamp into a string so that it can be 
        //written into the log, otherwise it wouldn't write to log if you don't convert to string.
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    
    
    }
}
