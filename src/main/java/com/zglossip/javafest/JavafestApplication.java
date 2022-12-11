package com.zglossip.javafest;

import com.zglossip.javafest.exceptions.NonNumberArgumentException;
import com.zglossip.javafest.exceptions.TooManyArgumentsException;
import com.zglossip.javafest.service.FlamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavafestApplication implements CommandLineRunner {

  private final FlamesService flamesService;

  @Autowired
  public JavafestApplication(final FlamesService flamesService) {
    this.flamesService = flamesService;
  }

  public static void main(final String[] args) {
    SpringApplication.run(JavafestApplication.class, args);
  }

  @Override
  public void run(final String... args) throws Exception {

    Integer size = null;

    if (args.length > 1) {
      throw new TooManyArgumentsException();
    } else if (args.length == 1) {
      try {
        size = Integer.parseInt(args[0]);
      } catch (final NumberFormatException e) {
        throw new NonNumberArgumentException();
      }
    }

    flamesService.printMadelineKahnAsMrsWhiteInClueSayingFlames(size, size);

  }

}
