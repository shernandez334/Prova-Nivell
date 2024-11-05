package EscapeRoomTest.inputChecker;

import EscapeRoomTest.exceptions.NonAlphabeticInputException;

import java.util.Scanner;

public class InputTestReader {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String ALPHABETIC_PATTERN = "[a-zA-Z]+";

    public String readStringInput() throws NonAlphabeticInputException{
        String str = SCANNER.next();
        if (!str.matches(ALPHABETIC_PATTERN)) {
            throw new NonAlphabeticInputException("The system couldn't recognize the string.");
        }
        return str;
    }
}
