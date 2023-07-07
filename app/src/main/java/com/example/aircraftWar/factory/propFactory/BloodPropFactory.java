package com.example.aircraftWar.factory.propFactory;

import com.example.aircraftWar.prop.AbstractProp;
import com.example.aircraftWar.prop.BloodProp;

/**
 * 具体的 BloodProp 血包道具工厂
 *
 * @author hitsz200110211
 */
public class BloodPropFactory implements PropFactory {
    /**
     * @param locationX 血包道具位置x坐标
     * @param locationY 血包道具位置y坐标
     * @param speedX 血包道具x轴速度
     * @param speedY 血包道具y轴速度
     * @return 创建的 BloodProp 血包道具
     */
    @Override
    public AbstractProp creatProp (int locationX, int locationY, int speedX, int speedY) {
        return new BloodProp(locationX, locationY, speedX, speedY);
    }
}

