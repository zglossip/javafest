package com.zglossip.javafest.service;

import com.zglossip.javafest.domain.enums.ArgumentType;
import com.zglossip.javafest.domain.enums.EditorType;
import com.zglossip.javafest.exceptions.InvalidFlagException;
import com.zglossip.javafest.exceptions.NoEditorTypeException;
import com.zglossip.javafest.exceptions.NonNumberArgumentException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ArgumentUtilServiceSpec {

  ArgumentUtilService argumentUtilService = new ArgumentUtilService();

  @Test
  public void testEditorTypeArgument() {
    //Given
    final String[] args = new String[1];
    args[0] = "copy";

    final Map<ArgumentType, Object> expected = new HashMap<>();
    expected.put(ArgumentType.EDITOR_TYPE, EditorType.COPY);

    //When
    final Map<ArgumentType, Object> result = argumentUtilService.getArguments(args);

    //Then
    assert result.equals(expected);
  }

  @Test
  public void testWidthArgument() {
    //Given
    final String[] args = new String[3];
    args[0] = "ascii";
    args[1] = "-w";
    args[2] = "100";

    final Map<ArgumentType, Object> expected = new HashMap<>();
    expected.put(ArgumentType.EDITOR_TYPE, EditorType.ASCII);
    expected.put(ArgumentType.WIDTH, 100);

    //When
    final Map<ArgumentType, Object> result = argumentUtilService.getArguments(args);

    //Then
    assert result.equals(expected);
  }

  @Test
  public void testHeightArgument() {
    //Given
    final String[] args = new String[3];
    args[0] = "ascii";
    args[1] = "-h";
    args[2] = "100";

    final Map<ArgumentType, Object> expected = new HashMap<>();
    expected.put(ArgumentType.EDITOR_TYPE, EditorType.ASCII);
    expected.put(ArgumentType.HEIGHT, 100);

    //When
    final Map<ArgumentType, Object> result = argumentUtilService.getArguments(args);

    //Then
    assert result.equals(expected);
  }

  @Test
  public void testFileArgument() {
    //Given
    final String[] args = new String[3];
    args[0] = "ascii";
    args[1] = "-p";
    args[2] = "file.jpg";

    final Map<ArgumentType, Object> expected = new HashMap<>();
    expected.put(ArgumentType.EDITOR_TYPE, EditorType.ASCII);
    expected.put(ArgumentType.FILE, "file.jpg");

    //When
    final Map<ArgumentType, Object> result = argumentUtilService.getArguments(args);

    //Then
    assert result.equals(expected);
  }

  @Test
  public void testCopyArgument() {
    //Given
    final String[] args = new String[2];
    args[0] = "ascii";
    args[1] = "-c";

    final Map<ArgumentType, Object> expected = new HashMap<>();
    expected.put(ArgumentType.EDITOR_TYPE, EditorType.ASCII);
    expected.put(ArgumentType.COPY, Boolean.TRUE);

    //When
    final Map<ArgumentType, Object> result = argumentUtilService.getArguments(args);

    //Then
    assert result.equals(expected);
  }

  @Test
  public void testFlameArgument() {
    //Given
    final String[] args = new String[2];
    args[0] = "ascii";
    args[1] = "-f";

    final Map<ArgumentType, Object> expected = new HashMap<>();
    expected.put(ArgumentType.EDITOR_TYPE, EditorType.ASCII);
    expected.put(ArgumentType.FLAMES, Boolean.TRUE);

    //When
    final Map<ArgumentType, Object> result = argumentUtilService.getArguments(args);

    //Then
    assert result.equals(expected);
  }

  @Test
  public void testTwoColorArgument() {
    //Given
    final String[] args = new String[2];
    args[0] = "ascii";
    args[1] = "-t";

    final Map<ArgumentType, Object> expected = new HashMap<>();
    expected.put(ArgumentType.EDITOR_TYPE, EditorType.ASCII);
    expected.put(ArgumentType.TWO_COLOR, Boolean.TRUE);

    //When
    final Map<ArgumentType, Object> result = argumentUtilService.getArguments(args);

    //Then
    assert result.equals(expected);
  }

  @Test
  public void testInvertedArgument() {
    //Given
    final String[] args = new String[2];
    args[0] = "ascii";
    args[1] = "-i";

    final Map<ArgumentType, Object> expected = new HashMap<>();
    expected.put(ArgumentType.EDITOR_TYPE, EditorType.ASCII);
    expected.put(ArgumentType.INVERTED, Boolean.TRUE);

    //When
    final Map<ArgumentType, Object> result = argumentUtilService.getArguments(args);

    //Then
    assert result.equals(expected);
  }

  @Test
  public void testMultipleArguments() {
    //Given
    final String[] args = new String[5];
    args[0] = "ascii";
    args[1] = "-h";
    args[2] = "100";
    args[3] = "-w";
    args[4] = "200";

    final Map<ArgumentType, Object> expected = new HashMap<>();
    expected.put(ArgumentType.EDITOR_TYPE, EditorType.ASCII);
    expected.put(ArgumentType.HEIGHT, 100);
    expected.put(ArgumentType.WIDTH, 200);

    //When
    final Map<ArgumentType, Object> result = argumentUtilService.getArguments(args);

    //Then
    assert result.equals(expected);
  }


  @Test
  public void testNoArguments() {
    //Given
    final String[] args = new String[1];
    args[0] = "ascii";

    final Map<ArgumentType, Object> expected = new HashMap<>();
    expected.put(ArgumentType.EDITOR_TYPE, EditorType.ASCII);

    //When
    final Map<ArgumentType, Object> result = argumentUtilService.getArguments(args);

    //Then
    assert result.equals(expected);
  }

  @Test
  public void testEmpty() {
    //Given
    final String[] args = new String[0];

    //When
    try {
      argumentUtilService.getArguments(args);
    } catch (final NoEditorTypeException e) {
      //Then
      return;
    }

    //Or exception wasn't thrown
    assert false;
  }

  @Test
  public void testBadEditorType() {
    //Given
    final String[] args = new String[1];
    args[0] = "badtype";

    //When
    try {
      argumentUtilService.getArguments(args);
    } catch (final NoEditorTypeException e) {
      //Then
      return;
    }

    //Or exception wasn't thrown
    assert false;
  }

  @Test
  public void testFailsOnBadFlag() {
    //Given
    final String[] args = new String[3];
    args[0] = "ascii";
    args[1] = "h";
    args[2] = "100";

    //When
    try {
      argumentUtilService.getArguments(args);
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
    final String[] args = new String[3];
    args[0] = "ascii";
    args[1] = "-0";
    args[2] = "100";

    //When
    try {
      argumentUtilService.getArguments(args);
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
    final String[] args = new String[3];
    args[0] = "ascii";
    args[1] = "-w";
    args[2] = "a";

    //When
    try {
      argumentUtilService.getArguments(args);
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
    final String[] args = new String[3];
    args[0] = "ascii";
    args[1] = "-h";
    args[2] = "a";

    //When
    try {
      argumentUtilService.getArguments(args);
    } catch (final NonNumberArgumentException e) {
      //Then
      return;
    }

    //Or exception wasn't thrown
    assert false;
  }
}
