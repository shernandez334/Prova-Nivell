package EscapeRoomTest.entities;

import EscapeRoomTest.sql.DatabaseConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomHasElement {
    private int roomHasElementId;
    private int room_id;
    private int element_id;
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomHasElement.class);

    public RoomHasElement(int room_id, int element_id){
        this.roomHasElementId = getCurrentRoomHasElementId();
        this.room_id = room_id;
        this.element_id = element_id;
    }

    public int getRoomHasElementId(){
        return this.roomHasElementId;
    }
    public int getRoomId(){
        return this.room_id;
    }
    public int getElementId() {
        return this.element_id;
    }

    public int getCurrentRoomHasElementId(){
        int currentElementId = 0;
        String sql = "SELECT room_has_element_id FROM room_has_element ORDER BY room_has_element_id DESC LIMIT 1";
        DatabaseConnector connector = DatabaseConnector.getInstance();
        try (Connection conn = connector.getConnection("escape_room_test");
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            rs.next();
            currentElementId = rs.getInt("room_has_element_id");
        } catch (SQLException e) {
            LOGGER.error("Couldn't get the next room_has_element_id number: {}", e.getMessage());
        }
        return currentElementId;
    }
}