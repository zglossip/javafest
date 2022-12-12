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

  public void printFlame(final String filepath, final Integer width, final Integer height, final boolean invert) {
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
    printImage(flameVisualsService.getMkAscii(width, height));
  }

  private void printMadelineKahnAsMrsWhiteInClueSayingFlamesInverted(final Integer width, final Integer height) {
    printImage(flameVisualsService.getInvertedMkAscii(width, height));
  }

  private void printCustomImage(final String filepath, final Integer width, final Integer height, final boolean inverted) {
    if (inverted) {
      printCustomImageInverted(filepath, width, height);
    } else {
      printCustomImage(filepath, width, height);
    }
  }

  private void printCustomImage(final String filepath, final Integer width, final Integer height) {
    printImage(flameVisualsService.getCustomAscii(filepath, width, height));
  }

  private void printCustomImageInverted(final String filepath, final Integer width, final Integer height) {
    printImage(flameVisualsService.getCustomAsciiInverted(filepath, width, height));
  }

  private void printImage(final AsciiImage image) {
    printService.printText(image.getImage());
    printService.printText(flameVisualsService.getFooter(image.getWidth()));
  }

}
