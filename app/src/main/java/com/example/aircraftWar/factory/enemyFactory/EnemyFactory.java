package com.example.aircraftWar.factory.enemyFactory;

import com.example.aircraftWar.aircraft.AbstractEnemyAircraft;

/**
 * 抽象层创建者--敌机工厂
 * 为三种具体的敌机工厂(Mob, Elite, Boss)提供接口
 *
 * @author hitsz200110211
 */
public interface EnemyFactory {

    /**
     * 抽象敌机工厂接口
     * @return 创建的 AbstractAircraft敌机
     */
    AbstractEnemyAircraft creatEnemyAircraft();

}