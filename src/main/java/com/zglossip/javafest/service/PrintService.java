package com.zglossip.javafest.service;

import org.springframework.stereotype.Service;

@Service
public class PrintService {

  public void printText(final Object text) {
    System.out.println(text);
  }

}
