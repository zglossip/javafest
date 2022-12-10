package com.zglossip.javafest;

import com.zglossip.javafest.service.FlamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavafestApplication implements CommandLineRunner {

	private final FlamesService flamesService;

	@Autowired
	public JavafestApplication(FlamesService flamesService) {
		this.flamesService = flamesService;
	}

	public static void main(String[] args) {
		SpringApplication.run(JavafestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		flamesService.printMadelineKahnAsMrsWhiteInClueSayingFlames();

	}

}
