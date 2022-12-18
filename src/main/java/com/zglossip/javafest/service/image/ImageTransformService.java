package com.zglossip.javafest.service.image;

import com.zglossip.javafest.domain.TriFunction;
import com.zglossip.javafest.service.ImageTraversalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@Service
public class ImageTransformService {

  private final ImageTraversalService imageTraversalService;

  @Autowired
  public ImageTransformService(final ImageTraversalService imageTraversalService) {
    this.imageTraversalService = imageTraversalService;
  }

  //TODO Write test
  public BufferedImage getScaledImage(final BufferedImage image, final Integer width, final Integer height) {
    final BufferedImage scaled = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    final Graphics2D graphics2D = scaled.createGraphics();
    graphics2D.drawImage(image, 0, 0, width, height, null);
    graphics2D.dispose();

    return scaled;
  }

  public BufferedImage getTransformedImage(final BufferedImage image, final List<TriFunction<BufferedImage, Integer, Integer, Color>> funcs) {
    final BufferedImage transformedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

    final List<BiConsumer<Integer, Integer>> cellConsumers = funcs.stream().map(func -> {
      final BiConsumer<Integer, Integer> function = (x, y) -> {
        final Color color = func.apply(image, x, y);
        transformedImage.setRGB(x, y, color.getRGB());
      };

      return function;
    }).collect(Collectors.toList());

    imageTraversalService.traverseImage(transformedImage.getWidth(), transformedImage.getHeight(), cellConsumers, false);

    return transformedImage;
  }
}
