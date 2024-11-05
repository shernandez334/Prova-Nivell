package EscapeRoomTest.dao;

import EscapeRoomTest.Main;
import EscapeRoomTest.entities.Clue;
import EscapeRoomTest.entities.Decor;
import EscapeRoomTest.entities.Element;
import EscapeRoomTest.entities.Room;
import EscapeRoomTest.enums.Material;
import EscapeRoomTest.enums.Theme;
import EscapeRoomTest.enums.Type;
import EscapeRoomTest.factories.ElementFactory;
import EscapeRoomTest.util.IOHelper;
import EscapeRoomTest.util.MenuHelper;

public class ElementDao implements ElementFactory {
    private static final IOHelper IO_HELPER = new IOHelper();
    private static final DatabaseInputDao INPUT_DAO = new DatabaseInputDao();

    @Override
    public Element createElement(Type type){
        String name = IO_HELPER.readString("Introduce the name of the " + type.name());
        double price = IO_HELPER.readDouble("Price of the " + type.name());
        int quantity = IO_HELPER.readInt("Quantity of the " + type.name());
        INPUT_DAO.inputElementIntoTable(name, price, quantity, type);
        return new Element(name, price, quantity);
    }

    @Override
    public Clue createClue(){
        Element elem = createElement(Type.CLUE);
        Theme theme = MenuHelper.readThemeSelection("Introduce theme: ");
        double est_time = IO_HELPER.readDouble("Estimated time in minutes: ");
        INPUT_DAO.inputClueIntoTable(elem.getElementId(), theme, est_time);
        return new Clue(elem.getElementId(), elem.getName(), elem.getPrice(), elem.getQuantity(), theme, est_time);
    }

    @Override
    public Decor createDecor(){
        Element elem = createElement(Type.DECOR);
        Material material = MenuHelper.readMaterialSelection("Introduce the material: ");
        INPUT_DAO.inputDecorIntoTable(elem.getElementId(), material);
        return new Decor(elem.getElementId(), elem.getName(), elem.getPrice(), elem.getQuantity(), material);
    }
}