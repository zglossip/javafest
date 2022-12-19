package com.zglossip.javafest.service.flame;

import com.zglossip.javafest.domain.TriFunction;
import com.zglossip.javafest.service.ImageUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.zglossip.javafest.util.AsciiUtil.getCharacterFromColor;

@Service
public class FlameConsumerService {

  final private ImageUtilService imageUtilService;

  @Autowired
  public FlameConsumerService(final ImageUtilService imageUtilService) {
    this.imageUtilService = imageUtilService;
  }

  List<BiConsumer<Integer, Integer>> getAsciiCellConsumers(final int validatedWidth,
                                                           final int validatedHeight,
                                                           final BufferedImage image,
                                                           final List<TriFunction<BufferedImage, Integer, Integer, Color>> functionList,
                                                           final StringBuilder asciiString) {
//    final BigDecimal xScale = new BigDecimal(validatedWidth).divide(new BigDecimal(image.getWidth()), RoundingMode.HALF_UP);

    final BigDecimal xScale = BigDecimal.valueOf((double) image.getWidth() / (double) validatedWidth);
    final BigDecimal yScale = BigDecimal.valueOf((double) image.getHeight() / (double) validatedHeight);

    final List<TriFunction<BufferedImage, Integer, Integer, Color>> funcListWithScale = new ArrayList<>(List.of(imageUtilService.getScaleFunction(xScale, yScale)));

    funcListWithScale.addAll(functionList);

    return funcListWithScale.stream().map(func -> (BiConsumer<Integer, Integer>) (x, y) -> {
      final Color color = func.apply(image, x, y);
      asciiString.append(getCharacterFromColor(color.getRed(), color.getBlue(), color.getGreen()));
    }).collect(Collectors.toList());
  }

  List<Consumer<Integer>> getAsciiRowConsumer(final StringBuilder asciiString) {
    return List.of(y -> asciiString.append("\n"));
  }

}
