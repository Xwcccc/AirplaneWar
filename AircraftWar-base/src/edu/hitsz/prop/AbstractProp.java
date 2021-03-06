package edu.hitsz.prop;

import edu.hitsz.application.Main;
import edu.hitsz.basic.FlyingObject;

public abstract class AbstractProp extends FlyingObject {
    /**
     *
     * 移动方向
     */
    private int direction ;


    public AbstractProp(int locationX, int locationY, int speedX, int speedY, int direction) {
        super(locationX, locationY, speedX, speedY);
        this.direction = direction;
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 x 轴出界
        if (locationX <= 0 || locationX >= Main.WINDOW_WIDTH) {
            vanish();
        }

        // 判定 y 轴出界
        if (speedY > 0 && locationY >= Main.WINDOW_HEIGHT ) {
            // 向下飞行出界
            vanish();
        }else if (locationY <= 0){
            // 向上飞行出界
            vanish();
        }
    }

    public abstract int effect();
}
