package com.zglossip.javafest.util;

import com.zglossip.javafest.domain.ArgumentType;
import com.zglossip.javafest.exceptions.InvalidFlagException;
import com.zglossip.javafest.exceptions.NonNumberArgumentException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ArgumentUtil {

  public static Map<ArgumentType, Object> getArguments(final String[] args) {
    if (args.length == 0) {
      return Collections.emptyMap();
    }

    final Map<ArgumentType, Object> argumentMap = new HashMap<>();

    final Map<String, ArgumentType> flagMap = Arrays.stream(ArgumentType.values()).collect(Collectors.toMap(
            ArgumentType::getFlag, Function.identity()
    ));

    int position = 0;

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

  private static ArgumentType getArgumentTypeForFlag(final String flag, final Map<String, ArgumentType> flagMap) {
    if (!isFlagValid(flag)) {
      throw new InvalidFlagException(flag);
    }

    final ArgumentType argumentType = flagMap.get(String.valueOf(flag.charAt(1)).toLowerCase(Locale.ROOT));

    if (argumentType == null) {
      throw new InvalidFlagException(flag);
    }

    return argumentType;
  }

  private static Integer getIntFromValue(final String value) {
    try {
      return Integer.parseInt(value);
    } catch (final NumberFormatException e) {
      throw new NonNumberArgumentException();
    }
  }

  private static boolean isFlagValid(final String flag) {
    return flag.length() == 2 && flag.charAt(0) == '-';
  }

}
