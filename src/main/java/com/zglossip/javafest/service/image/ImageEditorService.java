package com.zglossip.javafest.service.image;

import com.zglossip.javafest.exceptions.ImageException;
import com.zglossip.javafest.service.ImageIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.zglossip.javafest.util.ImageUtil.IMAGE_PATH;

@Service
public class ImageEditorService {

  private final ImageIOService imageIOService;
  private final ImageScaleService imageScaleService;

  @Autowired
  public ImageEditorService(final ImageIOService imageIOService, final ImageScaleService imageScaleService) {
    this.imageIOService = imageIOService;
    this.imageScaleService = imageScaleService;
  }

  public void copyImage(final String filepath, final Integer width, final Integer height) {

    final BufferedImage image;
    try {
      image = imageIOService.read(validateAndGetInputStream(filepath));
    } catch (final IOException e) {
      throw new ImageException();
    }

    imageIOService.write(imageScaleService.getScaledImage(image, validateWidth(width, image.getWidth()), validateHeight(height, image.getHeight())));
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
