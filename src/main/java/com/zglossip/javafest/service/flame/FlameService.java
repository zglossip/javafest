package com.zglossip.javafest.service.flame;

import com.zglossip.javafest.domain.AsciiImage;
import com.zglossip.javafest.service.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlameService {

  private final FlameVisualsService flameVisualsService;
  private final PrintService printService;

  @Autowired
  public FlameService(final FlameVisualsService flameVisualsService, final PrintService printService) {
    this.flameVisualsService = flameVisualsService;
    this.printService = printService;
  }

  public void printFlame(final String filepath, final Integer width, final Integer height, final boolean invert, final boolean footer) {
    if (filepath == null) {
      printMadelineKahnAsMrsWhiteInClueSayingFlames(width, height, invert, footer);
      return;
    }

    printCustomImage(filepath, width, height, invert, footer);
  }

  private void printMadelineKahnAsMrsWhiteInClueSayingFlames(final Integer width, final Integer height, final boolean inverted, final boolean footer) {
    if (inverted) {
      printMadelineKahnAsMrsWhiteInClueSayingFlamesInverted(width, height, footer);
    } else {
      printMadelineKahnAsMrsWhiteInClueSayingFlames(width, height, footer);
    }
  }

  private void printMadelineKahnAsMrsWhiteInClueSayingFlames(final Integer width, final Integer height, final boolean footer) {
    printImage(flameVisualsService.getMkAscii(width, height), footer);
  }

  private void printMadelineKahnAsMrsWhiteInClueSayingFlamesInverted(final Integer width, final Integer height, final boolean footer) {
    printImage(flameVisualsService.getInvertedMkAscii(width, height), footer);
  }

  private void printCustomImage(final String filepath, final Integer width, final Integer height, final boolean inverted, final boolean footer) {
    if (inverted) {
      printCustomImageInverted(filepath, width, height, footer);
    } else {
      printCustomImage(filepath, width, height, footer);
    }
  }

  private void printCustomImage(final String filepath, final Integer width, final Integer height, final boolean footer) {
    printImage(flameVisualsService.getCustomAscii(filepath, width, height), footer);
  }

  private void printCustomImageInverted(final String filepath, final Integer width, final Integer height, final boolean footer) {
    printImage(flameVisualsService.getCustomAsciiInverted(filepath, width, height), footer);
  }

  private void printImage(final AsciiImage image, final boolean footer) {
    printService.printText(image.getImage());
    if (footer) {
      printService.printText(flameVisualsService.getFooter(image.getWidth()));
    }
  }

}
