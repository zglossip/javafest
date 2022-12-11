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

  public void exec(final String filepath, final Integer width, final Integer height, final boolean invert) {
    if (filepath == null) {
      printMadelineKahnAsMrsWhiteInClueSayingFlames(width, height, invert);
      return;
    }

    printCustomImage(filepath, width, height, invert);
  }

  private void printMadelineKahnAsMrsWhiteInClueSayingFlames(final Integer width, final Integer height, final boolean inverted) {
    if (inverted) {
      printMadelineKahnAsMrsWhiteInClueSayingFlamesInverted(width, height);
    } else {
      printMadelineKahnAsMrsWhiteInClueSayingFlames(width, height);
    }
  }

  private void printMadelineKahnAsMrsWhiteInClueSayingFlames(final Integer width, final Integer height) {
    printImage(flamesService.getMkAscii(width, height));
  }

  private void printMadelineKahnAsMrsWhiteInClueSayingFlamesInverted(final Integer width, final Integer height) {
    printImage(flamesService.getInvertedMkAscii(width, height));
  }

  private void printCustomImage(final String filepath, final Integer width, final Integer height, final boolean inverted) {
    if (inverted) {
      printCustomImageInverted(filepath, width, height);
    } else {
      printCustomImage(filepath, width, height);
    }
  }

  private void printCustomImage(final String filepath, final Integer width, final Integer height) {
    printImage(flamesService.getCustomAscii(filepath, width, height));
  }

  private void printCustomImageInverted(final String filepath, final Integer width, final Integer height) {
    printImage(flamesService.getCustomAsciiInverted(filepath, width, height));
  }

  private void printImage(final AsciiImage image) {
    printService.printText(image.getImage());
    printService.printText(flamesService.getFooter(image.getWidth()));
  }

}
