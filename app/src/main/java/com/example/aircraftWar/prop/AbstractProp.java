package com.example.aircraftWar.prop;

import com.example.aircraftWar.activity.MainActivity;
import com.example.aircraftWar.basic.AbstractFlyingObject;

/**
 * 所有道具的抽象父类：
 * 血包，火力，炸弹道具
 *
 * @author hitsz
 */

public abstract class AbstractProp extends AbstractFlyingObject {

    public AbstractProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    public AbstractProp() {
        super();
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= MainActivity.screenHeight ) {
            vanish();
        }
    }

    /**
     * 道具生效,并在控制台打印相关语句
     */
    public abstract void effect();

}