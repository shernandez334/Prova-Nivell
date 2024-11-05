package EscapeRoomTest.util;

import EscapeRoomTest.Main;
import EscapeRoomTest.enums.Difficulty;
import EscapeRoomTest.enums.Material;
import EscapeRoomTest.enums.Theme;

import java.util.Arrays;

public class MenuHelper {
    private static final String[] DIFFICULTY_OPTIONS = {"1.EASY", "2.MEDIUM", "3.HARD"};
    private static final String[] THEME_OPTIONS = {"1. SPACE", "2. COWBOY", "3. MEDIEVAL"};
    private static final String[] MATERIAL_OPTIONS = {"1. METAL", "2. WOOD", "3. PLASTIC", "4. CRISTAL"};
    private static final IOHelper IO_HELPER = new IOHelper();

    public static int readSelection(String heading, String closing, String ... options){
        int selection;
        do {
            System.out.println(heading);
            Arrays.stream(options).forEach(System.out::println);
            selection = IO_HELPER.readInt(closing);
        }while (selection <= 0 || selection > options.length);
        return selection;
    }

    public static Difficulty readDifficultySelection(String message) {
        int op = readSelection(message, ">", DIFFICULTY_OPTIONS);
        return Difficulty.values()[op - 1];
    }

    public static Theme readThemeSelection(String message){
        int op = readSelection(message, ">", THEME_OPTIONS);
        return Theme.values()[op - 1];
    }

    public static Material readMaterialSelection(String message){
        int op = readSelection(message, ">", MATERIAL_OPTIONS);
        return Material.values()[op - 1];
    }
}