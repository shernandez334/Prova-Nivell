package EscapeRoomTest.menus;

import EscapeRoomTest.dao.ElementDao;
import EscapeRoomTest.dao.RoomDao;
import EscapeRoomTest.dao.RoomHasElementDao;
import EscapeRoomTest.dao.StockManagerDao;
import EscapeRoomTest.entities.Clue;
import EscapeRoomTest.entities.Decor;
import EscapeRoomTest.entities.Room;
import EscapeRoomTest.entities.RoomHasElement;
import EscapeRoomTest.enums.RoomStatus;
import EscapeRoomTest.util.IOHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateMenu {
    private static final RoomDao ROOM_DAO = new RoomDao();
    private static final ElementDao ELEMENT_DAO = new ElementDao();
    private static final RoomHasElementDao ROOM_ELEMENT_DAO = new RoomHasElementDao();
    private static final StockManagerDao STOCK_DAO = new StockManagerDao();
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateMenu.class);
    private static final IOHelper IO_HELPER = new IOHelper();


    public void createElementMenu(){
        int op;
        boolean isElementCreated = false;
        Room room = ROOM_DAO.createRoom();
        do{
            op = IO_HELPER.readInt("""
                    Create at least one element:
                    1. Create Clue.
                    2. Create Decoration.
                    3. Exit.
                    """);
            boolean elementCreatedInThisIteration = handleElementMenu(room, op);
            if (elementCreatedInThisIteration){
                isElementCreated = true;
            }
        } while (op != 3 || !isElementCreated);
    }

    public boolean handleElementMenu(Room room, int op) {
        boolean isElementCreated = false;
        switch (op) {
            case 1 -> {
                Clue clue = ELEMENT_DAO.createClue();
                RoomHasElement roomElement = ROOM_ELEMENT_DAO.addElementToRoomHasElement(room.getRoomId(), clue.getElementId());
                STOCK_DAO.addRoomHasElementIntoStockManager(roomElement.getElementId(), RoomStatus.ACTIVE);
                isElementCreated = true;
            }
            case 2 -> {
                Decor decor = ELEMENT_DAO.createDecor();
                RoomHasElement roomElement = ROOM_ELEMENT_DAO.addElementToRoomHasElement(room.getRoomId(), decor.getElementId());
                STOCK_DAO.addRoomHasElementIntoStockManager(roomElement.getElementId(), RoomStatus.ACTIVE);
                isElementCreated = true;
            }
            case 3 -> System.out.println("Exiting...");
            default -> LOGGER.warn("Option out of bounds.");
        }
        return isElementCreated;
    }
}
