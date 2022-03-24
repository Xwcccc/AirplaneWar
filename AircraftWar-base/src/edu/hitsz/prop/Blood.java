package edu.hitsz.prop;

public class Blood extends AbstractProp{
    private int power;

    public Blood(int locationX, int locationY, int speedX, int speedY, int direction, int power) {
        super(locationX, locationY, speedX, speedY, direction);
        this.power = power;
    }

    @Override
    public int effect() {
        System.out.println("Blood appear!!!!");
        return power;
    }
}
