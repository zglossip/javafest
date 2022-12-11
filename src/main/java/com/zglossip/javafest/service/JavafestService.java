package com.zglossip.javafest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JavafestService {

  private final FlamesService flamesService;
  private final PrintService printService;

  @Autowired
  public JavafestService(final FlamesService flamesService, final PrintService printService) {
    this.flamesService = flamesService;
    this.printService = printService;
  }

  public void printMadelineKahnAsMrsWhiteInClueSayingFlames(final Integer width, final Integer height) {
    printService.printText(flamesService.getMkAscii(width, height));
    printService.printText(flamesService.getFooter(width));
  }

}
