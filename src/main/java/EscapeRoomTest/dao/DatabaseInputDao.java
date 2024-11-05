package EscapeRoomTest.dao;

import EscapeRoomTest.enums.*;
import EscapeRoomTest.factories.DatabaseInputFactory;
import EscapeRoomTest.sql.DatabaseConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInputDao implements DatabaseInputFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseInputDao.class);

    @Override
    public void inputElementIntoTable(String name, double price, int quantity, Type type){
        String roomSqlQuery = "INSERT INTO element (name, price, quantity, type) VALUES (?, ?, ?, ?)";
        DatabaseConnector connector = DatabaseConnector.getInstance();
        try (Connection connection = connector.getConnection("escape_room_test");
             PreparedStatement pstmt = connection.prepareStatement(roomSqlQuery)){
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, quantity);
            pstmt.setString(4, type.name());
            pstmt.executeUpdate();
            LOGGER.info("Executed query: {} with element name: {}, price: {}, quantity: {}, type: {}", roomSqlQuery, name, price, quantity, type.name());
        } catch (SQLException e){
            LOGGER.warn("Error inputting values into the element table: {}", e.getMessage());
        }
    }

    @Override
    public void inputClueIntoTable(int element_id, Theme theme, double est_time){
        String roomSqlQuery = "INSERT INTO clue (element_id, theme, est_time) VALUES (?, ?, ?)";
        DatabaseConnector connector = DatabaseConnector.getInstance();
        try (Connection connection = connector.getConnection("escape_room_test");
             PreparedStatement pstmt = connection.prepareStatement(roomSqlQuery)){
            pstmt.setInt(1, element_id);
            pstmt.setString(2, theme.name());
            pstmt.setDouble(3, est_time);
            pstmt.executeUpdate();
            LOGGER.info("Executed query: {} with element_id: {}, theme: {}, est_time: {}", roomSqlQuery, element_id, theme.name(), est_time);
        } catch (SQLException e){
            LOGGER.warn("Error inputting values into the clue table: {}", e.getMessage());
        }
    }

    @Override
    public void inputDecorIntoTable(int element_id, Material material){
        String roomSqlQuery = "INSERT INTO decor (element_id, material) VALUES (?, ?)";
        DatabaseConnector connector = DatabaseConnector.getInstance();
        try (Connection connection = connector.getConnection("escape_room_test");
             PreparedStatement pstmt = connection.prepareStatement(roomSqlQuery)){
            pstmt.setInt(1, element_id);
            pstmt.setString(2, material.name());
            pstmt.executeUpdate();
            LOGGER.info("Executed query {} with decor element_id: {} and material: {}", roomSqlQuery, element_id, material.name());
        } catch (SQLException e){
            LOGGER.warn("Error inputting values into the material table: {}", e.getMessage());
        }
    }

    @Override
    public void inputRoomIntoTable(String name, Difficulty difficulty){
        String roomSqlQuery = "INSERT INTO room (name, difficulty) VALUES (?, ?)";
        DatabaseConnector connector = DatabaseConnector.getInstance();
        try (Connection connection = connector.getConnection("escape_room_test");
             PreparedStatement pstmt = connection.prepareStatement(roomSqlQuery)){
            pstmt.setString(1, name);
            pstmt.setString(2, difficulty.name());
            pstmt.executeUpdate();
            LOGGER.info("Executed query {} with room name: {} and difficulty: {}", roomSqlQuery, name, difficulty.name());
        } catch (SQLException e){
            LOGGER.warn("Error inputting values into the room table: {}", e.getMessage());
        }
    }

    @Override
    public void inputElementIntoRoomHasElement(int room_id, int element_id){
        String roomSqlQuery = "INSERT INTO room_has_element (room_id, element_id) VALUES (?, ?)";
        DatabaseConnector connector = DatabaseConnector.getInstance();
        try (Connection connection = connector.getConnection("escape_room_test");
             PreparedStatement pstmt = connection.prepareStatement(roomSqlQuery)){
            pstmt.setInt(1, room_id);
            pstmt.setInt(2, element_id);
            pstmt.executeUpdate();
            LOGGER.info("Executed query {} with room_id: {}, element_id: {}", roomSqlQuery, room_id, element_id);
        } catch (SQLException e){
            LOGGER.warn("Error inputting values into the room_has_element table: {}", e.getMessage());
        }
    }

    @Override
    public void inputRoomHasElementIntoStock(int roomHasElementId, RoomStatus roomStatus){
        String roomSqlQuery = "INSERT INTO stock_manager (room_has_element_id, status) VALUES (?, ?)";
        DatabaseConnector connector = DatabaseConnector.getInstance();
        try (Connection connection = connector.getConnection("escape_room_test");
             PreparedStatement pstmt = connection.prepareStatement(roomSqlQuery)){
            pstmt.setInt(1, roomHasElementId);
            pstmt.setString(2, roomStatus.name());
            pstmt.executeUpdate();
            LOGGER.info("Executed query {} with room_has_element_id: {}, room_status: {}", roomSqlQuery, roomHasElementId, roomStatus.name());
        } catch (SQLException e){
            LOGGER.warn("Error inputting values into the Stock_Manager table: {}", e.getMessage());
        }
    }
}