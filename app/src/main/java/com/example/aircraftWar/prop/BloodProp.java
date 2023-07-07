package com.example.aircraftWar.prop;

import com.example.aircraftWar.aircraft.HeroAircraft;

/**
 * 血包道具
 * 由精英机被击毁后产生
 * 英雄机获得血包，血量hp增加
 *
 * @author hitsz200110211
 */
public class BloodProp extends AbstractProp {

    /**
     * 血包道具每次加血量
     */
    private int increase = 20;

    public BloodProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    /**
     * 血包道具效果：增加英雄机血量
     */
    public void effect(){
        HeroAircraft heroAircraft = HeroAircraft.getHeroAircraft();
        //增加完血量还未到达最大血量
        /**if (Main.soundSwitch == true) {
            new MusicThread("src/videos/get_supply.wav").start();
        }*/
        if (heroAircraft.getMaxHp() >= (increase+heroAircraft.getHp())) {
            heroAircraft.decreaseHp(-increase);
        }
        else {
            heroAircraft.decreaseHp(-(heroAircraft.getMaxHp()-heroAircraft.getHp()));
        }
        System.out.println("BloodSupply active!");
    }

}