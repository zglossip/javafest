package com.zglossip.javafest.service;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;

@SpringBootTest
public class JavafestServiceSpec {

  FlameService flameService = Mockito.mock(FlameService.class);

  JavafestService javafestService = new JavafestService(flameService);

  @Test
  public void testExec() {
    //Given
    final String filepath = "test.com";
    final Integer width = 100;
    final Integer height = 101;
    final boolean invert = true;

    //When
    javafestService.exec(filepath, width, height, invert);

    //Then
    final InOrder inOrder = inOrder(flameService);

    inOrder.verify(flameService, times(1)).printFlame(filepath, width, height, invert);
  }

}
