package com.example.aircraftWar.factory.enemyFactory;

import com.example.aircraftWar.activity.MainActivity;
import com.example.aircraftWar.aircraft.AbstractEnemyAircraft;
import com.example.aircraftWar.aircraft.BossEnemy;
import com.example.aircraftWar.application.ImageManager;
import com.example.aircraftWar.strategy.ScatterShoot;

/**
 * 具体的 BossEnemy敌机工厂
 *
 * @author hitsz200110211
 */

public class BossEnemyFactory implements EnemyFactory {

    /**
     * boss的初始血量
     */
    private static int hp;

    /**
     * @return 创建的 BossEnemy敌机
     */
    @Override
    public AbstractEnemyAircraft creatEnemyAircraft () {

        BossEnemy bossEnemy = new BossEnemy(
                (int) ( Math.random() * (MainActivity.screenWidth - ImageManager.BOSS_ENEMY_IMAGE.getWidth()))*1,
                100,
                5,
                0,
                hp,
                3,
                20,
                1);
        bossEnemy.setShootStrategy(new ScatterShoot());
        return bossEnemy;
    }

    public static void setHp(int hp) {
        BossEnemyFactory.hp = hp;
    }

    public int getHp() {
        return hp;
    }
}
