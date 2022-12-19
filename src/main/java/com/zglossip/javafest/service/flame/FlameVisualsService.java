package com.zglossip.javafest.service.flame;

import com.zglossip.javafest.domain.AsciiImage;
import com.zglossip.javafest.domain.TriFunction;
import com.zglossip.javafest.exceptions.ImageException;
import com.zglossip.javafest.service.ImageIOService;
import com.zglossip.javafest.service.ImageTraversalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

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

  private final ImageTraversalService imageTraversalService;
  private final FlameConsumerService flameConsumerService;
  private final ImageIOService imageIOService;

  @Autowired
  public FlameVisualsService(final ImageTraversalService imageTraversalService,
                             final FlameConsumerService flameConsumerService,
                             final ImageIOService imageIOService) {
    this.imageTraversalService = imageTraversalService;
    this.flameConsumerService = flameConsumerService;
    this.imageIOService = imageIOService;
  }

  public String getFooter(final int width) {
    final StringBuilder stringBuilder = new StringBuilder();

    final String spaces = getSpaces((width - FOOTER_WIDTH) / 2);

    Arrays.stream(FOOTER.split("\n")).forEach(string -> {
      stringBuilder.append(spaces).append(string).append("\n");
    });

    return stringBuilder.toString();
  }

  public AsciiImage getAsciiString(final String filepath, final Integer width, final Integer height, final List<TriFunction<BufferedImage, Integer, Integer, Color>> functionList) {
    final BufferedImage image;
    try {
      image = imageIOService.read(filepath);
    } catch (final IOException e) {
      throw new ImageException();
    }
    final int validatedWidth = getValidatedWidth(width, height, image.getWidth(), image.getHeight());
    final int validatedHeight = getValidatedHeight(height, validatedWidth, image.getWidth(), image.getHeight());

    final StringBuilder asciiString = new StringBuilder();

    final List<BiConsumer<Integer, Integer>> cellConsumers = flameConsumerService.getAsciiCellConsumers(
            validatedWidth,
            validatedHeight,
            image,
            functionList,
            asciiString);

    final List<Consumer<Integer>> rowConsumer = flameConsumerService.getAsciiRowConsumer(asciiString);

    imageTraversalService.traverseImage(validatedWidth, validatedHeight, cellConsumers, rowConsumer, true);

    return new AsciiImage(asciiString.toString(), validatedWidth);
  }

  private int getValidatedWidth(final Integer width, final Integer height, final int origWidth, final int orgHeight) {
    if (width == null) {
      if (height == null) {
        return DEFAULT_SIZE;
      }
      final double multiplier = (double) origWidth / orgHeight;
      return (int) Math.round(height * multiplier);
    }

    return width;
  }

  private int getValidatedHeight(final Integer height, final int validatedWidth, final int origWidth, final int orgHeight) {
    if (height == null) {
      final double multiplier = (double) orgHeight / origWidth;
      return (int) Math.round(validatedWidth * multiplier);
    }

    return height;
  }

  private String getSpaces(final int numberOfSpaces) {
    final StringBuilder spacesBuilder = new StringBuilder();
    for (int i = 0; i < numberOfSpaces; i++) {
      spacesBuilder.append(" ");
    }
    return spacesBuilder.toString();
  }

}
