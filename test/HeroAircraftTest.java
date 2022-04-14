import edu.hitsz.aircraft.HeroAircraft;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroAircraftTest {

    HeroAircraft heroAircraft;

    @BeforeEach
    void setUp() {
        System.out.println("**--  - Executed before each test method in this class  --**");
    }

    @AfterEach
    void tearDown() {
        System.out.println("**--- Executed after each test method in this class ---**");
        heroAircraft = null;
    }

    @Test
    void getHeroAircraft() {
       assertTrue(HeroAircraft.getHeroAircraft(1,1,0,1,100) instanceof HeroAircraft);
        }

    @Test
    void shoot() {
        System.out.println("**--- Test shoot method ---**");
        heroAircraft = HeroAircraft.getHeroAircraft(1,1,0,1,100);
        assertNotNull(heroAircraft.shoot());
    }

}