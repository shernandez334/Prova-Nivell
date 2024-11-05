package EscapeRoomTest.factories;

import EscapeRoomTest.enums.*;

public interface DatabaseInputFactory {
    void inputElementIntoTable(String name, double price, int quantity, Type type);
    void inputClueIntoTable(int element_id, Theme theme, double est_time);
    void inputDecorIntoTable(int element_id, Material material);
    void inputRoomIntoTable(String name, Difficulty difficulty);
    void inputElementIntoRoomHasElement(int room_id, int element_id);
    void inputRoomHasElementIntoStock(int roomHasElementId, RoomStatus roomStatus);
}