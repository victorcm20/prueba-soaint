package com.aquintero.soaint.prueba.controller;

import com.aquintero.soaint.prueba.entity.User;
import com.aquintero.soaint.prueba.repository.LogsRepositry;
import com.aquintero.soaint.prueba.service.DemoServiceImpl;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DemoController
{

    private static final Logger logger = Logger.getLogger(DemoController.class.getName());

    private DemoServiceImpl demoServiceImpl;
    
    private LogsRepositry logsRepositry;
    
    public DemoController(LogsRepositry logsRepositry) {
		// TODO Auto-generated constructor stub
    	this.logsRepositry = logsRepositry;
	}

    public void executeProcess(User user)
    {
        try {
        	if(user.getRol() != null) {
	            demoServiceImpl = new DemoServiceImpl(user.getRol().getLogToFile(), user.getRol().getLogToConsole(), 
	            	user.getRol().getLogMessage(), user.getRol().getLogWarning(), user.getRol().getLogError(),
	            	user.getRol().getLogToDatabase());
	            demoServiceImpl.logMessage("Este mensaje sera visualizado!!!", Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, user, logsRepositry);        		
        	} else {
        		logger.log(Level.SEVERE, "El usuario ingresado no tiene permisos");
        	}

        }catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }

    }


}
