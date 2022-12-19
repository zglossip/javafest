package com.zglossip.javafest.service.image;

import com.zglossip.javafest.base.TestBase;
import com.zglossip.javafest.domain.TriFunction;
import com.zglossip.javafest.service.ImageIOService;
import com.zglossip.javafest.service.ImageUtilService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;

public class ImageEditorServiceSpec extends TestBase {

  private final ImageIOService imageIOService = Mockito.mock(ImageIOService.class);
  private final ImageTransformService imageTransformService = Mockito.mock(ImageTransformService.class);
  private final ImageUtilService imageUtilService = Mockito.mock(ImageUtilService.class);

  final ImageEditorService imageEditorService = new ImageEditorService(imageIOService, imageTransformService, imageUtilService);

  @Test
  public void testCopy() throws IOException {
    //Given
    final String filepath = "./src/test/resources/good_for_her.jpg";
    final Integer width = 4;
    final Integer height = 3;
    final boolean invert = false;

    final BufferedImage image = Mockito.mock(BufferedImage.class);

    when(image.getWidth()).thenReturn(12);
    when(image.getHeight()).thenReturn(12);
    when(imageIOService.read(filepath)).thenReturn(image);

    final TriFunction<BufferedImage, Integer, Integer, Color> scaleFunc = (i, x, y) -> Color.CYAN;
    when(imageUtilService.getScaleFunction(new BigDecimal("3.0"), new BigDecimal("4.0"))).thenReturn(scaleFunc);

    final BufferedImage transformedImage = Mockito.mock(BufferedImage.class);
    when(imageTransformService.getTransformedImage(eq(image), eq(width), eq(height), eq(List.of(scaleFunc)))).thenReturn(transformedImage);

    //When
    imageEditorService.printImage(filepath, width, height, invert, false);

    //Then
    verify(imageIOService, times(1)).write(transformedImage);
  }

  @Test
  public void testCopyWithFooter() {
    //Given
    final String filepath = "./src/test/resources/good_for_her.jpg";
    final Integer width = 140;
    final Integer height = 170;
    final boolean invert = false;
    final boolean footer = true;

    //When
    try {
      imageEditorService.printImage(filepath, width, height, invert, footer);
    } catch (final RuntimeException e) {
      return;
    }

    //Shouldn't reach this
    assert false;

  }

  @Test
  public void testCopyNullWidth() throws IOException {
    //Given
    final String filepath = "./src/test/resources/good_for_her.jpg";
    final Integer width = null;
    final Integer height = 4;
    final boolean invert = false;

    final BufferedImage image = Mockito.mock(BufferedImage.class);

    when(image.getWidth()).thenReturn(12);
    when(image.getHeight()).thenReturn(12);
    when(imageIOService.read(filepath)).thenReturn(image);

    final TriFunction<BufferedImage, Integer, Integer, Color> scaleFunc = (i, x, y) -> Color.CYAN;
    when(imageUtilService.getScaleFunction(new BigDecimal("3.0"), new BigDecimal("3.0"))).thenReturn(scaleFunc);

    final BufferedImage transformedImage = Mockito.mock(BufferedImage.class);
    when(imageTransformService.getTransformedImage(eq(image), eq(4), eq(height), eq(List.of(scaleFunc)))).thenReturn(transformedImage);

    //When
    imageEditorService.printImage(filepath, width, height, invert, false);

    //Then
    verify(imageIOService, times(1)).write(transformedImage);
  }

  @Test
  public void testCopyNullHeight() throws IOException {
    //Given
    final String filepath = "./src/test/resources/good_for_her.jpg";
    final Integer width = 4;
    final Integer height = null;
    final boolean invert = false;

    final BufferedImage image = Mockito.mock(BufferedImage.class);

    when(image.getWidth()).thenReturn(12);
    when(image.getHeight()).thenReturn(12);
    when(imageIOService.read(filepath)).thenReturn(image);

    final TriFunction<BufferedImage, Integer, Integer, Color> scaleFunc = (i, x, y) -> Color.CYAN;
    when(imageUtilService.getScaleFunction(new BigDecimal("3.0"), new BigDecimal("3.0"))).thenReturn(scaleFunc);

    final BufferedImage transformedImage = Mockito.mock(BufferedImage.class);
    when(imageTransformService.getTransformedImage(eq(image), eq(width), eq(4), eq(List.of(scaleFunc)))).thenReturn(transformedImage);

    //When
    imageEditorService.printImage(filepath, width, height, invert, false);

    //Then
    verify(imageIOService, times(1)).write(transformedImage);
  }

  @Test
  public void testCopyBothNull() throws IOException {
    //Given
    final String filepath = "./src/test/resources/good_for_her.jpg";
    final Integer width = null;
    final Integer height = null;
    final boolean invert = false;

    final BufferedImage image = Mockito.mock(BufferedImage.class);
    when(image.getWidth()).thenReturn(4);
    when(image.getHeight()).thenReturn(3);
    when(imageIOService.read(filepath)).thenReturn(image);

    final BufferedImage transformedImage = Mockito.mock(BufferedImage.class);
    when(imageTransformService.getTransformedImage(eq(image), eq(4), eq(3), eq(List.of()))).thenReturn(transformedImage);

    //When
    imageEditorService.printImage(filepath, width, height, invert, false);

    //Then
    verify(imageIOService, times(1)).write(transformedImage);
  }

  @Test
  public void testCopyInverted() throws IOException {
    //Given
    final String filepath = "./src/test/resources/good_for_her.jpg";
    final Integer width = null;
    final Integer height = null;
    final boolean invert = true;

    final BufferedImage image = Mockito.mock(BufferedImage.class);
    when(image.getWidth()).thenReturn(4);
    when(image.getHeight()).thenReturn(3);
    when(imageIOService.read(filepath)).thenReturn(image);

    final TriFunction<BufferedImage, Integer, Integer, Color> invertFunc = (i, x, y) -> Color.CYAN;
    when(imageUtilService.getInvertColorFunction()).thenReturn(invertFunc);

    final BufferedImage transformedImage = Mockito.mock(BufferedImage.class);
    when(imageTransformService.getTransformedImage(eq(image), eq(4), eq(3), eq(List.of(invertFunc)))).thenReturn(transformedImage);

    //When
    imageEditorService.printImage(filepath, width, height, invert, false);

    //Then
    verify(imageIOService, times(1)).write(transformedImage);
  }
}
