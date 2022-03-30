package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;

/**
 * @Xwc hitsz
 */
public class BossFactory implements EnemyFactory{
    @Override
    public AbstractAircraft creatEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        return null;
    }
}
