package com.zglossip.javafest.service;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static com.zglossip.javafest.util.AsciiUtil.getCharacterFromColor;

@Service
public class FlameConsumerService {

  public List<BiConsumer<Integer, Integer>> getAsciiCellConsumers(final BufferedImage image, final StringBuilder asciiString) {

    return List.of((x, y) -> {
      final Color color = new Color(image.getRGB(x, y));
      asciiString.append(getCharacterFromColor(color.getRed(), color.getBlue(), color.getGreen()));
    });
  }

  public List<Consumer<Integer>> getAsciiRowConsumer(final StringBuilder asciiString) {
    return List.of(y -> asciiString.append("\n"));
  }

}
