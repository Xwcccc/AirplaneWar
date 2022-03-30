package edu.hitsz.factory;

import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.Fire;

/**
 * @Xwc hitsz
 */
public class FireFactory implements PropFactory{
    @Override
    public AbstractProp creatProp(int locationX, int locationY, int speedX, int speedY, int direction) {
        return new Fire(locationX,locationY,speedX,speedY,direction);
    }
}
