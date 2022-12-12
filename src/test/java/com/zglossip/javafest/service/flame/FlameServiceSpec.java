package com.zglossip.javafest.service.flame;

import com.zglossip.javafest.domain.AsciiImage;
import com.zglossip.javafest.service.PrintService;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class FlameServiceSpec {

  FlameVisualsService flameVisualsService = mock(FlameVisualsService.class);
  PrintService printService = mock(PrintService.class);

  FlameService flameService = new FlameService(flameVisualsService, printService);

  @Test
  public void testPrintMadelineKahnAsMrsWhiteInClueSayingFlames() {
    //Given
    final Integer width = 100;
    final Integer height = 100;
    final boolean inverted = false;

    final String mkAscii = "MK ASCII";
    final String footer = "FOOTER";

    when(flameVisualsService.getMkAscii(width, height)).thenReturn(new AsciiImage(mkAscii, width));
    when(flameVisualsService.getFooter(width)).thenReturn(footer);

    //When
    flameService.printFlame(null, width, height, inverted);

    //Then
    final InOrder inOrder = inOrder(printService);

    inOrder.verify(printService, times(1)).printText(mkAscii);
    inOrder.verify(printService, times(1)).printText(footer);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  public void testPrintMadelineKahnAsMrsWhiteInClueSayingFlamesInverted() {
    //Given
    final Integer width = 100;
    final Integer height = 100;
    final boolean inverted = true;

    final String mkAscii = "MK ASCII";
    final String footer = "FOOTER";

    when(flameVisualsService.getInvertedMkAscii(width, height)).thenReturn(new AsciiImage(mkAscii, width));
    when(flameVisualsService.getFooter(width)).thenReturn(footer);

    //When
    flameService.printFlame(null, width, height, inverted);

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
    final boolean inverted = false;

    final String ascii = "ASCII";
    final String footer = "FOOTER";

    when(flameVisualsService.getCustomAscii(filepath, width, height)).thenReturn(new AsciiImage(ascii, width));
    when(flameVisualsService.getFooter(width)).thenReturn(footer);

    //When
    flameService.printFlame(filepath, width, height, inverted);

    //Then
    final InOrder inOrder = inOrder(printService);

    inOrder.verify(printService, times(1)).printText(ascii);
    inOrder.verify(printService, times(1)).printText(footer);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  public void testCustomSayingFlamesInverted() {
    //Given
    final Integer width = 100;
    final Integer height = 100;
    final String filepath = "file.jpg";
    final boolean inverted = true;

    final String ascii = "ASCII";
    final String footer = "FOOTER";

    when(flameVisualsService.getCustomAsciiInverted(filepath, width, height)).thenReturn(new AsciiImage(ascii, width));
    when(flameVisualsService.getFooter(width)).thenReturn(footer);

    //When
    flameService.printFlame(filepath, width, height, inverted);

    //Then
    final InOrder inOrder = inOrder(printService);

    inOrder.verify(printService, times(1)).printText(ascii);
    inOrder.verify(printService, times(1)).printText(footer);
    inOrder.verifyNoMoreInteractions();
  }

}
