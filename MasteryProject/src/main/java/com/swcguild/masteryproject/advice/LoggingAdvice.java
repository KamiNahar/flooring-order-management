/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.advice;

import com.swcguild.masteryproject.DAO.AuditDAO;

/**
 *
 * @author kaminahar
 */
public class LoggingAdvice {
    AuditDAO auditDAO;
    public LoggingAdvice(AuditDAO auditDAO) {
    this.auditDAO = auditDAO;
}
   
     
     public void writeLog() {
        
    }
}
