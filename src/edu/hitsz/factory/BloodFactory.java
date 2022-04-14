package edu.hitsz.factory;

import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.Blood;

/**
 * @author xwc
 */
public class BloodFactory implements PropFactory{
    @Override
    public AbstractProp creatProp(int locationX, int locationY, int speedX, int speedY, int direction) {
        return new Blood(locationX,locationY,speedX,speedY,direction);
    }
}
