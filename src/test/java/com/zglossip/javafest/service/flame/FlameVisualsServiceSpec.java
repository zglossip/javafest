package com.zglossip.javafest.service.flame;

import com.zglossip.javafest.base.TestBase;
import com.zglossip.javafest.domain.AsciiImage;
import com.zglossip.javafest.service.ImageIOService;
import com.zglossip.javafest.service.ImageTraversalService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static org.mockito.Mockito.*;

@Component
public class FlameVisualsServiceSpec extends TestBase {

  final private ImageTraversalService imageTraversalService = Mockito.mock(ImageTraversalService.class);
  final private FlameConsumerService flameConsumerService = Mockito.mock(FlameConsumerService.class);
  final private ImageIOService imageIOService = Mockito.mock(ImageIOService.class);

  final private FlameVisualsService flameVisualsService = new FlameVisualsService(
          imageTraversalService,
          flameConsumerService,
          imageIOService);

  @Test
  public void testAsciiImage() throws IOException {
    //Given needed input
    final String filePath = "good_for_her.jpg";
    final Integer width = 100;
    final Integer height = 100;

    //And mocks
    final BufferedImage bufferedImage = Mockito.mock(BufferedImage.class);
    when(imageIOService.read(filePath)).thenReturn(bufferedImage);
    final List<BiConsumer<Integer, Integer>> cellConsumers = List.of((x, y) -> {
    });
    when(flameConsumerService.getAsciiCellConsumers(
            eq(width),
            eq(height),
            eq(bufferedImage),
            argThat(List::isEmpty),
            any(StringBuilder.class))).thenReturn(cellConsumers);
    final List<Consumer<Integer>> rowConsumer = List.of(x -> {
    });
    when(flameConsumerService.getAsciiRowConsumer(any(StringBuilder.class))).thenReturn(rowConsumer);

    //When
    final AsciiImage result = flameVisualsService.getAsciiString(filePath, width, height, List.of());

    //Then
    final AsciiImage expected = new AsciiImage("", width);
    assert Objects.equals(result, expected);

    verify(imageTraversalService, times(1)).traverseImage(eq(width), eq(height), eq(cellConsumers), eq(rowConsumer), eq(true));
  }

  @Test
  public void testAsciiImageNullWidth() throws IOException {
    //Given needed input
    final String filePath = "good_for_her.jpg";
    final Integer width = null;
    final Integer height = 100;

    //And mocks
    final BufferedImage bufferedImage = Mockito.mock(BufferedImage.class);
    when(bufferedImage.getWidth()).thenReturn(600);
    when(bufferedImage.getHeight()).thenReturn(300);
    when(imageIOService.read(filePath)).thenReturn(bufferedImage);
    final List<BiConsumer<Integer, Integer>> cellConsumers = List.of((x, y) -> {
    });
    when(flameConsumerService.getAsciiCellConsumers(
            eq(200),
            eq(height),
            eq(bufferedImage),
            argThat(List::isEmpty),
            any(StringBuilder.class))).thenReturn(cellConsumers);
    final List<Consumer<Integer>> rowConsumer = List.of(x -> {
    });
    when(flameConsumerService.getAsciiRowConsumer(any(StringBuilder.class))).thenReturn(rowConsumer);

    //When
    final AsciiImage result = flameVisualsService.getAsciiString(filePath, width, height, List.of());

    //Then
    final AsciiImage expected = new AsciiImage("", 200);
    assert Objects.equals(result, expected);

    verify(imageTraversalService, times(1)).traverseImage(eq(200), eq(height), eq(cellConsumers), eq(rowConsumer), eq(true));
  }

  @Test
  public void testAsciiImageNullHeight() throws IOException {
    //Given needed input
    final String filePath = "good_for_her.jpg";
    final Integer width = 400;
    final Integer height = null;

    //And mocks
    final BufferedImage bufferedImage = Mockito.mock(BufferedImage.class);
    when(bufferedImage.getWidth()).thenReturn(600);
    when(bufferedImage.getHeight()).thenReturn(300);
    when(imageIOService.read(filePath)).thenReturn(bufferedImage);
    final List<BiConsumer<Integer, Integer>> cellConsumers = List.of((x, y) -> {
    });
    when(flameConsumerService.getAsciiCellConsumers(
            eq(width),
            eq(200),
            eq(bufferedImage),
            argThat(List::isEmpty),
            any(StringBuilder.class))).thenReturn(cellConsumers);
    final List<Consumer<Integer>> rowConsumer = List.of(x -> {
    });
    when(flameConsumerService.getAsciiRowConsumer(any(StringBuilder.class))).thenReturn(rowConsumer);

    //When
    final AsciiImage result = flameVisualsService.getAsciiString(filePath, width, height, List.of());

    //Then
    final AsciiImage expected = new AsciiImage("", 400);
    assert Objects.equals(result, expected);

    verify(imageTraversalService, times(1)).traverseImage(eq(width), eq(200), eq(cellConsumers), eq(rowConsumer), eq(true));
  }

}
