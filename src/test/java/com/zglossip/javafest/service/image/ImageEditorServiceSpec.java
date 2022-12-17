package com.zglossip.javafest.service.image;

import com.zglossip.javafest.service.ImageIOService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ImageEditorServiceSpec {

  final ImageIOService imageIOService = Mockito.mock(ImageIOService.class);
  final ImageTransformService imageTransformService = Mockito.mock(ImageTransformService.class);

  final ImageEditorService imageEditorService = new ImageEditorService(imageIOService, imageTransformService);

  @Test
  public void testCopy() {
    //Given
    final String filepath = "./src/test/resources/good_for_her.jpg";
    final Integer width = 140;
    final Integer height = 170;
    final boolean invert = true;

    final BufferedImage image = Mockito.mock(BufferedImage.class);
    final BufferedImage scaledImage = Mockito.mock(BufferedImage.class);

    when(image.getHeight()).thenReturn(200);
    when(image.getWidth()).thenReturn(200);
    when(imageIOService.read(any(FileInputStream.class))).thenReturn(image);
    when(imageTransformService.getScaledImage(image, width, height)).thenReturn(scaledImage);

    //When
    imageEditorService.copyImage(filepath, width, height, invert);

    //Then
    verify(imageIOService, times(1)).write(scaledImage);
  }

  //TODO: I don't like this test, but I just don't want to deal with this right now
  @Test
  public void testCopyNullFile() {
    //Given
    final String filepath = null;
    final Integer width = 140;
    final Integer height = 170;
    final boolean invert = true;

    final BufferedImage image = Mockito.mock(BufferedImage.class);
    final BufferedImage scaledImage = Mockito.mock(BufferedImage.class);

    when(image.getHeight()).thenReturn(200);
    when(image.getWidth()).thenReturn(200);
    when(imageIOService.read(any(InputStream.class))).thenReturn(image);
    when(imageTransformService.getScaledImage(image, width, height)).thenReturn(scaledImage);

    //When
    imageEditorService.copyImage(filepath, width, height, invert);

    //Then
    verify(imageIOService, times(1)).write(scaledImage);
  }

  @Test
  public void testCopyNullWidth() {
    //Given
    final String filepath = "./src/test/resources/good_for_her.jpg";
    final Integer width = null;
    final Integer height = 170;
    final boolean invert = true;

    final BufferedImage image = Mockito.mock(BufferedImage.class);
    final BufferedImage scaledImage = Mockito.mock(BufferedImage.class);

    when(image.getHeight()).thenReturn(200);
    when(image.getWidth()).thenReturn(200);
    when(imageIOService.read(any(FileInputStream.class))).thenReturn(image);
    when(imageTransformService.getScaledImage(image, 200, height)).thenReturn(scaledImage);

    //When
    imageEditorService.copyImage(filepath, width, height, invert);

    //Then
    verify(imageIOService, times(1)).write(scaledImage);
  }

  @Test
  public void testCopyNullHeight() {
    //Given
    final String filepath = "./src/test/resources/good_for_her.jpg";
    final Integer width = 140;
    final Integer height = null;
    final boolean invert = true;

    final BufferedImage image = Mockito.mock(BufferedImage.class);
    final BufferedImage scaledImage = Mockito.mock(BufferedImage.class);

    when(image.getHeight()).thenReturn(200);
    when(image.getWidth()).thenReturn(200);
    when(imageIOService.read(any(FileInputStream.class))).thenReturn(image);
    when(imageTransformService.getScaledImage(image, width, 200)).thenReturn(scaledImage);

    //When
    imageEditorService.copyImage(filepath, width, height, invert);

    //Then
    verify(imageIOService, times(1)).write(scaledImage);
  }
}
