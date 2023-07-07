package com.example.aircraftWar.strategy;

import com.example.aircraftWar.bullet.BaseBullet;

import java.util.List;

/**
 * @author hitsz200110211
 */
public interface ShootStrategy {
    /**
     * 抽象射击策略接口
     * @param locationX  进行射击的飞机的X位置
     * @param locationY  飞机的Y位置
     * @param speedY     飞机的Y速度
     * @param shootNum   子弹一次性发射数量
     * @param power      子弹伤害
     * @param direction  子弹射击方向
     * @return 射击出的子弹List
     */
    List<BaseBullet> execute (int locationX, int locationY, int speedY, int shootNum, int power, int direction);
}
