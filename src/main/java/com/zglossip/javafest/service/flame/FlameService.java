package com.zglossip.javafest.service.flame;

import com.zglossip.javafest.domain.AsciiImage;
import com.zglossip.javafest.service.BaseEditorService;
import com.zglossip.javafest.service.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlameService extends BaseEditorService {

  private final FlameVisualsService flameVisualsService;
  private final PrintService printService;

  @Autowired
  public FlameService(final FlameVisualsService flameVisualsService,
                      final PrintService printService) {
    this.flameVisualsService = flameVisualsService;
    this.printService = printService;
  }

  @Override
  public void printImage(final String filepath, final Integer width, final Integer height, final boolean invert, final boolean footer, final boolean twoColor) {


    final AsciiImage asciiImage = flameVisualsService.getAsciiString(filepath, width, height, invert, twoColor);

    printService.printText(asciiImage.getImage());

    if (footer) {
      printService.printText(flameVisualsService.getFooter(asciiImage.getWidth()));
    }
  }

}
