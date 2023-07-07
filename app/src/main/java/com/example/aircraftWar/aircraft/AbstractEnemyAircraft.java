package com.example.aircraftWar.aircraft;

import com.example.aircraftWar.activity.MainActivity;
import com.example.aircraftWar.application.game.Game;
import com.example.aircraftWar.observer.AbstractSubscriber;
import com.example.aircraftWar.prop.AbstractProp;

import java.util.List;

/**
 * @author hitsz200110211
 */
public abstract class AbstractEnemyAircraft extends AbstractAircraft implements AbstractSubscriber {

    public AbstractEnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    public AbstractEnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp, int shootNum, int power, int direction) {
        super(locationX, locationY, speedX, speedY, hp, shootNum, power, direction);
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
     *飞机及落后产生道具，可产生道具的对象必须实现
     * @return
     * 可产生道具的对象需实现，返回道具
     * 不可产生道具的对象空实现，返回null
     */
    public abstract List<AbstractProp> dropProp();

    @Override
    public void update () {
        if (isValid) {
            Game.setScore(Game.getScore() + 10);
            vanish();
        }
    }
}
