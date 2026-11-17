package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusTest {

    @Test
    @DisplayName("D1 [1,0,1,1,1,1,1] thr=10 -> 3 : 50F so score(49->54), score(54->56); streak(46->41); best(43->53T)")
    void allDefs_belowThreshold_streak5() {
        assertEquals(3, DataFlowTargets.bonus(new int[] {1, 0, 1, 1, 1, 1, 1}, 10));
    }

    @Test
    @DisplayName("D2 [1,0,1] thr=1 -> 5 : 50T so score(51->56); best(43->53F)")
    void allDefs_reachThreshold_shortStreak() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {1, 0, 1}, 1));
    }
}