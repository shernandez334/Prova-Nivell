package EscapeRoomTest.dao;

import EscapeRoomTest.enums.RoomStatus;
import EscapeRoomTest.factories.StockManagerFactory;

public class StockManagerDao implements StockManagerFactory {
    private static final DatabaseInputDao DATABASE_INPUT_DAO = new DatabaseInputDao();

    @Override
    public void addRoomHasElementIntoStockManager(int room_has_element_id, RoomStatus roomStatus){
        DATABASE_INPUT_DAO.inputRoomHasElementIntoStock(room_has_element_id, roomStatus);
    }
}
