package com.aquintero.soaint.prueba;

import com.aquintero.soaint.prueba.controller.DemoController;
import com.aquintero.soaint.prueba.entity.User;
import com.aquintero.soaint.prueba.repository.LogsRepositry;
import com.aquintero.soaint.prueba.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

@SpringBootApplication
public class PruebaApplication implements CommandLineRunner {

	private static final Logger logger = Logger.getLogger(PruebaApplication.class.getName());

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LogsRepositry logsRepositry;

	public static void main(String[] args) throws IOException
	{
		SpringApplication.run(PruebaApplication.class, args);

	}
	
	@Override
    public void run(String... args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter your username: ");
    	String username = reader.readLine();
    	System.out.println("Enter your password: ");
    	String password = reader.readLine();
    	System.out.println("***************************************************");	
    	User user = userRepository.findUsername(username, password);
    	if(user != null) {
			DemoController demoController = new DemoController(logsRepositry);
			demoController.executeProcess(user);    		
    	} else {
    		System.out.println("Failed Authentication!!!");
    		System.exit(0);
    	}

	}

}
