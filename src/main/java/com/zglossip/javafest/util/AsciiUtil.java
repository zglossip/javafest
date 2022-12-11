package com.zglossip.javafest.util;

public class AsciiUtil {

  private static final String GREY_SCALE = " `.-':_,^=;><+!rc*/z?sLTv)J7(|Fi{C}fI31tlu[neoZ5Yxjya]2ESwqkP6h9d4VpOGbUAKXHm8RD#$Bg0MNWQ%&@";

  public static String getCharacterFromColor(final int r, final int b, final int g) {
    return getCharacterFromColorBw((r + b + g) / 3);
  }

  public static String getCharacterFromColorBw(final int color) {
    final int numberOfSymbols = GREY_SCALE.length();
    final int position = (numberOfSymbols - 1) * color / 255;
    return String.valueOf(GREY_SCALE.charAt(position));
  }

}
