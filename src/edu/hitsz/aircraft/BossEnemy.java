package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.prop.AbstractProp;

import java.util.List;

/**
 * @author xwc
 */
public class BossEnemy extends AbstractAircraft {
    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public AbstractProp prop() {
        return null;
    }

    @Override
    public List<BaseBullet> shoot() {
        return null;
    }
}
