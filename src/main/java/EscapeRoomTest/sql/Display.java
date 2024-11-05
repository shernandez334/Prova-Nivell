package EscapeRoomTest.sql;

import EscapeRoomTest.util.IOHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Display {
    private static final IOHelper IO_HELPER = new IOHelper();
    private static final Logger LOGGER = LoggerFactory.getLogger(Display.class);

    public void DisplayRooms(){
        String displayRoomsQuery = SqlQueries.allRoomsQuery();
        try (Connection connection = DatabaseConnector.getInstance().getConnection("escape_room_test");
             PreparedStatement pstmt = connection.prepareStatement(displayRoomsQuery);
             ResultSet rs = pstmt.executeQuery()){
            boolean hasActiveRooms = false;
            while (rs.next()) {
                int roomId = rs.getInt("room_id");
                String roomName = rs.getString("room_name");
                String difficulty = rs.getString("difficulty");
                System.out.printf("Room ID: %d, Name: %s, Difficulty: %s%n", roomId, roomName, difficulty);
            }
            if (!hasActiveRooms) {
                System.out.println("There are no active rooms.");
                System.exit(0);
            }
        } catch (SQLException e) {
            LOGGER.warn("Error retrieving rooms: {}", e.getMessage());
        }
    }

    public void displayElements(int room_id){
        String displayElementsQuery = SqlQueries.allElementsQuery();
        try (Connection connection = DatabaseConnector.getInstance().getConnection("escape_room_test");
             PreparedStatement pstmt = connection.prepareStatement(displayElementsQuery)){
            pstmt.setInt(1, room_id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int element_id = rs.getInt("element_id");
                String elemName = rs.getString("element_name");
                String type = rs.getString("element_type");
                int elemQuantity = rs.getInt("element_quantity");
                System.out.printf("Element_ID: %d, Name: %s, Type: %s, Quantity: %d%n", element_id, elemName, type, elemQuantity);
            }
        } catch (SQLException e) {
            LOGGER.warn("Error retrieving elements: {}", e.getMessage());
        }
    }

    public void displayInventory(){
        DisplayRooms();
        int room_id = IO_HELPER.readInt("Please enter the room for which you'd like to view the elements.");
        displayElements(room_id);
    }
}