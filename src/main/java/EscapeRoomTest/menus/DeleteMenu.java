package EscapeRoomTest.menus;

import EscapeRoomTest.sql.DatabaseConnector;
import EscapeRoomTest.sql.Display;
import EscapeRoomTest.util.IOHelper;
import EscapeRoomTest.sql.SqlQueries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteMenu {
    public static final IOHelper IO_HELPER = new IOHelper();
    public static final Display DISPLAY = new Display();
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteMenu.class);

    public void deleteElementMenu(){
        DISPLAY.DisplayRooms();
        int roomDeleted = IO_HELPER.readInt("Type the ID of the room you wan to eliminate:");
        handleDeleteRoom(roomDeleted);
    }

    public void  handleDeleteRoom(int roomDeleted){
        String updateStatusQuery = SqlQueries.updateStatusQuery();
        try (Connection connection = DatabaseConnector.getInstance().getConnection("escape_room_test");
             PreparedStatement pstmt = connection.prepareStatement(updateStatusQuery)) {
            pstmt.execute("SET SQL_SAFE_UPDATES = 0;");
            pstmt.setInt(1, roomDeleted);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("Error updating status for room ID {}: {}", roomDeleted, e.getMessage());
        }
    }
}
