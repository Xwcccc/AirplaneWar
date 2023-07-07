package com.example.aircraftWar.aircraft;

import com.example.aircraftWar.activity.MainActivity;
import com.example.aircraftWar.application.ImageManager;
import com.example.aircraftWar.bullet.BaseBullet;
import com.example.aircraftWar.strategy.DirectShoot;

import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {
    private volatile static HeroAircraft heroAircraft;

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp, int shootNum, int power, int direction) {
        super(locationX, locationY, speedX, speedY, hp, shootNum, power, direction);
    }

    /**
     * 共有的静态方法，用于实例化对象
     * @return HeroAircraft类唯一的实例
     */
    public static HeroAircraft getHeroAircraft() {
        if (heroAircraft == null) {
            synchronized (HeroAircraft.class) {
                if (heroAircraft == null) {
                    heroAircraft = new HeroAircraft(
                            MainActivity.screenWidth / 2,
                            MainActivity.screenHeight - ImageManager.HERO_IMAGE.getHeight() ,
                            0, 0, 1000, 1, 30, -1);
                    heroAircraft.setShootStrategy(new DirectShoot());
                }
            }
        }
        return heroAircraft;
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    @Override
    public List<BaseBullet> shoot() {
        return shootStrategy.execute(this.getLocationX(), this.getLocationY(), this.getSpeedY(), this.getShootNum(), this.getPower(), this.getDirection());
    }

}
