package com.zglossip.javafest.domain;

public enum ArgumentType {
  WIDTH("w"), HEIGHT("h"), FILE("p");

  private final String flag;

  ArgumentType(final String flag) {
    this.flag = flag;
  }

  public String getFlag() {
    return flag;
  }
}
