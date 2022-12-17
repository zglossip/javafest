package com.zglossip.javafest.service.image;

import com.zglossip.javafest.service.ImageTraversalService;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Service
public class ImageTransformService {

  private final ImageTraversalService imageTraversalService;

  public ImageTransformService(final ImageTraversalService imageTraversalService) {
    this.imageTraversalService = imageTraversalService;
  }

  public BufferedImage getScaledImage(final BufferedImage image, final Integer width, final Integer height) {
    final BufferedImage scaled = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    final Graphics2D graphics2D = scaled.createGraphics();
    graphics2D.drawImage(image, 0, 0, width, height, null);
    graphics2D.dispose();

    return scaled;
  }

  public BufferedImage getColoredImage(final BufferedImage image, final List<Function<Color, Color>> colorFuncs) {
    if (colorFuncs.isEmpty()) {
      return image;
    }

    final BufferedImage coloredImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

    final BiConsumer<Integer, Integer> cellConsumer = (x, y) -> {
      colorFuncs.forEach(func -> {
        final Color color = func.apply(new Color(image.getRGB(x, y), true));
        coloredImage.setRGB(x, y, color.getRGB());
      });
    };

    imageTraversalService.traverseImage(coloredImage.getWidth(), coloredImage.getHeight(), cellConsumer, null);

    return coloredImage;

  }
}
