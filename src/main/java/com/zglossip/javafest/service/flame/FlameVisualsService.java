package com.zglossip.javafest.service.flame;

import com.zglossip.javafest.domain.AsciiImage;
import com.zglossip.javafest.service.ImageTraversalService;
import com.zglossip.javafest.service.image.ImageEditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Service
public class FlameVisualsService {

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
  private final ImageEditorService imageEditorService;

  @Autowired
  public FlameVisualsService(final ImageTraversalService imageTraversalService,
                             final FlameConsumerService flameConsumerService,
                             final ImageEditorService imageEditorService) {
    this.imageTraversalService = imageTraversalService;
    this.flameConsumerService = flameConsumerService;
    this.imageEditorService = imageEditorService;
  }

  public String getFooter(final int width) {
    final StringBuilder stringBuilder = new StringBuilder();

    final String spaces = getSpaces((width - FOOTER_WIDTH) / 2);

    Arrays.stream(FOOTER.split("\n")).forEach(string -> {
      stringBuilder.append(spaces).append(string).append("\n");
    });

    return stringBuilder.toString();
  }

  public AsciiImage getAsciiString(final String filepath, final Integer width, final Integer height, final boolean invert, final boolean twoColor) {

    final StringBuilder asciiString = new StringBuilder();

    final BufferedImage image = imageEditorService.getImage(filepath, width, height, invert, twoColor);

    final List<BiConsumer<Integer, Integer>> cellConsumers = flameConsumerService.getAsciiCellConsumers(image, asciiString);

    final List<Consumer<Integer>> rowConsumer = flameConsumerService.getAsciiRowConsumer(asciiString);

    imageTraversalService.traverseImage(image.getWidth(), image.getHeight(), cellConsumers, rowConsumer, true);

    return new AsciiImage(asciiString.toString(), image.getWidth());
  }

  private String getSpaces(final int numberOfSpaces) {
    final StringBuilder spacesBuilder = new StringBuilder();
    for (int i = 0; i < numberOfSpaces; i++) {
      spacesBuilder.append(" ");
    }
    return spacesBuilder.toString();
  }

}
