package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.MobEnemy;

/**
 * @author xwc
 */
public class MobFactory implements EnemyFactory{
    @Override
    public AbstractAircraft creatEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        return new MobEnemy(locationX,locationY,speedX,speedY,hp);
    }
}
