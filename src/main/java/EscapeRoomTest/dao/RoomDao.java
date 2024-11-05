package EscapeRoomTest.dao;

import EscapeRoomTest.entities.Room;
import EscapeRoomTest.enums.Difficulty;
import EscapeRoomTest.util.IOHelper;
import EscapeRoomTest.util.MenuHelper;

public class RoomDao {
    private static final IOHelper IO_HELPER = new IOHelper();
    private static final DatabaseInputDao INPUT_DAO = new DatabaseInputDao();

    public Room createRoom(){
        String name = IO_HELPER.readString("Introduce the name of the room: ");
        Difficulty difficulty = MenuHelper.readDifficultySelection("Introduce the level of difficulty: ");
        INPUT_DAO.inputRoomIntoTable(name, difficulty);
        return new Room(name, difficulty);
    }
}