package EscapeRoomTest.factories;

import EscapeRoomTest.enums.RoomStatus;

public interface StockManagerFactory {
    void addRoomHasElementIntoStockManager(int room_has_element_id, RoomStatus roomStatus);
}
