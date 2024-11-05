package EscapeRoomTest.factories;

import EscapeRoomTest.entities.RoomHasElement;

public interface RoomHasElementFactory {
    RoomHasElement addElementToRoomHasElement(int room_id, int element_id);
}
