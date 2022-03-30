package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.prop.AbstractProp;

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

    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
    }

    public void decreaseHp(int decrease){
        hp -= decrease;
        if(hp <= 0){
            hp=0;
            vanish();
        }
    }

    public void increaseHp(int increase){
        hp += increase;
    }

    public int getHp() {
        return hp;
    }

    /**
     * 射出子弹
     * @return
     *  英雄机和精英机射出子弹
     *  不具有射击功能的，返回null
     */
    public abstract List<BaseBullet> shoot();

    /**
     * 飞机道具
     * @return
     *  可随意定义实现对象，返回道具
     *  不设置道具的，返回null
     */
    public abstract AbstractProp prop();

}


