package com.zglossip.javafest.domain;

import org.springframework.lang.NonNull;

import java.util.Comparator;
import java.util.Objects;

public class AsciiImage implements Comparable<AsciiImage> {

  private static final Comparator<AsciiImage> comparator = Comparator.comparing(AsciiImage::getImage, Comparator.nullsLast(Comparator.naturalOrder()))
          .thenComparing(AsciiImage::getWidth, Comparator.naturalOrder());

  private String image;
  private int width;

  public AsciiImage(@NonNull final String image, final int width) {
    this.image = image;
    this.width = width;
  }

  public String getImage() {
    return image;
  }

  public void setImage(final String image) {
    this.image = image;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(final int width) {
    this.width = width;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final AsciiImage that = (AsciiImage) o;
    return width == that.width && image.equals(that.image);
  }

  @Override
  public int hashCode() {
    return Objects.hash(image, width);
  }

  @Override
  public String toString() {
    return "AsciiImage{" +
            "image='" + image + '\'' +
            ", width=" + width +
            '}';
  }


  @Override
  public int compareTo(final AsciiImage that) {
    return comparator.compare(this, that);
  }
}
