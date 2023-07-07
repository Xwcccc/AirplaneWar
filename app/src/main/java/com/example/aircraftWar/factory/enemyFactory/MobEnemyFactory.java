package com.example.aircraftWar.factory.enemyFactory;

import com.example.aircraftWar.activity.MainActivity;
import com.example.aircraftWar.aircraft.AbstractEnemyAircraft;
import com.example.aircraftWar.aircraft.MobEnemy;
import com.example.aircraftWar.application.ImageManager;

/**
 * 具体的 MobEnemy敌机工厂
 *
 * @author  hitsz200110211
 */
public class MobEnemyFactory implements EnemyFactory {

    /**
     * mobEnemy的速度
     */
    private static int speedY;

    public static void setSpeedY (int speedY) {
        MobEnemyFactory.speedY = speedY;
    }

    public static int getSpeedY() {
        return speedY;
    }

    /**
     * @return 创建的 MobEnemy敌机
     */
    @Override
    public AbstractEnemyAircraft creatEnemyAircraft () {
        return new MobEnemy(
                (int) ( Math.random() * (MainActivity.screenWidth - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * MainActivity.screenHeight * 0.2)*1,
                0,
                speedY,
                30);
    }

}
