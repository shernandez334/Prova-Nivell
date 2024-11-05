package EscapeRoomTest.factories;

import EscapeRoomTest.entities.Clue;
import EscapeRoomTest.entities.Decor;
import EscapeRoomTest.entities.Element;
import EscapeRoomTest.enums.Type;

public interface ElementFactory {
    Element createElement(Type type);
    Clue createClue();
    Decor createDecor();
}
