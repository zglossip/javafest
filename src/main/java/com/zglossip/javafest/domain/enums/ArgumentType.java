package com.zglossip.javafest.domain.enums;

public enum ArgumentType {
  WIDTH("w"), HEIGHT("h"), FILE("p"), INVERTED("i"), COPY("c"), FLAMES("f"), EDITOR_TYPE(null);

  private final String flag;

  ArgumentType(final String flag) {
    this.flag = flag;
  }

  public String getFlag() {
    return flag;
  }
}
