package com.zglossip.javafest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageIOService {

  private final String defaultImageFile;

  @Autowired
  public ImageIOService(final String defaultImageFile) {
    this.defaultImageFile = defaultImageFile;
  }

  private final static String OUTPUT_FILE_NAME = "output.png";

  //TODO: Write test
  public BufferedImage read(final String filepath) throws IOException {
    final InputStream inputStream;

    if (filepath == null) {
      inputStream = new ClassPathResource(defaultImageFile).getInputStream();
    } else {
      inputStream = new FileInputStream(filepath);
    }

    return ImageIO.read(inputStream);
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
