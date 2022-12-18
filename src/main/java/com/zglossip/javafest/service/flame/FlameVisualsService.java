package com.zglossip.javafest.service.flame;

import com.zglossip.javafest.domain.AsciiImage;
import com.zglossip.javafest.domain.TriFunction;
import com.zglossip.javafest.exceptions.ImageException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import static com.zglossip.javafest.util.AsciiUtil.getCharacterFromColor;
import static com.zglossip.javafest.util.ImageUtil.IMAGE_PATH;
import static com.zglossip.javafest.util.ImageUtil.INVERT_COLOR_FUNC;

@Service
public class FlameVisualsService {

  public static final int DEFAULT_SIZE = 200;
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

  public AsciiImage getMkAscii(final Integer width, final Integer height) {
    return getMkAscii(width, height, null);
  }

  public AsciiImage getInvertedMkAscii(final Integer width, final Integer height) {
    return getMkAscii(width, height, INVERT_COLOR_FUNC);
  }

  private AsciiImage getMkAscii(final Integer width, final Integer height, final TriFunction<BufferedImage, Integer, Integer, Color> colorFunc) {
    return getAsciiStringFromImage(width, height, getMkImage(), colorFunc);
  }

  public AsciiImage getCustomAscii(final String filepath, final Integer width, final Integer height) {
    return getCustomAscii(filepath, width, height, null);
  }

  public AsciiImage getCustomAsciiInverted(final String filepath, final Integer width, final Integer height) {
    return getCustomAscii(filepath, width, height, INVERT_COLOR_FUNC);
  }

  private AsciiImage getCustomAscii(final String filepath, final Integer width, final Integer height, final TriFunction<BufferedImage, Integer, Integer, Color> colorFunc) {
    return getAsciiStringFromImage(width, height, getCustomImage(filepath), colorFunc);
  }

  public String getFooter(final int width) {
    final StringBuilder stringBuilder = new StringBuilder();

    final String spaces = getSpaces((width - FOOTER_WIDTH) / 2);

    Arrays.stream(FOOTER.split("\n")).forEach(string -> {
      stringBuilder.append(spaces).append(string).append("\n");
    });

    return stringBuilder.toString();
  }

  private static BufferedImage getMkImage() {
    try {
      return getImage(new ClassPathResource(IMAGE_PATH).getInputStream());
    } catch (final IOException e) {
      throw new ImageException();
    }
  }

  private static BufferedImage getCustomImage(final String filepath) {
    try {
      return getImage(new FileInputStream(filepath));
    } catch (final IOException e) {
      throw new ImageException();
    }
  }

  //TODO: Replace this with ImageIOService
  private static BufferedImage getImage(final InputStream inputStream) {
    try {
      return ImageIO.read(inputStream);
    } catch (final IOException e) {
      throw new ImageException();
    }
  }

  //TODO Replace this with ImageTraversalService
  public static AsciiImage getAsciiStringFromImage(final Integer width, final Integer height, final BufferedImage image, final TriFunction<BufferedImage, Integer, Integer, Color> colorFunc) {
    final int validatedWidth = getValidatedWidth(width, height, image.getWidth(), image.getHeight());
    final int validatedHeight = getValidatedHeight(height, validatedWidth, image.getWidth(), image.getHeight());
    final StringBuilder asciiString = new StringBuilder();

    for (int y = 0; y < validatedHeight; y++) {
      if (y % 2 != 0) {
        continue;
      }
      for (int x = 0; x < validatedWidth; x++) {
        final int convertedX = convertPosition(x, validatedWidth, image.getWidth());
        final int convertedY = convertPosition(y, validatedHeight, image.getHeight());
        final Color color;
        if (colorFunc != null) {
          color = colorFunc.apply(image, convertedX, convertedY);
        } else {
          color = new Color(image.getRGB(convertedX, convertedY), true);
        }
        asciiString.append(getCharacterFromColor(color.getRed(), color.getBlue(), color.getGreen()));
      }
      asciiString.append("\n");
    }

    return new AsciiImage(asciiString.toString(), validatedWidth);
  }

  private static int getValidatedWidth(final Integer width, final Integer height, final int origWidth, final int orgHeight) {
    if (width == null) {
      if (height == null) {
        return DEFAULT_SIZE;
      }
      final double multiplier = (double) origWidth / orgHeight;
      return (int) Math.round(height * multiplier);
    }

    return width;
  }

  private static int getValidatedHeight(final Integer height, final int validatedWidth, final int origWidth, final int orgHeight) {
    if (height == null) {
      final double multiplier = (double) orgHeight / origWidth;
      return (int) Math.round(validatedWidth * multiplier);
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
