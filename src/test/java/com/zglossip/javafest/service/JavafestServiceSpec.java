package com.zglossip.javafest.service;

import com.zglossip.javafest.service.flame.FlameService;
import com.zglossip.javafest.service.image.ImageEditorService;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;

@SpringBootTest
public class JavafestServiceSpec {

  FlameService flameService = Mockito.mock(FlameService.class);
  ImageEditorService imageEditorService = Mockito.mock(ImageEditorService.class);

  JavafestService javafestService = new JavafestService(flameService, imageEditorService);

  @Test
  public void testExec() {
    //Given
    final String filepath = "test.com";
    final Integer width = 100;
    final Integer height = 101;
    final boolean invert = true;

    //When
    javafestService.exec(filepath, width, height, invert, false);

    //Then
    final InOrder inOrder = inOrder(flameService);

    inOrder.verify(flameService, times(1)).printFlame(filepath, width, height, invert);
  }

  //TODO Add test case for copy

}
