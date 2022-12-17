package com.zglossip.javafest.service.flame;

import com.zglossip.javafest.base.TestBase;
import com.zglossip.javafest.domain.AsciiImage;
import com.zglossip.javafest.service.PrintService;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;

public class FlameServiceSpec extends TestBase {

  FlameVisualsService flameVisualsService = mock(FlameVisualsService.class);
  PrintService printService = mock(PrintService.class);

  FlameService flameService = new FlameService(flameVisualsService, printService);

  @Test
  public void testPrintDefault() {
    //Given
    final Integer width = 100;
    final Integer height = 100;
    final boolean inverted = false;
    final boolean footer = false;

    final String mkAscii = "MK ASCII";

    when(flameVisualsService.getMkAscii(width, height)).thenReturn(new AsciiImage(mkAscii, width));

    //When
    flameService.printFlame(null, width, height, inverted, footer);

    //Then
    final InOrder inOrder = inOrder(printService);

    inOrder.verify(printService, times(1)).printText(mkAscii);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  public void testDefaultSayingFlames() {
    //Given
    final Integer width = 100;
    final Integer height = 100;
    final boolean inverted = false;
    final boolean footer = true;

    final String mkAscii = "MK ASCII";
    final String footerText = "FOOTER";

    when(flameVisualsService.getMkAscii(width, height)).thenReturn(new AsciiImage(mkAscii, width));
    when(flameVisualsService.getFooter(width)).thenReturn(footerText);

    //When
    flameService.printFlame(null, width, height, inverted, footer);

    //Then
    final InOrder inOrder = inOrder(printService);

    inOrder.verify(printService, times(1)).printText(mkAscii);
    inOrder.verify(printService, times(1)).printText(footerText);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  public void testDefaultInverted() {
    //Given
    final Integer width = 100;
    final Integer height = 100;
    final boolean inverted = true;
    final boolean footer = false;

    final String mkAscii = "MK ASCII";

    when(flameVisualsService.getInvertedMkAscii(width, height)).thenReturn(new AsciiImage(mkAscii, width));

    //When
    flameService.printFlame(null, width, height, inverted, footer);

    //Then
    final InOrder inOrder = inOrder(printService);

    inOrder.verify(printService, times(1)).printText(mkAscii);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  public void testCustom() {
    //Given
    final Integer width = 100;
    final Integer height = 100;
    final String filepath = "file.jpg";
    final boolean inverted = false;
    final boolean footer = false;

    final String ascii = "ASCII";

    when(flameVisualsService.getCustomAscii(filepath, width, height)).thenReturn(new AsciiImage(ascii, width));

    //When
    flameService.printFlame(filepath, width, height, inverted, footer);

    //Then
    final InOrder inOrder = inOrder(printService);

    inOrder.verify(printService, times(1)).printText(ascii);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  public void testCustomSayingFlames() {
    //Given
    final Integer width = 100;
    final Integer height = 100;
    final String filepath = "file.jpg";
    final boolean inverted = false;
    final boolean footer = true;

    final String ascii = "ASCII";
    final String footerText = "FOOTER";

    when(flameVisualsService.getCustomAscii(filepath, width, height)).thenReturn(new AsciiImage(ascii, width));
    when(flameVisualsService.getFooter(width)).thenReturn(footerText);

    //When
    flameService.printFlame(filepath, width, height, inverted, footer);

    //Then
    final InOrder inOrder = inOrder(printService);

    inOrder.verify(printService, times(1)).printText(ascii);
    inOrder.verify(printService, times(1)).printText(footerText);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  public void testCustomInverted() {
    //Given
    final Integer width = 100;
    final Integer height = 100;
    final String filepath = "file.jpg";
    final boolean inverted = true;
    final boolean footer = false;

    final String ascii = "ASCII";

    when(flameVisualsService.getCustomAsciiInverted(filepath, width, height)).thenReturn(new AsciiImage(ascii, width));

    //When
    flameService.printFlame(filepath, width, height, inverted, footer);

    //Then
    final InOrder inOrder = inOrder(printService);

    inOrder.verify(printService, times(1)).printText(ascii);
    inOrder.verifyNoMoreInteractions();
  }

}
