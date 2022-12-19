package com.zglossip.javafest.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Service
public class ImageTraversalService {

  //TODO: Write test
  public void traverseImage(final int width, final int height, final List<BiConsumer<Integer, Integer>> cellConsumers, final List<Consumer<Integer>> rowConsumers, final boolean truncate) {
    for (int y = 0; y < height; y++) {
      if (truncate && y % 2 == 0) {
        continue;
      }
      final int finalY = y;
      for (int x = 0; x < width; x++) {
        final int finalX = x;
        cellConsumers.forEach(con -> con.accept(finalX, finalY));
      }
      rowConsumers.forEach(con -> con.accept(finalY));
    }
  }

}
