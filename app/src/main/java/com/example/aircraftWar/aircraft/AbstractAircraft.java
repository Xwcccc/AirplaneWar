package com.example.aircraftWar.aircraft;

import com.example.aircraftWar.basic.AbstractFlyingObject;
import com.example.aircraftWar.bullet.BaseBullet;
import com.example.aircraftWar.strategy.ShootStrategy;

import java.util.List;

/**
 * 所有种类飞机的抽象父类：
 * 敌机（BOSS, ELITE, MOB），英雄飞机
 *
 * @author hitsz
 */
public abstract class AbstractAircraft extends AbstractFlyingObject {
    /**
     * 生命值
     */
    protected int maxHp;
    protected int hp;

    /**
     * 飞机的射击策略
     */
    protected ShootStrategy shootStrategy;

    /** 攻击方式
     * shootNum 子弹一次发射数量
     * power 子弹伤害
     * direction 子弹射击方向 (向上发射：-1，向下发射：1)
     * */
    protected int shootNum;
    protected int power;
    protected int direction;

    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
    }

    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp, int shootNum, int power, int direction) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
        this.shootNum = shootNum;
        this.power = power;
        this.direction = direction;
    }

    public void decreaseHp(int decrease){
        hp -= decrease;
        if(hp <= 0){
            hp=0;
            vanish();
        }
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getShootNum() {
        return shootNum;
    }

    public int getPower() {
        return power;
    }

    public int getDirection() {
        return direction;
    }

    public void setShootStrategy (ShootStrategy shootStrategy) {
        this.shootStrategy = shootStrategy;
    }

    public void setShootNum(int shootNum) {
        this.shootNum = shootNum;
    }

    /**
     * 飞机射击方法
     * @return
     * 可射击对象需实现，返回子弹
     * 非可射击对象空实现，返回null
     */
    public abstract List<BaseBullet> shoot();

}