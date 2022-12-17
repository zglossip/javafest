package com.zglossip.javafest.service;

import com.zglossip.javafest.base.TestBase;
import com.zglossip.javafest.domain.enums.EditorType;
import com.zglossip.javafest.service.flame.FlameService;
import com.zglossip.javafest.service.image.ImageEditorService;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;

public class JavafestServiceSpec extends TestBase {

  FlameService flameService = Mockito.mock(FlameService.class);
  ImageEditorService imageEditorService = Mockito.mock(ImageEditorService.class);

  JavafestService javafestService = new JavafestService(flameService, imageEditorService);

  @Test
  public void testExecAscii() {
    //Given
    final EditorType editorType = EditorType.ASCII;
    final String filepath = "test.com";
    final Integer width = 100;
    final Integer height = 101;
    final boolean invert = true;

    //When
    javafestService.exec(editorType, filepath, width, height, invert);

    //Then
    final InOrder inOrder = inOrder(flameService);

    inOrder.verify(flameService, times(1)).printFlame(filepath, width, height, invert);
  }

  @Test
  public void testExecCopy() {
    //Given
    final EditorType editorType = EditorType.COPY;
    final String filepath = "test.com";
    final Integer width = 100;
    final Integer height = 101;
    final boolean invert = true;

    //When
    javafestService.exec(editorType, filepath, width, height, invert);

    //Then
    final InOrder inOrder = inOrder(imageEditorService);

    inOrder.verify(imageEditorService, times(1)).copyImage(filepath, width, height, invert);
  }

}
