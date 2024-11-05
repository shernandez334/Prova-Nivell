package EscapeRoomTest.entities;

import EscapeRoomTest.sql.DatabaseConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Element {
    private int element_id;
    private String name;
    private double price;
    private int quantity;
    private static final Logger LOGGER = LoggerFactory.getLogger(Element.class);

    public Element(String name, double price, int quantity){
        element_id = getCurrentElementId();
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getElementId(){
        return this.element_id;
    }
    public String getName(){
        return  this.name;
    }
    public double getPrice(){
        return this.price;
    }
    public int getQuantity(){
        return  this.quantity;
    }

    public int getCurrentElementId(){
        int currentElementId = 0;
        String sql = "SELECT element_id FROM element ORDER BY element_id DESC LIMIT 1";
        DatabaseConnector connector = DatabaseConnector.getInstance();
        try (Connection conn = connector.getConnection("escape_room_test");
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            rs.next();
            currentElementId = rs.getInt("element_id");
        } catch (SQLException e) {
            LOGGER.error("Couldn't get the next element_id number: {}", e.getMessage());
        }
        return currentElementId;
    }
}