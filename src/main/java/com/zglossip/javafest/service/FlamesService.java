package com.zglossip.javafest.service;

import com.zglossip.javafest.exceptions.ImageException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import static com.zglossip.javafest.util.AsciiUtil.getCharacterFromColor;

@Service
public class FlamesService {

  private static final String IMAGE_PATH = "madeline_kahn_as_mrs_white_in_clue_saying_flames.png";
  private static final int DEFAULT_SIZE = 200;
  private static final int FOOTER_WIDTH = 69;
  private static final String FOOTER = """
           ________ ___       ________  _____ ______   _______   ________
          |\\  _____\\\\  \\     |\\   __  \\|\\   _ \\  _   \\|\\  ___ \\ |\\   ____\\
          \\ \\  \\__/\\ \\  \\    \\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\ \\   __/|\\ \\  \\___|_
           \\ \\   __\\\\ \\  \\    \\ \\   __  \\ \\  \\\\|__| \\  \\ \\  \\_|/_\\ \\_____  \\
            \\ \\  \\_| \\ \\  \\____\\ \\  \\ \\  \\ \\  \\    \\ \\  \\ \\  \\_|\\ \\|____|\\  \\
             \\ \\__\\   \\ \\_______\\ \\__\\ \\__\\ \\__\\    \\ \\__\\ \\_______\\____\\_\\  \\
              \\|__|    \\|_______|\\|__|\\|__|\\|__|     \\|__|\\|_______|\\_________\\
                                                                   \\|_________|
                                                                              
                                                                              
          """;

  public String getMkAscii(final Integer width, final Integer height) {
    return getAsciiStringFromImage(width, height, getImage());
  }

  public String getFooter(final Integer width) {
    final StringBuilder stringBuilder = new StringBuilder();

    final String spaces = getSpaces((getValidatedWidth(width) - FOOTER_WIDTH) / 2);

    Arrays.stream(FOOTER.split("\n")).forEach(string -> {
      stringBuilder.append(spaces).append(string).append("\n");
    });

    return stringBuilder.toString();
  }

  private static BufferedImage getImage() {
    try {
      final InputStream inputStream = new ClassPathResource(IMAGE_PATH).getInputStream();
      return ImageIO.read(inputStream);
    } catch (final IOException e) {
      throw new ImageException();
    }
  }

  private static String getAsciiStringFromImage(final Integer width, final Integer height, final BufferedImage image) {
    final int validatedWidth = getValidatedWidth(width);
    final int validatedHeight = getValidatedHeight(height, validatedWidth, image.getWidth(), image.getHeight());
    final StringBuilder asciiString = new StringBuilder();

    for (int y = 0; y < validatedHeight; y++) {
      if (y % 3 != 0) {
        continue;
      }
      for (int x = 0; x < validatedWidth; x++) {
        final int convertedX = convertPosition(x, validatedWidth, image.getWidth());
        final int convertedY = convertPosition(y, validatedHeight, image.getHeight());
        final Color color = new Color(image.getRGB(convertedX, convertedY), true);
        asciiString.append(getCharacterFromColor(color.getRed(), color.getBlue(), color.getGreen()));
      }
      asciiString.append("\n");
    }

    return asciiString.toString();
  }

  private static int getValidatedWidth(final Integer width) {
    if (width == null) {
      return DEFAULT_SIZE;
    }

    return width;
  }

  private static int getValidatedHeight(final Integer height, final int validatedWidth, final int origWidth, final int orgHeight) {
    if (height == null) {
      return validatedWidth * (int) Math.round((double) orgHeight / origWidth);
    }

    return height;
  }

  private static int convertPosition(final int position, final int validatedSize, final int actualSize) {
    final double multiplier = (double) actualSize / validatedSize;
    return (int) Math.round(multiplier * position);
  }

  private static String getSpaces(final int numberOfSpaces) {
    final StringBuilder spacesBuilder = new StringBuilder();
    for (int i = 0; i < numberOfSpaces; i++) {
      spacesBuilder.append(" ");
    }
    return spacesBuilder.toString();
  }

}
