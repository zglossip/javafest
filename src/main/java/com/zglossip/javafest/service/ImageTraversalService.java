package com.zglossip.javafest.service;

import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Service
public class ImageTraversalService {

  public void traverseImage(final int width, final int height, final BiConsumer<Integer, Integer> cellConsumer, final Consumer<Integer> rowConsumer) {
    for (int y = 0; y < height; y++) {
      if (rowConsumer != null) {
        rowConsumer.accept(y);
      }
      for (int x = 0; x < width; x++) {
        if (cellConsumer != null) {
          cellConsumer.accept(x, y);
        }
      }
    }
  }

}
