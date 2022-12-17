package com.zglossip.javafest.service;

import com.zglossip.javafest.domain.enums.ArgumentType;
import com.zglossip.javafest.domain.enums.EditorType;
import com.zglossip.javafest.exceptions.InvalidFlagException;
import com.zglossip.javafest.exceptions.NoEditorTypeException;
import com.zglossip.javafest.exceptions.NonNumberArgumentException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.zglossip.javafest.domain.enums.ArgumentType.EDITOR_TYPE;

@Service
public class ArgumentUtilService {

  public Map<ArgumentType, Object> getArguments(final String[] args) {
    if (args.length == 0) {
      throw new NoEditorTypeException();
    }

    final Map<ArgumentType, Object> argumentMap = new HashMap<>();

    argumentMap.put(EDITOR_TYPE, getEditorType(args[0]));

    final Map<String, ArgumentType> flagMap = Arrays.stream(ArgumentType.values()).collect(Collectors.toMap(
            ArgumentType::getFlag, Function.identity()
    ));

    int position = 1;

    while (position < args.length) {
      final ArgumentType argumentType = getArgumentTypeForFlag(args[position], flagMap);
      switch (argumentType) {
        case WIDTH, HEIGHT -> {
          position++;
          argumentMap.put(argumentType, getIntFromValue(args[position]));
        }
        case FILE -> {
          position++;
          argumentMap.put(argumentType, args[position]);
        }
        case INVERTED, COPY -> argumentMap.put(argumentType, true);
      }

      position++;
    }


    return argumentMap;
  }

  private EditorType getEditorType(final String arg) {
    try {
      return EditorType.valueOf(arg.toUpperCase());
    } catch (final IllegalArgumentException e) {
      throw new NoEditorTypeException();
    }
  }

  private ArgumentType getArgumentTypeForFlag(final String flag, final Map<String, ArgumentType> flagMap) {
    if (!isFlagValid(flag)) {
      throw new InvalidFlagException(flag);
    }

    final ArgumentType argumentType = flagMap.get(String.valueOf(flag.charAt(1)).toLowerCase(Locale.ROOT));

    if (argumentType == null) {
      throw new InvalidFlagException(flag);
    }

    return argumentType;
  }

  private Integer getIntFromValue(final String value) {
    try {
      return Integer.parseInt(value);
    } catch (final NumberFormatException e) {
      throw new NonNumberArgumentException();
    }
  }

  private boolean isFlagValid(final String flag) {
    return flag.length() == 2 && flag.charAt(0) == '-';
  }

}
