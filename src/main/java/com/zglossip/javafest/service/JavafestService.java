package com.zglossip.javafest.service;

import com.zglossip.javafest.domain.enums.EditorType;
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

  public void exec(final EditorType editorType, final String filepath, final Integer width, final Integer height, final boolean invert, final boolean flames) {
    switch (editorType) {
      case ASCII -> flameService.printFlame(filepath, width, height, invert, flames);
      case COPY -> imageEditorService.copyImage(filepath, width, height, invert);
    }
  }
}
