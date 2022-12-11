package com.zglossip.javafest.service;

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

    when(flamesService.getMkAscii(width, height)).thenReturn(mkAscii);
    when(flamesService.getFooter(width)).thenReturn(footer);

    //When
    javafestService.printMadelineKahnAsMrsWhiteInClueSayingFlames(width, height);

    //Then
    final InOrder inOrder = inOrder(printService);

    inOrder.verify(printService, times(1)).printText(mkAscii);
    inOrder.verify(printService, times(1)).printText(footer);
    inOrder.verifyNoMoreInteractions();
  }

}
