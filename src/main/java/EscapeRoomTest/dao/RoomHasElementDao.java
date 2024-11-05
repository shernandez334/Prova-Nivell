package EscapeRoomTest.dao;

import EscapeRoomTest.entities.RoomHasElement;
import EscapeRoomTest.factories.RoomHasElementFactory;

public class RoomHasElementDao implements RoomHasElementFactory {
    private static final DatabaseInputDao INPUT_DAO = new DatabaseInputDao();

    public RoomHasElement addElementToRoomHasElement(int room_id, int element_id){
        INPUT_DAO.inputElementIntoRoomHasElement(room_id, element_id);
        return new RoomHasElement(room_id, element_id);
    }
}