package com.zglossip.javafest.exceptions;

public class NoEditorTypeException extends RuntimeException {
  public NoEditorTypeException() {
    super("No editor type found. Please enter either \"copy\" or \"flames\"");
  }
}
