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

    Integer width = null;
    Integer height = null;

    switch (args.length) {
      case 2:
        try {
          height = Integer.parseInt(args[1]);
        } catch (final NumberFormatException e) {
          throw new NonNumberArgumentException();
        }
      case 1:
        try {
          width = Integer.parseInt(args[0]);
        } catch (final NumberFormatException e) {
          throw new NonNumberArgumentException();
        }
      case 0:
        break;
      default:
        throw new TooManyArgumentsException();
    }

    flamesService.printMadelineKahnAsMrsWhiteInClueSayingFlames(width, height);

  }

}
