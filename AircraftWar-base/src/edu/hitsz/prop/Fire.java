package edu.hitsz.prop;

public class Fire extends AbstractProp{

    public Fire(int locationX, int locationY, int speedX, int speedY, int direction) {
        super(locationX, locationY, speedX, speedY, direction);
    }

    @Override
    public int effect() {
        System.out.println("FireSupply active!");
        return 1;
    }
}
