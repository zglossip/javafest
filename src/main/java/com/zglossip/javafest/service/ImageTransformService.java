package com.zglossip.javafest.service;

import com.zglossip.javafest.domain.TriFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;

@Service
public class ImageTransformService {

  private final ImageTraversalService imageTraversalService;

  @Autowired
  public ImageTransformService(final ImageTraversalService imageTraversalService) {
    this.imageTraversalService = imageTraversalService;
  }

  public BufferedImage getTransformedImage(final BufferedImage image, final int newWidth, final int newHeight, final TriFunction<BufferedImage, Integer, Integer, Color> func) {
    final BufferedImage transformedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

    final List<BiConsumer<Integer, Integer>> cellConsumers = List.of((x, y) -> {
      final Color curColor = func.apply(image, x, y);
      transformedImage.setRGB(x, y, curColor.getRGB());
    });

    imageTraversalService.traverseImage(transformedImage.getWidth(), transformedImage.getHeight(), cellConsumers, Collections.emptyList(), false);

    return transformedImage;
  }
}
