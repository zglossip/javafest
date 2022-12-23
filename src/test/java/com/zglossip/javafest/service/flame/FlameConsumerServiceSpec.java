package com.zglossip.javafest.service.flame;

import com.zglossip.javafest.base.TestBase;
import com.zglossip.javafest.service.FlameConsumerService;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.function.BiConsumer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FlameConsumerServiceSpec extends TestBase {

  FlameConsumerService flameConsumerService = new FlameConsumerService();

  //TODO: How to verify consumers?
  @Test
  public void testGetAsciiCellConsumers() {
    //Given params
    final BufferedImage image = mock(BufferedImage.class);
    when(image.getWidth()).thenReturn(1200);
    when(image.getHeight()).thenReturn(1200);
    final StringBuilder asciiString = new StringBuilder();


    //When
    final List<BiConsumer<Integer, Integer>> result = flameConsumerService.getAsciiCellConsumers(
            image,
            asciiString
    );

    //Then
    assert result.size() == 1;
  }

}
