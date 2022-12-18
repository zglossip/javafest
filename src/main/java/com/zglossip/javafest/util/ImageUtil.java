package com.zglossip.javafest.util;

import com.zglossip.javafest.domain.TriFunction;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtil {
  public static final String IMAGE_PATH = "madeline_kahn_as_mrs_white_in_clue_saying_flames.png";
  public static TriFunction<BufferedImage, Integer, Integer, Color> INVERT_COLOR_FUNC = (image, x, y) -> {
    Color color = new Color(image.getRGB(x, y), true);
    return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
  };
}
