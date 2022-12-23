package com.zglossip.javafest.service;

public abstract class BaseEditorService {
  public abstract void printImage(final String filepath, final Integer width, final Integer height, final boolean invert, final boolean footer, final boolean twoColor);
}
