package com.example.aircraftWar.factory.propFactory;

import com.example.aircraftWar.prop.AbstractProp;
import com.example.aircraftWar.prop.BombProp;

/**
 * 具体的 BombProp 炸弹道具工厂
 *
 * @author hitsz200110211
 */
public class BombPropFactory  implements PropFactory {
    /**
     * @param locationX 炸弹道具位置x坐标
     * @param locationY 炸弹道具位置y坐标
     * @param speedX 炸弹道具x轴速度
     * @param speedY 炸弹道具y轴速度
     * @return 创建的 BombProp 炸弹道具
     */
    @Override
    public AbstractProp creatProp (int locationX, int locationY, int speedX, int speedY) {
        return new BombProp(locationX, locationY, speedX, speedY);
    }
}