package edu.hitsz.prop;

/**
 * @author xwc
 */
public class Blood extends AbstractProp{
    private final int power = 10;

    public Blood(int locationX, int locationY, int speedX, int speedY, int direction) {
        super(locationX, locationY, speedX, speedY, direction);
    }

    @Override
    public int effect() {
        System.out.println("Add power "+power);
        return power;
    }
}
