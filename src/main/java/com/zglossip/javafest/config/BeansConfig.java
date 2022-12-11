package com.zglossip.javafest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

  @Bean("mkamwicsfImagePath")
  public String getMkamwicsfImagePath() {
    return "madeline_kahn_as_mrs_white_in_clue_saying_flames.png";
  }

  @Bean("defaultFileSize")
  public int getDefaultFileSize() {
    return 200;
  }
}
