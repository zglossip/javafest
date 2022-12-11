package com.zglossip.javafest;

import com.zglossip.javafest.domain.ArgumentType;
import com.zglossip.javafest.service.JavafestService;
import com.zglossip.javafest.util.ArgumentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class JavafestApplication implements CommandLineRunner {

  private final JavafestService javafestService;

  @Autowired
  public JavafestApplication(final JavafestService javafestService) {
    this.javafestService = javafestService;
  }

  public static void main(final String[] args) {
    SpringApplication.run(JavafestApplication.class, args);
  }

  @Override
  public void run(final String... args) throws Exception {

    final Map<ArgumentType, Integer> argumentMap = ArgumentUtil.getArguments(args);

    final Integer width = argumentMap.get(ArgumentType.WIDTH);
    final Integer height = argumentMap.get(ArgumentType.HEIGHT);

    javafestService.printMadelineKahnAsMrsWhiteInClueSayingFlames(width, height);

  }

}
