package com.example.aircraftWar.strategy;

import com.example.aircraftWar.bullet.BaseBullet;
import com.example.aircraftWar.bullet.EnemyBullet;
import com.example.aircraftWar.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * @author hitsz200110211
 */
public class ScatterShoot implements ShootStrategy {
    @Override
    public List<BaseBullet> execute (int locationX, int locationY, int speedY, int shootNum, int power, int direction) {
        List<BaseBullet> res = new LinkedList<>();
        BaseBullet basebullet;
        int x;
        int y = locationY + direction*2;
        int sX;
        int sY = speedY + direction*5;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            x = locationX + (i*2 - shootNum + 1)*10;
            sX = i*2 - shootNum + 1;
            if (direction == -1) {
                basebullet = new HeroBullet(x, y, sX, sY, power);
            } else {
                basebullet = new EnemyBullet(x, y, sX, sY, power);
            }
            res.add(basebullet);
        }
        return res;
    }
}
