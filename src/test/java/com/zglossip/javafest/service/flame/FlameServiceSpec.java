package com.zglossip.javafest.service.flame;

import com.zglossip.javafest.base.TestBase;
import com.zglossip.javafest.domain.AsciiImage;
import com.zglossip.javafest.service.FlameService;
import com.zglossip.javafest.service.FlameVisualsService;
import com.zglossip.javafest.service.PrintService;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;

public class FlameServiceSpec extends TestBase {

  private final FlameVisualsService flameVisualsService = mock(FlameVisualsService.class);
  private final PrintService printService = mock(PrintService.class);

  FlameService flameService = new FlameService(flameVisualsService, printService);

  @Test
  public void testPrintImage() {
    //Given params
    final String filepath = "good_for_her.jpg";
    final Integer width = 200;
    final Integer height = 100;
    final boolean invert = false;
    final boolean footer = false;
    final boolean twoColor = false;

    //And mocks
    final String ascii = "ASCII STRING";
    when(flameVisualsService.getAsciiString(
            eq(filepath),
            eq(width),
            eq(height),
            eq(invert),
            eq(twoColor))
    ).thenReturn(new AsciiImage(ascii, width));

    //When
    flameService.printImage(filepath, width, height, invert, footer, twoColor);

    //Then
    final InOrder inOrder = inOrder(printService);

    inOrder.verify(printService, times(1)).printText(ascii);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  public void testPrintImageInvert() {
    //Given params
    final String filepath = "good_for_her.jpg";
    final Integer width = 200;
    final Integer height = 100;
    final boolean invert = true;
    final boolean footer = false;
    final boolean twoColor = false;

    //And mocks
    final String ascii = "ASCII STRING";
    when(flameVisualsService.getAsciiString(
            eq(filepath),
            eq(width),
            eq(height),
            eq(invert),
            eq(twoColor))
    ).thenReturn(new AsciiImage(ascii, width));

    //When
    flameService.printImage(filepath, width, height, invert, footer, twoColor);

    //Then
    final InOrder inOrder = inOrder(printService);

    inOrder.verify(printService, times(1)).printText(ascii);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  public void testPrintImageFooter() {
    //Given params
    final String filepath = "good_for_her.jpg";
    final Integer width = 200;
    final Integer height = 100;
    final boolean invert = false;
    final boolean footer = true;
    final boolean twoColor = false;

    //And mocks
    final String ascii = "ASCII STRING";
    when(flameVisualsService.getAsciiString(
            eq(filepath),
            eq(width),
            eq(height),
            eq(invert),
            eq(twoColor))
    ).thenReturn(new AsciiImage(ascii, width));

    final String footerText = "FOOTER";
    when(flameVisualsService.getFooter(width)).thenReturn(footerText);

    //When
    flameService.printImage(filepath, width, height, invert, footer, twoColor);

    //Then
    final InOrder inOrder = inOrder(printService);

    inOrder.verify(printService, times(1)).printText(ascii);
    inOrder.verify(printService, times(1)).printText(footerText);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  public void testPrintImageTwoColor() {
    //Given params
    final String filepath = "good_for_her.jpg";
    final Integer width = 200;
    final Integer height = 100;
    final boolean invert = false;
    final boolean footer = false;
    final boolean twoColor = true;

    //And mocks
    final String ascii = "ASCII STRING";
    when(flameVisualsService.getAsciiString(
            eq(filepath),
            eq(width),
            eq(height),
            eq(invert),
            eq(twoColor))
    ).thenReturn(new AsciiImage(ascii, width));

    //When
    flameService.printImage(filepath, width, height, invert, footer, twoColor);

    //Then
    final InOrder inOrder = inOrder(printService);

    inOrder.verify(printService, times(1)).printText(ascii);
    inOrder.verifyNoMoreInteractions();
  }

}
