package com.example.aircraftWar.application.game;

import android.content.Context;

import com.example.aircraftWar.factory.enemyFactory.EliteEnemyFactory;
import com.example.aircraftWar.factory.enemyFactory.EnemyFactory;
import com.example.aircraftWar.factory.enemyFactory.MobEnemyFactory;

/**
 * @author hitsz200110211
 */
public class EasyGame extends Game {

    public EasyGame(Context context) {
        super(context);
    }

    @Override
    boolean increaseDifficultyHook () {
        return false;
    }

    @Override
    void initializationDifficulty() {
        enemyMaxNumber = 4;
        MobEnemyFactory.setSpeedY(6);
        EliteEnemyFactory.setSpeedY(6);
        eliteEnemyProbability = 0.2f;
        enemyShootDuration = 1800;
    }

    @Override
    void increaseDifficulty() {
        //空实现
    }

    @Override
    boolean creatBossHook () {
        return false;
    }

    @Override
    EnemyFactory createBoss() {
        return null;
    }

}
