package com.zglossip.javafest.service;

import com.zglossip.javafest.service.flame.FlameService;
import com.zglossip.javafest.service.image.ImageEditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JavafestService {

  private final FlameService flameService;
  private final ImageEditorService imageEditorService;

  @Autowired
  public JavafestService(final FlameService flameService, final ImageEditorService imageEditorService) {
    this.flameService = flameService;
    this.imageEditorService = imageEditorService;
  }

  public void exec(final String filepath, final Integer width, final Integer height, final boolean invert, final boolean copy) {
    flameService.printFlame(filepath, width, height, invert);
    if (copy) {
      imageEditorService.copyImage(filepath, width, height);
    }
  }
}
