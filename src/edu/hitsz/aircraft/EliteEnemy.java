package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.prop.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 精英敌机
 * 可射击
 *
 * @author xwcccc
 */
public class EliteEnemy extends AbstractAircraft {
    /** 攻击方式 */
    private final int shootNum2 = 1;     //子弹一次发射数量
    private final int power2 = 20;       //子弹伤害
    private final int direction2 = 1;  //子弹射击方向 (向下发射：1，向上发射：-1)

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
    public List<AbstractBullet> shoot() {
        List<AbstractBullet> res2 = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction2*2;
        int speedX = 0;
        int speedY = this.getSpeedY() + direction2*5;
        AbstractBullet abstractBullet;
        for(int i=0; i<shootNum2; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            abstractBullet = new HeroBullet(x + (i*2 - shootNum2 + 1)*10, y, speedX, speedY, power2);
            res2.add(abstractBullet);
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

        if(r % 7 == 0) {
            return new Blood(x, y, speedX, speedY, 1, 20);
        }
        else if(r % 6 == 0){
            return new Boom(x, y, speedX, speedY, 1);
        }
        else if(r % 3 == 0){
            return new Fire(x, y, speedX, speedY, 1);
        }
        else{
            return null;
        }
    }
}
