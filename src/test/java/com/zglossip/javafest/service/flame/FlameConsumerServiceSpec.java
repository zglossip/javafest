package com.zglossip.javafest.service.flame;

import com.zglossip.javafest.base.TestBase;
import com.zglossip.javafest.domain.TriFunction;
import com.zglossip.javafest.service.ImageUtilService;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FlameConsumerServiceSpec extends TestBase {

  ImageUtilService imageUtilService = mock(ImageUtilService.class);

  FlameConsumerService flameConsumerService = new FlameConsumerService(imageUtilService);

  //TODO: How to verify consumers?
  @Test
  public void testGetAsciiCellConsumers() {
    //Given params
    final int validatedWidth = 300;
    final int validatedHeight = 400;
    final BufferedImage image = mock(BufferedImage.class);
    when(image.getWidth()).thenReturn(1200);
    when(image.getHeight()).thenReturn(1200);
    final List<TriFunction<BufferedImage, Integer, Integer, Color>> functionList = new ArrayList<>();
    final TriFunction<BufferedImage, Integer, Integer, Color> testFunc = (i, x, y) -> Color.CYAN;
    functionList.add(testFunc);
    final StringBuilder asciiString = new StringBuilder();

    //And mocks
    when(imageUtilService.getScaleFunction(new BigDecimal("4.0"), new BigDecimal("3.0"))).thenReturn((i, x, y) -> Color.CYAN);

    //When
    final List<BiConsumer<Integer, Integer>> result = flameConsumerService.getAsciiCellConsumers(
            validatedWidth,
            validatedHeight,
            image,
            functionList,
            asciiString
    );

    //Then
    assert result.size() == 2;
  }

}
