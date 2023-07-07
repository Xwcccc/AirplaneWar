package com.example.aircraftWar.aircraft;

import com.example.aircraftWar.bullet.BaseBullet;
import com.example.aircraftWar.factory.propFactory.*;
import com.example.aircraftWar.prop.AbstractProp;

import java.util.LinkedList;
import java.util.List;

/**
 * Boss敌机
 *
 * @author hitsz
 */
public class BossEnemy extends AbstractEnemyAircraft {
    /**
     * bossExist 当前存在boss机则为true，否则为false
     */
    private static boolean bossExist = false;
/**
    public static MusicThread getBossMusic() {
        return bossMusic;
    }

    private static MusicThread bossMusic;
*/
    /**
     * propFactory 道具工厂
     */
    private PropFactory propFactory;

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp, int shootNum, int power, int direction) {
        super(locationX, locationY, speedX, speedY, hp, shootNum, power, direction);
        /**if (Main.soundSwitch) {
            Game.music.setStopMusic(true);
            bossMusic = new MusicThread("src/videos/bgm_boss.wav");
            bossMusic.setLoop(true);
            bossMusic.start();
        }*/
    }

    @Override
    public List<BaseBullet> shoot() {
        return shootStrategy.execute(this.getLocationX(), this.getLocationY(), this.getSpeedY(), this.getShootNum(), this.getPower(), this.getDirection());
    }

    @Override
    public List<AbstractProp> dropProp() {
        List<AbstractProp> props = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY();
        int speedX = 0;
        int speedY = 7;

        double random = Math.random();

        // 产生每种道具的概率为0.3（还有0.1的概率不产生道具）
        if (random < 0.1) {
            System.out.println("No supply generated!");
        } else {
            if (random < 0.4) {
                propFactory = new BloodPropFactory();
            } else if (random < 0.7) {
                propFactory = new BombPropFactory();
            } else {
                propFactory = new BulletPropFactory();
            }
            props.add(propFactory.creatProp(x, y, speedX, speedY));
        }
        return props;
    }

    @Override
    public void vanish() {
        isValid = false;
       /** if (Main.soundSwitch) {
            bossMusic.setStopMusic(true);
            Game.music = new MusicThread("src/videos/bgm.wav");
            Game.music.start();
        }
        */
    }

    public static boolean isBossExist() {
        return bossExist;
    }

    public static void setBossExist(boolean bossExist) {
        BossEnemy.bossExist = bossExist;
    }
}
