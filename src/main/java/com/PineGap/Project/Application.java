package com.PineGap.Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		
		if(!DataSet.isDataSetInitialised()){
            DataSet.initaliseDataSet();
        }

		SpringApplication.run(Application.class, args);
	}

}