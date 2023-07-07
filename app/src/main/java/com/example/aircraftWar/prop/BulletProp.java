package com.example.aircraftWar.prop;

import com.example.aircraftWar.aircraft.HeroAircraft;
import com.example.aircraftWar.strategy.*;

/**
 * 子弹道具
 * 由精英机被击毁后产生
 * 英雄机获得子弹，则英雄机每次发射的子弹数加1
 *
 * @author hitsz200110211
 */
public class BulletProp extends AbstractProp {

    /**
     * maxBulletNumber 英雄机一次性可发射的最多子弹数
     */
    private int maxBulletNumber = 3;

    public BulletProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    /**
     * 火力道具效果：由直射变成散射并维持一段时间（后恢复直射）
     */
    public void effect(){
        HeroAircraft heroAircraft = HeroAircraft.getHeroAircraft();
        Runnable r = ()-> {
           /** if (Main.soundSwitch) {
                new MusicThread("src/videos/bullet.wav").start();
            }
            */
            heroAircraft.setShootNum(3);
            heroAircraft.setShootStrategy(new ScatterShoot());
            System.out.println("BulletSupply active!");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            heroAircraft.setShootNum(1);
            heroAircraft.setShootStrategy(new DirectShoot());
        };
        new Thread(r).start();
    }

}
