package EscapeRoomTest.menus;

import EscapeRoomTest.sql.Display;
import EscapeRoomTest.util.IOHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoomManagerMenu {
    private static final IOHelper IO_HELPER = new IOHelper();
    private static final CreateMenu CREATE_MENU = new CreateMenu();
    private static final DeleteMenu DELETE_MENU = new DeleteMenu();
    private static final Display DISPLAY = new Display();
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomManagerMenu.class);

    public void manageEscapeRoomMenu(){
        int op;
        do {
            op = IO_HELPER.readInt("""
                    Type one of the following options:
                    1. Create Room
                    2. Remove Rooms
                    3. Show Inventory
                    4. Exit""");
            switch (op){
                case 1 -> CREATE_MENU.createElementMenu();
                case 2 -> DELETE_MENU.deleteElementMenu();
                case 3 -> DISPLAY.displayInventory();
                case 4 -> LOGGER.info("You have exited the escape room.");
                default -> LOGGER.warn("Not an Available option.");
            }
        } while (op != 4);
    }
}