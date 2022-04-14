package edu.hitsz.aircraft;

import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.factory.BloodFactory;
import edu.hitsz.factory.BoomFactory;
import edu.hitsz.factory.FireFactory;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.prop.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 精英敌机
 * 可射击
 *
 * @author xwc
 */
public class EliteEnemy extends AbstractAircraft {
    /** 攻击方式 */
    private final int shootNum2 = 1;
    private final int power2 = 20;
    private final int direction2 = 1;
    final int getRandom1 = 7;
    final int getRandom2 = 6;
    final int getRandom3 = 3;

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void forward() {
        super.forward();// 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    @Override
    public List<BaseBullet> shoot() {
        List<BaseBullet> res2 = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction2*2;
        int speedX = 0;
        int speedY = this.getSpeedY() + direction2*5;
        BaseBullet baseBullet;
        for(int i=0; i<shootNum2; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            baseBullet = new EnemyBullet(x + (i*2 - shootNum2 + 1)*10, y, speedX, speedY, power2);
            res2.add(baseBullet);
        }
        return res2;
    }

    @Override
    public AbstractProp prop(){
        Random ran = new Random();
        int r = ran.nextInt(50);
        int x = this.getLocationX();
        int y = this.getLocationY() + direction2*2;
        int speedX = 0;
        int speedY = this.getSpeedY() + direction2*3;

        if(r % getRandom1 == 0) {
            return new BloodFactory().creatProp(x, y, speedX, speedY, 1);
        }
        else if(r % getRandom2 == 0){
            return new BoomFactory().creatProp(x, y, speedX, speedY, 1);
        }
        else if(r % getRandom3 == 0){
            return new FireFactory().creatProp(x, y, speedX, speedY, 1);
        }
        else{
            return null;
        }
    }

}
