package com.zglossip.javafest.service;

import com.zglossip.javafest.domain.TriFunction;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ImageUtilService {
  private static final TriFunction<BufferedImage, Integer, Integer, Color> INVERT_COLOR_FUNCTION = (image, x, y) -> {
    Color color = new Color(image.getRGB(Math.min(x, image.getWidth() - 1), Math.min(y, image.getHeight() - 1)), true);
    return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
  };

  private static final TriFunction<BufferedImage, Integer, Integer, Color> TWO_COLOR_FUNCTION = (image, x, y) -> {
    Color color = new Color(image.getRGB(Math.min(x, image.getWidth() - 1), Math.min(y, image.getHeight() - 1)), true);
    int bw = (color.getBlue() + color.getGreen() + color.getRed()) / 3;
    if (bw >= 127) {
      return Color.WHITE;
    }
    return Color.BLACK;
  };

  public TriFunction<BufferedImage, Integer, Integer, Color> getInvertColorFunction() {
    return INVERT_COLOR_FUNCTION;
  }

  public TriFunction<BufferedImage, Integer, Integer, Color> getTwoColorFunction() {
    return TWO_COLOR_FUNCTION;
  }

  public TriFunction<BufferedImage, Integer, Integer, Color> getScaleFunction(final BigDecimal xScale, final BigDecimal yScale) {
    return (image, x, y) -> {
      final int newX = xScale.multiply(new BigDecimal(x)).setScale(0, RoundingMode.DOWN).intValue();
      final int newY = yScale.multiply(new BigDecimal(y)).setScale(0, RoundingMode.DOWN).intValue();
      try {
        return new Color(image.getRGB(Math.min(newX, image.getWidth() - 1), Math.min(newY, image.getHeight() - 1)), true);
      } catch (final ArrayIndexOutOfBoundsException e) {
        return new Color(image.getRGB(newX - 1, newY), true);
      }
    };
  }
}
