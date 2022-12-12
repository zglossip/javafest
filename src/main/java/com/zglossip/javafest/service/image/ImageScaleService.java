package com.zglossip.javafest.service.image;

import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class ImageScaleService {

  public BufferedImage getScaledImage(final BufferedImage image, final Integer width, final Integer height) {
    return (BufferedImage) image.getScaledInstance(width, height, BufferedImage.SCALE_DEFAULT);
  }
}
