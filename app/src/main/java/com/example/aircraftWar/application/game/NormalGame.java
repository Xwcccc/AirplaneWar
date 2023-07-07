package com.example.aircraftWar.application.game;

import android.content.Context;

import com.example.aircraftWar.aircraft.BossEnemy;
import com.example.aircraftWar.factory.enemyFactory.BossEnemyFactory;
import com.example.aircraftWar.factory.enemyFactory.EliteEnemyFactory;
import com.example.aircraftWar.factory.enemyFactory.EnemyFactory;
import com.example.aircraftWar.factory.enemyFactory.MobEnemyFactory;

/**
 * @author hitsz200110211
 */
public class NormalGame extends Game {

    public NormalGame(Context context) {
        super(context);
    }

    @Override
    void initializationDifficulty() {
        enemyMaxNumber = 5;
        eliteEnemyProbability = 0.25f;
        MobEnemyFactory.setSpeedY(7);
        EliteEnemyFactory.setSpeedY(7);
        bossScoreThreshold = 800;
        enemyShootDuration = 1200;
        BossEnemyFactory.setHp(210);
    }

    @Override
    void increaseDifficulty() {
        if (time>0 & time%20000==0 ) {
            enemyMaxNumber *= 1.1;
            if(enemyShootDuration > 300) {enemyShootDuration -= 20;}
            if(eliteEnemyProbability < 0.8) {eliteEnemyProbability += 0.03;}
            if(bossScoreThreshold > 200) {bossScoreThreshold -= 50;}
            MobEnemyFactory.setSpeedY(MobEnemyFactory.getSpeedY()+1);
            EliteEnemyFactory.setSpeedY(EliteEnemyFactory.getSpeedY()+1);
            System.out.printf("提高难度！敌机数量最大值：%.0f，" +
                            "敌机速度：%d，" +
                            "精英机产生概率：%.2f，" +
                            "产生一架boss机的分数阈值：%d，"+
                            "敌机射击周期：%.2f\n"
                    ,enemyMaxNumber
                    ,MobEnemyFactory.getSpeedY()
                    ,eliteEnemyProbability
                    ,bossScoreThreshold
                    ,(float)enemyShootDuration/1000);
        }
    }

    @Override
    EnemyFactory createBoss () {
        EnemyFactory enemyFactory = new BossEnemyFactory();
        BossEnemy.setBossExist(true);
        return enemyFactory;
    }

}
