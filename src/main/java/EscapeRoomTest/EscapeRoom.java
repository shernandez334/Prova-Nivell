package EscapeRoomTest;

import EscapeRoomTest.menus.RoomManagerMenu;
import EscapeRoomTest.sql.DatabaseConnector;
import EscapeRoomTest.sql.DatabaseInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EscapeRoom {
    private String name;
    private static RoomManagerMenu roomManagerMenu = new RoomManagerMenu();
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConnector.class);

    public EscapeRoom(String name){
        this.name = name;
    }

    public void execute(){
        DatabaseInitializer.createDatabaseIfAbsent("escape_room_test");
        roomManagerMenu.manageEscapeRoomMenu();
    }
}