package com.zglossip.javafest.service.image;

import com.zglossip.javafest.domain.TriFunction;
import com.zglossip.javafest.exceptions.ImageException;
import com.zglossip.javafest.service.BaseEditorService;
import com.zglossip.javafest.service.ImageIOService;
import com.zglossip.javafest.service.ImageUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageEditorService extends BaseEditorService {

  private final ImageIOService imageIOService;
  private final ImageTransformService imageTransformService;
  private final ImageUtilService imageUtilService;

  @Autowired
  public ImageEditorService(final ImageIOService imageIOService,
                            final ImageTransformService imageTransformService,
                            final ImageUtilService imageUtilService) {
    this.imageIOService = imageIOService;
    this.imageTransformService = imageTransformService;
    this.imageUtilService = imageUtilService;
  }

  @Override
  public void printImage(final String filepath, final Integer width, final Integer height, final boolean invert, final boolean footer, final boolean twoColor) {

    if (footer) {
      throw new RuntimeException("Cannot print footer");
    }

    imageIOService.write(getImage(filepath, width, height, invert, twoColor));
  }

  public BufferedImage getImage(final String filepath, final Integer width, final Integer height, final boolean invert, final boolean twoColor) {
    final BufferedImage image;
    try {
      image = imageIOService.read(filepath);
    } catch (final IOException e) {
      throw new ImageException();
    }

    final List<TriFunction<BufferedImage, Integer, Integer, Color>> functionList = new ArrayList<>();

    final int validatedWidth = validateWidth(width, height, image.getWidth(), image.getHeight());
    final int validatedHeight = validateHeight(
            height,
            validatedWidth,
            image.getHeight(),
            image.getWidth()
    );

    if (width != null || height != null) {
      final BigDecimal xScale = BigDecimal.valueOf((double) image.getWidth() / (double) validatedWidth);
      final BigDecimal yScale = BigDecimal.valueOf((double) image.getHeight() / (double) validatedHeight);
      functionList.add(imageUtilService.getScaleFunction(xScale, yScale));
    }

    if (invert) {
      functionList.add(imageUtilService.getInvertColorFunction());
    }

    if (twoColor) {
      functionList.add(imageUtilService.getTwoColorFunction());
    }

    BufferedImage imageToWrite = image;

    for (final TriFunction<BufferedImage, Integer, Integer, Color> func : functionList) {
      imageToWrite = imageTransformService.getTransformedImage(imageToWrite, validatedWidth, validatedHeight, func);
    }

    return imageToWrite;
  }

  private int validateWidth(final Integer width, final Integer height, final int origWidth, final int orgHeight) {
    if (width == null) {
      if (height == null) {
        return origWidth;
      }
      final double multiplier = (double) origWidth / orgHeight;
      return (int) Math.round(height * multiplier);
    }

    return width;
  }

  private int validateHeight(final Integer height, final int validatedWidth, final int origHeight, final int origWidth) {
    if (height == null) {
      final double multiplier = (double) origHeight / origWidth;
      return (int) Math.round(validatedWidth * multiplier);
    }

    return height;
  }
}
