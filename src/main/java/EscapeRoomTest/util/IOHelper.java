package EscapeRoomTest.util;

import EscapeRoomTest.exceptions.NonAlphabeticInputException;
import EscapeRoomTest.inputChecker.InputTestReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class IOHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(IOHelper.class);
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final InputTestReader INPUT_TEST_READER = new InputTestReader();

    public double readDouble(String message){
        double num = 0;
        boolean isDouble = false;
        do {
            try {
                System.out.println(message);
                num = SCANNER.nextInt();
                isDouble = true;
            } catch (NumberFormatException e){
                System.out.println("Invalid number " + e.getMessage());
                LOGGER.warn("Invalid number: {}", e.getMessage());
            }
        } while(!isDouble);
        return num;
    }

    public int readInt(String message){
        int num = 0;
        boolean isInt = false;
        do{
            try {
                System.out.println(message);
                num = SCANNER.nextInt();
                isInt = true;
            } catch (NumberFormatException e){
                System.out.println("Wrong Input. Introduce a valid number: " + e.getMessage());
                LOGGER.warn("Wrong Input. Introduce a valid number: {}", e.getMessage());
            }
        }while (!isInt);
        return num;
    }

    public String readString(String message){
        String str = "";
        boolean isString = false;
        do{
            try {
                System.out.println(message);
                str = INPUT_TEST_READER.readStringInput();
                isString = true;
            } catch (NonAlphabeticInputException e){
                System.out.println("Wrong Input. Introduce a valid number: " + e.getMessage());
                LOGGER.warn("Wrong Input. Introduce a valid number: {}", e.getMessage());
            }
        } while (!isString);
        return str;
    }
}
