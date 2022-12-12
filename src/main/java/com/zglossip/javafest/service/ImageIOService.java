package com.zglossip.javafest.service;

import com.zglossip.javafest.exceptions.ImageException;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageIOService {

  private final static String OUTPUT_FILE_NAME = "output.png";

  //TODO: Write test
  public BufferedImage read(final InputStream inputStream) {
    try {
      return ImageIO.read(inputStream);
    } catch (final IOException e) {
      throw new ImageException();
    }
  }

  //TODO: Write test
  public void write(final BufferedImage image) {
    final File outputfile = new File(OUTPUT_FILE_NAME);
    try {
      ImageIO.write(image, "png", outputfile);
    } catch (final IOException e) {
      throw new RuntimeException("Unable to write image", e);
    }
  }

}
