package com.zglossip.javafest.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiConsumer;

@Service
public class ImageTraversalService {

  //TODO: Write test
  public void traverseImage(final int width, final int height, final List<BiConsumer<Integer, Integer>> cellConsumers, final boolean truncate) {
    for (int y = 0; y < height; y++) {
      if (truncate && y % 2 == 0) {
        continue;
      }
      for (int x = 0; x < width; x++) {
        final int finalX = x;
        final int finalY = y;
        cellConsumers.forEach(con -> con.accept(finalX, finalY));
      }
    }
  }

}
