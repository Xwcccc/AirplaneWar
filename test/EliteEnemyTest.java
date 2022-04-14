import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.Blood;
import edu.hitsz.prop.Boom;
import edu.hitsz.prop.Fire;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class EliteEnemyTest {

    EliteEnemy eliteenemy;

    @BeforeEach
    void setUp() {
        System.out.println("**--  - Executed before each test method in this class  --**");
        eliteenemy = new EliteEnemy(1,1,0,2,100);
    }

    @AfterEach
    void tearDown() {
        System.out.println("**--- Executed after each test method in this class ---**");
        eliteenemy = null;
    }

    @DisplayName("Test vanish method")
    @Test
    void vanish() {
        System.out.println("**--- Test vanish method ---**");
        assertTrue(eliteenemy.notValid() == false);
    }

    @Test
    @DisplayName("Test elite_shoot method")
    void shoot() {
        System.out.println("**--- Test shoot method ---**");
        assertNotNull(eliteenemy.shoot());
    }

    @Test
    @Disabled("Nothing to check")
    void prop() {
        System.out.println("**--- Test prop method ---**");
    }
}