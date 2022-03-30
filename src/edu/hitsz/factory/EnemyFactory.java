package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;

/**
 * @Xwc hitsz
 */
public interface EnemyFactory {
    AbstractAircraft creatEnemy(int locationX, int locationY, int speedX, int speedY, int hp);
}
