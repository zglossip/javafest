package com.zglossip.javafest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JavafestService {

  private final FlameService flameService;

  @Autowired
  public JavafestService(final FlameService flameService) {
    this.flameService = flameService;
  }

  public void exec(final String filepath, final Integer width, final Integer height, final boolean invert) {
    flameService.printFlame(filepath, width, height, invert);
  }
}
