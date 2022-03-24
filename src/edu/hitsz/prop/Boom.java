package edu.hitsz.prop;

public class Boom extends AbstractProp{

    public Boom(int locationX, int locationY, int speedX, int speedY, int direction) {
        super(locationX, locationY, speedX, speedY, direction);
    }

    @Override
    public int effect() {
        System.out.println("BombSupply active!");
        return 1;
    }
}
