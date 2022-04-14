package edu.hitsz.factory;

import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.Boom;

/**
 * @author xwc
 */
public class BoomFactory implements PropFactory{
    @Override
    public AbstractProp creatProp(int locationX, int locationY, int speedX, int speedY, int direction) {
        return new Boom(locationX,locationY,speedX,speedY,direction);
    }
}
