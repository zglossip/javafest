package com.zglossip.javafest.service.image;

import com.zglossip.javafest.exceptions.ImageException;
import com.zglossip.javafest.service.BaseEditorService;
import com.zglossip.javafest.service.ImageIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.zglossip.javafest.util.ImageUtil.IMAGE_PATH;
import static com.zglossip.javafest.util.ImageUtil.INVERT_COLOR_FUNC;

@Service
public class ImageEditorService extends BaseEditorService {

  private final ImageIOService imageIOService;
  private final ImageTransformService imageTransformService;

  @Autowired
  public ImageEditorService(final ImageIOService imageIOService, final ImageTransformService imageTransformService) {
    this.imageIOService = imageIOService;
    this.imageTransformService = imageTransformService;
  }

  @Override
  public void printImage(final String filepath, final Integer width, final Integer height, final boolean invert, final boolean footer) {

    if (footer) {
      throw new RuntimeException("Cannot print footer");
    }

    BufferedImage image;
    try {
      image = imageIOService.read(validateAndGetInputStream(filepath));
    } catch (final IOException e) {
      throw new ImageException();
    }

    if (invert) {
      image = imageTransformService.getTransformedImage(image, List.of(INVERT_COLOR_FUNC));
    }

    imageIOService.write(imageTransformService.getScaledImage(image, validateWidth(width, image.getWidth()), validateHeight(height, image.getHeight())));
  }

  private InputStream validateAndGetInputStream(final String filepath) throws IOException {
    if (filepath == null) {
      return new ClassPathResource(IMAGE_PATH).getInputStream();
    }
    return new FileInputStream(filepath);
  }

  private int validateWidth(final Integer width, final int origWidth) {
    if (width == null) {
      return origWidth;
    }

    return width;
  }

  private int validateHeight(final Integer height, final int origHeight) {
    if (height == null) {
      return origHeight;
    }

    return height;
  }
}
