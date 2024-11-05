package EscapeRoomTest.entities;

import EscapeRoomTest.enums.Difficulty;
import EscapeRoomTest.menus.RoomManagerMenu;
import EscapeRoomTest.sql.DatabaseConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private int room_id;
    private String name;
    private Difficulty difficulty;
    private List<Element> elements = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(Room.class);

    public Room(String name, Difficulty difficulty){
        this.room_id = getCurrentRoomId();
        this.name = name;
        this.difficulty = difficulty;
        this.elements = new ArrayList<>();
    }

    public int getRoomId(){
        return this.room_id;
    }

    public String getName(){
        return this.name;
    }

    public Difficulty getDifficulty(){
        return this.difficulty;
    }

    public int getCurrentRoomId(){
        int currentRoomId = 0;
        String sql = "SELECT room_id FROM room ORDER BY room_id DESC LIMIT 1";
        DatabaseConnector connector = DatabaseConnector.getInstance();
        try (Connection conn = connector.getConnection("escape_room_test");
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            rs.next();
            currentRoomId = rs.getInt("room_id");
        } catch (SQLException e) {
            LOGGER.error("Couldn't get the next room_id number: {}", e.getMessage());
        }
        return currentRoomId;
    }
}