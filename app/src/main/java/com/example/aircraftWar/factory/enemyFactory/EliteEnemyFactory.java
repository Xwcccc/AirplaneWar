package com.example.aircraftWar.factory.enemyFactory;

import com.example.aircraftWar.activity.MainActivity;
import com.example.aircraftWar.aircraft.AbstractEnemyAircraft;
import com.example.aircraftWar.aircraft.EliteEnemy;
import com.example.aircraftWar.application.ImageManager;
import com.example.aircraftWar.strategy.ScatterShoot;

import java.util.Random;


/**
 * 具体的 EliteEnemy敌机工厂
 *
 * @author hitsz200110211
 */
public class EliteEnemyFactory implements EnemyFactory {

    /**
     * eliteEnemy的速度
     */
    private static int speedY;

    public static void setSpeedY (int speedY) {
        EliteEnemyFactory.speedY = speedY;
    }

    public static int getSpeedY() {
        return speedY;
    }
    /**
     * @return 创建的 EliteEnemy敌机
     */
    @Override
    public AbstractEnemyAircraft creatEnemyAircraft () {
        EliteEnemy eliteEnemy =  new EliteEnemy(
                (int) ( Math.random() * (MainActivity.screenWidth - ImageManager.ELITE_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * MainActivity.screenHeight * 0.2),
                (new Random().nextInt(3)-1)*6,
                speedY, 60, 1, 20, 1
        );
        eliteEnemy.setShootStrategy(new ScatterShoot());
        return eliteEnemy;
    }

}
