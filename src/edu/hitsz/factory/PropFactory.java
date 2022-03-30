package edu.hitsz.factory;

import edu.hitsz.prop.AbstractProp;

/**
 * @Xwc hitsz
 */
public interface PropFactory {
     AbstractProp creatProp(int locationX, int locationY, int speedX, int speedY, int direction);
}
