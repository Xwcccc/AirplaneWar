package com.example.aircraftWar.factory.propFactory;

import com.example.aircraftWar.prop.AbstractProp;
import com.example.aircraftWar.prop.BulletProp;

/**
 * 具体的 BulletProp 子弹道具工厂
 *
 * @author hitsz200110211
 */
public class BulletPropFactory implements PropFactory {
    /**
     * @param locationX 子弹道具位置x坐标
     * @param locationY 子弹道具位置y坐标
     * @param speedX 子弹道具x轴速度
     * @param speedY 子弹道具y轴速度
     * @return 创建的 BulletProp子弹道具
     */
    @Override
    public AbstractProp creatProp (int locationX, int locationY, int speedX, int speedY) {
        return new BulletProp(locationX, locationY, speedX, speedY);
    }
}
