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
public class HardGame extends Game {

    public HardGame(Context context) {
        super(context);
    }

    @Override
    void initializationDifficulty() {
        enemyMaxNumber = 5;
        eliteEnemyProbability = 0.25f;
        MobEnemyFactory.setSpeedY(8);
        EliteEnemyFactory.setSpeedY(8);
        bossScoreThreshold = 800;
        enemyShootDuration = 1200;
        BossEnemyFactory.setHp(240);
    }

    @Override
    void increaseDifficulty() {
        if (time>0 & time%10000==0 ) {
            enemyMaxNumber *= 1.1;
            if(enemyShootDuration > 400) {enemyShootDuration -= 20;}
            if(eliteEnemyProbability < 0.9) {eliteEnemyProbability += 0.03;}
            if(bossScoreThreshold > 200) {bossScoreThreshold -= 50;}
            MobEnemyFactory.setSpeedY(MobEnemyFactory.getSpeedY()+1);
            EliteEnemyFactory.setSpeedY(EliteEnemyFactory.getSpeedY()+1);
            System.out.printf("提高难度！敌机数量最大值：%.0f，" +
                            "敌机速度：%d，" +
                            "精英机产生概率：%.2f，" +
                            "产生一架boss机的分数阈值：%d，"+
                            "敌机射击周期：%.2fs\n"
                    ,enemyMaxNumber
                    , MobEnemyFactory.getSpeedY()
                    ,eliteEnemyProbability
                    ,bossScoreThreshold
                    ,(float)enemyShootDuration/1000);
        }
    }

    @Override
    EnemyFactory createBoss () {
        EnemyFactory enemyFactory = new BossEnemyFactory();
        int currentHp =((BossEnemyFactory) enemyFactory).getHp();
        System.out.println("boss机当前血量："+currentHp);
        BossEnemyFactory.setHp((int) (currentHp*1.2));
        System.out.println("下一架boss机血量提升至1.2倍");
        BossEnemy.setBossExist(true);
        return enemyFactory;
    }
}