package com.zglossip.javafest;

import com.zglossip.javafest.domain.enums.ArgumentType;
import com.zglossip.javafest.domain.enums.EditorType;
import com.zglossip.javafest.service.ArgumentUtilService;
import com.zglossip.javafest.service.JavafestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import java.util.Map;

@SpringBootApplication
@Profile("!unitTesting")
public class JavafestApplication implements CommandLineRunner {

  private final JavafestService javafestService;
  private final ArgumentUtilService argumentUtilService;

  @Autowired
  public JavafestApplication(final JavafestService javafestService, final ArgumentUtilService argumentUtilService) {
    this.javafestService = javafestService;
    this.argumentUtilService = argumentUtilService;
  }

  public static void main(final String[] args) {
    SpringApplication.run(JavafestApplication.class, args);
  }

  @Override
  public void run(final String... args) throws Exception {

    final Map<ArgumentType, Object> argumentMap = argumentUtilService.getArguments(args);

    final EditorType editorType = (EditorType) argumentMap.get(ArgumentType.EDITOR_TYPE);

    final Integer width = (Integer) argumentMap.get(ArgumentType.WIDTH);
    final Integer height = (Integer) argumentMap.get(ArgumentType.HEIGHT);
    final String filepath = (String) argumentMap.get(ArgumentType.FILE);
    final boolean inverted = argumentMap.get(ArgumentType.INVERTED) != null && (boolean) argumentMap.get(ArgumentType.INVERTED);

    javafestService.exec(editorType, filepath, width, height, inverted, false);

  }

}
