package com.zglossip.javafest.service;

import com.zglossip.javafest.domain.AsciiImage;
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
    final AsciiImage image = flamesService.getMkAscii(width, height);
    printService.printText(image.getImage());
    printService.printText(flamesService.getFooter(image.getWidth()));
  }

}
