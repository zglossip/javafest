package com.zglossip.javafest.exceptions;

public class ImageException extends RuntimeException{
  public ImageException() {
    super("There was an issue generating picture.");
  }
}
