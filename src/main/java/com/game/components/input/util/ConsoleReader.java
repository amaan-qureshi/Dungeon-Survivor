package com.game.components.input.util;

import java.io.Serializable;
import java.util.Scanner;
import java.util.function.Predicate;

import static java.util.Objects.nonNull;

class ConsoleReader implements Serializable{

    private static final Predicate<String> NON_BLANK_TEXT = text -> nonNull(text) && !text.isEmpty() && text.chars().noneMatch(Character::isWhitespace);
    private static final Predicate<String> NUMERIC_TEXT = text -> nonNull(text) && text.chars().allMatch(Character::isDigit);
    private static final Predicate<String> NON_NEGATIVE_TEXT = line -> Integer.parseInt(line) > 0;
    private static final Predicate<String> VALID_INPUT_DATA = NON_BLANK_TEXT.and(NUMERIC_TEXT).and(NON_NEGATIVE_TEXT);

    int readIntegerUntil(Predicate<String> userCondition, Runnable onFail) {
        Predicate<String> retryCondition = VALID_INPUT_DATA.and(userCondition).negate();
        String line = null;
        do {
            if (nonNull(line)) {
                onFail.run();
            }
            line = readString();
        } while (retryCondition.test(line));
        return Integer.parseInt(line);
    }

    private Scanner scanner() {
        return new Scanner(System.in, "UTF-8");
    }

    String readString() {
        return scanner().nextLine();
    }
}
