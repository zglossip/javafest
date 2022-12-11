package com.zglossip.javafest.util;

import com.zglossip.javafest.domain.ArgumentType;
import com.zglossip.javafest.exceptions.InvalidFlagException;
import com.zglossip.javafest.exceptions.NonNumberArgumentException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ArgumentUtilSpec {

  @Test
  public void testWidthArgument() {
    //Given
    final String[] args = new String[2];
    args[0] = "-w";
    args[1] = "100";

    final Map<ArgumentType, Object> expected = new HashMap<>();
    expected.put(ArgumentType.WIDTH, 100);

    //When
    final Map<ArgumentType, Object> result = ArgumentUtil.getArguments(args);

    //Then
    assert result.equals(expected);
  }

  @Test
  public void testHeightArgument() {
    //Given
    final String[] args = new String[2];
    args[0] = "-h";
    args[1] = "100";

    final Map<ArgumentType, Object> expected = new HashMap<>();
    expected.put(ArgumentType.HEIGHT, 100);

    //When
    final Map<ArgumentType, Object> result = ArgumentUtil.getArguments(args);

    //Then
    assert result.equals(expected);
  }

  @Test
  public void testFileArgument() {
    //Given
    final String[] args = new String[2];
    args[0] = "-p";
    args[1] = "file.jpg";

    final Map<ArgumentType, Object> expected = new HashMap<>();
    expected.put(ArgumentType.FILE, "file.jpg");

    //When
    final Map<ArgumentType, Object> result = ArgumentUtil.getArguments(args);

    //Then
    assert result.equals(expected);
  }

  @Test
  public void testMultipleArguments() {
    //Given
    final String[] args = new String[4];
    args[0] = "-h";
    args[1] = "100";
    args[2] = "-w";
    args[3] = "200";

    final Map<ArgumentType, Object> expected = new HashMap<>();
    expected.put(ArgumentType.HEIGHT, 100);
    expected.put(ArgumentType.WIDTH, 200);

    //When
    final Map<ArgumentType, Object> result = ArgumentUtil.getArguments(args);

    //Then
    assert result.equals(expected);
  }

  @Test
  public void testNoArguments() {
    //Given
    final String[] args = new String[0];

    final Map<ArgumentType, Object> expected = new HashMap<>();

    //When
    final Map<ArgumentType, Object> result = ArgumentUtil.getArguments(args);

    //Then
    assert result.equals(expected);
  }

  @Test
  public void testFailsOnBadFlag() {
    //Given
    final String[] args = new String[2];
    args[0] = "h";
    args[1] = "100";

    //When
    try {
      ArgumentUtil.getArguments(args);
    } catch (final InvalidFlagException e) {
      //Then
      return;
    }

    //Or exception wasn't thrown
    assert false;
  }

  @Test
  public void testFailsOnNonExistentFlag() {
    //Given
    final String[] args = new String[2];
    args[0] = "-0";
    args[1] = "100";

    //When
    try {
      ArgumentUtil.getArguments(args);
    } catch (final InvalidFlagException e) {
      //Then
      return;
    }

    //Or exception wasn't thrown
    assert false;
  }

  @Test
  public void testFailsOnBadWidthValue() {
    //Given
    final String[] args = new String[2];
    args[0] = "-w";
    args[1] = "a";

    //When
    try {
      ArgumentUtil.getArguments(args);
    } catch (final NonNumberArgumentException e) {
      //Then
      return;
    }

    //Or exception wasn't thrown
    assert false;
  }

  @Test
  public void testFailsOnBadHeightValue() {
    //Given
    final String[] args = new String[2];
    args[0] = "-h";
    args[1] = "a";

    //When
    try {
      ArgumentUtil.getArguments(args);
    } catch (final NonNumberArgumentException e) {
      //Then
      return;
    }

    //Or exception wasn't thrown
    assert false;
  }
}
