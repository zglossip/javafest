package com.zglossip.javafest.util;

import java.awt.*;
import java.util.function.Function;

public class ImageUtil {
  public static final String IMAGE_PATH = "madeline_kahn_as_mrs_white_in_clue_saying_flames.png";
  public static Function<Color, Color> INVERT_COLOR_FUNC = color -> new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
}
