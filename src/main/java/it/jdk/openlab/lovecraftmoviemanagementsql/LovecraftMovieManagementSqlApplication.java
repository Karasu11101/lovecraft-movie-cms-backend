package it.jdk.openlab.lovecraftmoviemanagementsql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class LovecraftMovieManagementSqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(LovecraftMovieManagementSqlApplication.class, args);
	}

}
