package com.zglossip.javafest.service.flame;

import com.zglossip.javafest.domain.AsciiImage;
import com.zglossip.javafest.domain.TriFunction;
import com.zglossip.javafest.service.BaseEditorService;
import com.zglossip.javafest.service.ImageUtilService;
import com.zglossip.javafest.service.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlameService extends BaseEditorService {

  private final FlameVisualsService flameVisualsService;
  private final PrintService printService;
  private final ImageUtilService imageUtilService;

  @Autowired
  public FlameService(final FlameVisualsService flameVisualsService,
                      final PrintService printService,
                      final ImageUtilService imageUtilService) {
    this.flameVisualsService = flameVisualsService;
    this.printService = printService;
    this.imageUtilService = imageUtilService;
  }

  @Override
  public void printImage(final String filepath, final Integer width, final Integer height, final boolean invert, final boolean footer) {
    final List<TriFunction<BufferedImage, Integer, Integer, Color>> functionList = new ArrayList<>();

    if (invert) {
      functionList.add(imageUtilService.getInvertColorFunction());
    }

    final AsciiImage asciiImage = flameVisualsService.getAsciiString(filepath, width, height, functionList);

    printService.printText(asciiImage.getImage());

    if (footer) {
      printService.printText(flameVisualsService.getFooter(asciiImage.getWidth()));
    }
  }

}
