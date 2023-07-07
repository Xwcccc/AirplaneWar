package com.example.aircraftWar.factory.propFactory;

import com.example.aircraftWar.prop.AbstractProp;

/**
 * 抽象层创建者--道具工厂
 * 为三种具体的道具工厂（Blood, Bomb, Bullet）提供接口
 *
 * @author hitsz200110211
 */
public interface PropFactory {
    /**
     * 抽象道具工厂接口
     * @param locationX 道具位置x坐标
     * @param locationY 道具位置y坐标
     * @param speedX 道具x轴速度
     * @param speedY 道具y轴速度
     * @return 创建的 AbstractProp道具
     */
    AbstractProp creatProp(int locationX, int locationY, int speedX, int speedY);
}
