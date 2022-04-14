import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.EnemyBullet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class EnemyBulletTest {

    EnemyBullet enemyBullet;
    EliteEnemy eliteEnemy = new EliteEnemy(1,1,0,1,100);

    @BeforeEach
    void setUp() {
        System.out.println("**--  - Executed before each test method in this class  --**");
        enemyBullet = new EnemyBullet(1,1,0,1,10);
    }

    @AfterEach
    void tearDown() {
        System.out.println("**--- Executed after each test method in this class ---**");
        enemyBullet = null;
    }

    @Test
    void crash() {
        System.out.println("**--- Test crash method ---**");
        assertTrue(enemyBullet.crash(eliteEnemy));
    }

    @Test
    void getLocationX() {
        assumeTrue(enemyBullet.getLocationX() <= Main.WINDOW_WIDTH && enemyBullet.getLocationX() >= 0);
        System.out.println("The LocationX is effective");
    }

    @Test
    void getWidth() {
        assumeFalse(enemyBullet.getWidth() ==  -1);
        System.out.println("The Width is effective");
    }
}