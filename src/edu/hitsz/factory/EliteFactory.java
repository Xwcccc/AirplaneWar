package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EliteEnemy;

/**
 * @author xwc
 */
public class EliteFactory implements EnemyFactory{
    @Override
    public AbstractAircraft creatEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        return new EliteEnemy(locationX,locationY,speedX,speedY,hp);
    }
}
