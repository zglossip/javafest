package com.zglossip.javafest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!unitTesting")
@Configuration
public class BeansConfig {

  @Bean("defaultImageFile")
  public String getDefaultImageFile() {
    return "madeline_kahn_as_mrs_white_in_clue_saying_flames.png";
  }
}
