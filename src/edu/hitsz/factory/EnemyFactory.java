package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;

/**
 *
 * @author xwc
 */
public interface EnemyFactory {

 /**
 * 敌机工厂
 * @param locationX 接收的第一个参数，范围起点
 * @param locationY 接收的第二个参数，范围终点
 * @param speedX 水平方向速度
 * @param speedY 垂直方向速度
 * @param hp 飞机生命值
 * @return 产生的敌机
 */
    AbstractAircraft creatEnemy(int locationX, int locationY, int speedX, int speedY, int hp);
}
