import edu.hitsz.prop.Blood;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BloodTest {

    private Blood blood;

    @BeforeEach
    void setUp() {
        System.out.println("**--  - Executed before each test method in this class  --**");
        blood = new Blood(2,2,0,2,1);
    }

    @AfterEach
    void tearDown() {
        System.out.println("**--- Executed after each test method in this class ---**");
        blood = null;
    }

    @Test
    void getImage() {
        System.out.println("**--  Test image method  --**");
        assertNotNull(blood.getImage());
    }

    @DisplayName("Test power effect method")
    @Test
    void effect() {
        System.out.println("**--  Test power effect method  --**");
        assertEquals(10,blood.effect());
    }
}