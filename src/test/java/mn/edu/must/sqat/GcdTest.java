package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GcdTest {

    @Test
    @DisplayName("G1 gcd(7,0)=7 : x(13->20), y(13->15F) - zero iterations")
    void gcd_7_0_noIteration() {
        assertEquals(7, DataFlowTargets.gcd(7, 0));
    }

    @Test
    @DisplayName("G2 gcd(12,8)=4 : x(13->16), x(17->16), x(17->20), y(13->15T), y(13->16), y(13->17), "
            + "y(18->15T), y(18->15F), y(18->16), y(18->17), tmp(16->18) - two iterations")
    void gcd_12_8_twoIterations() {
        assertEquals(4, DataFlowTargets.gcd(12, 8));
    }

    @Test
    @DisplayName("G3 gcd(8,4)=4 : x(13->16), x(17->20), y(13->15T), y(13->16), y(13->17), y(18->15F), tmp(16->18) - one iteration")
    void gcd_8_4_oneIteration() {
        assertEquals(4, DataFlowTargets.gcd(8, 4));
    }

    @Test
    @DisplayName("G4 gcd(0,5)=5 : same pairs as G3, x=0 on entry")
    void gcd_0_5_oneIteration() {
        assertEquals(5, DataFlowTargets.gcd(0, 5));
    }
}