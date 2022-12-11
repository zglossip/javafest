package com.zglossip.javafest.exceptions;

public class InvalidFlagException extends RuntimeException {
  public InvalidFlagException(final String flag) {
    super("Flag \"" + flag + "\" invalid");
  }
}
