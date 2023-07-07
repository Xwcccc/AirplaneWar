package com.example.aircraftWar.prop;

import com.example.aircraftWar.observer.AbstractSubscriber;

import java.util.LinkedList;
import java.util.List;

/**
 * 炸弹道具
 * 由精英机被击毁后产生
 * 英雄机获得炸弹，清空屏幕上所有敌机（Mob，Elite，Boss）
 *
 * @author hitsz200110211
 */
public class BombProp extends AbstractProp {

    /**
     * 观察者列表
     */
    private List<AbstractSubscriber> subscribers = new LinkedList<>();

    public BombProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    /**
     * 增加观察者
     */
    public void addSubscriber(AbstractSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * 删除观察者
     */
    public void removeSubscriber(AbstractSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    /**
     * 通知所有观察者
     */
    public void notifySubscribers() {
        for (AbstractSubscriber subscriber : subscribers) {
            subscriber.update();
        }
    }

    @Override
    /**
     * 炸弹道具效果：清空屏幕上所有敌机
     */
    public void effect(){
       /** if (Main.soundSwitch) {
            new MusicThread("src/videos/bomb_explosion.wav").start();
        }*/
        System.out.println("BombSupply active!");
        notifySubscribers();
    }

}