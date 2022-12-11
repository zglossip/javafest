package com.zglossip.javafest.service;

import com.zglossip.javafest.domain.AsciiImage;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class JavafestServiceSpec {

  FlamesService flamesService = mock(FlamesService.class);
  PrintService printService = mock(PrintService.class);

  JavafestService javafestService = new JavafestService(flamesService, printService);

  @Test
  public void testPrintMadelineKahnAsMrsWhiteInClueSayingFlames() {
    //Given
    final Integer width = 100;
    final Integer height = 100;

    final String mkAscii = "MK ASCII";
    final String footer = "FOOTER";

    when(flamesService.getMkAscii(width, height)).thenReturn(new AsciiImage(mkAscii, width));
    when(flamesService.getFooter(width)).thenReturn(footer);

    //When
    javafestService.exec(null, width, height);

    //Then
    final InOrder inOrder = inOrder(printService);

    inOrder.verify(printService, times(1)).printText(mkAscii);
    inOrder.verify(printService, times(1)).printText(footer);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  public void testCustomSayingFlames() {
    //Given
    final Integer width = 100;
    final Integer height = 100;
    final String filepath = "file.jpg";

    final String ascii = "ASCII";
    final String footer = "FOOTER";

    when(flamesService.getCustomAscii(filepath, width, height)).thenReturn(new AsciiImage(ascii, width));
    when(flamesService.getFooter(width)).thenReturn(footer);

    //When
    javafestService.exec(filepath, width, height);

    //Then
    final InOrder inOrder = inOrder(printService);

    inOrder.verify(printService, times(1)).printText(ascii);
    inOrder.verify(printService, times(1)).printText(footer);
    inOrder.verifyNoMoreInteractions();
  }

}
