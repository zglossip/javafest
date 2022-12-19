package com.zglossip.javafest.base;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("unitTesting")
@SpringBootTest
public class TestBase {

  @BeforeAll
  public static void setup() {
    System.out.println("##########Test base set up##########");
  }

}
