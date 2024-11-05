package EscapeRoomTest.entities;

import EscapeRoomTest.enums.Theme;

public class Clue extends Element{
    private int element_id;
    private Theme theme;
    private double est_time;

    public Clue(int element_id, String name, double price, int quantity, Theme theme, double est_time){
        super(name, price + (price * 0.10), quantity);
        this.element_id = element_id;
        this.theme = theme;
        this.est_time = est_time;
    }

    public int getElementId(){
        return this.element_id;
    }
    public Theme getTheme(){
        return  this.theme;
    }
    public double getEstTime(){
        return this.est_time;
    }
}
