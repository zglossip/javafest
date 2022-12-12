package com.zglossip.javafest.service.image;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

@Service
public class ImageScaleService {

  public BufferedImage getScaledImage(final BufferedImage image, final Integer width, final Integer height) {
    final BufferedImage scaled = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    final Graphics2D graphics2D = scaled.createGraphics();
    graphics2D.drawImage(image, 0, 0, width, height, null);
    graphics2D.dispose();

    return scaled;
  }
}
