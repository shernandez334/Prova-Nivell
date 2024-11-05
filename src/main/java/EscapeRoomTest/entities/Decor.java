package EscapeRoomTest.entities;

import EscapeRoomTest.enums.Material;

public class Decor extends Element{
    private int element_id;
    private Material material;

    public Decor(int element_id, String name, double price, int quantity, Material material){
        super(name, price + (price * 0.21), quantity);
        this.element_id = element_id;
        this.material = material;
    }

    public int getElementId(){
        return this.element_id;
    }
    public Material getMaterial(){
        return this.material;
    }
}
