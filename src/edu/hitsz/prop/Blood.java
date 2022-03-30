package edu.hitsz.prop;

public class Blood extends AbstractProp{
    private final int power = 10;

    public Blood(int locationX, int locationY, int speedX, int speedY, int direction) {
        super(locationX, locationY, speedX, speedY, direction);
    }

    @Override
    public int effect() {
        return power;
    }
}
