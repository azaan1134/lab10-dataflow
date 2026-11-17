package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusTest {

    // ---------- Stage 1: ALL-DEFS ----------

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

    // ---------- Stage 2: ALL-DU-PAIRS ----------

    @Test
    @DisplayName("P1 null -> IAE : B1 attendance(31->32T)")
    void nullAttendance_throws() {
        assertThrows(IllegalArgumentException.class, () -> DataFlowTargets.bonus(null, 3));
    }

    @Test
    @DisplayName("P2 thr=-1 -> IAE : B5 threshold(31->32T)")
    void negativeThreshold_throws() {
        assertThrows(IllegalArgumentException.class, () -> DataFlowTargets.bonus(new int[] {1}, -1));
    }

    @Test
    @DisplayName("P3 [] thr=0 -> 5 : B10 present(35->50T), B24 best(37->53F), B4 attendance(31->38F)")
    void emptyArray_thresholdZero() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {}, 0));
    }

    @Test
    @DisplayName("P4 [0,0,0] thr=1 -> 0 : B11 present(35->50F), B32 score(49->56), B24 best(37->53F)")
    void allAbsent_belowThreshold() {
        assertEquals(0, DataFlowTargets.bonus(new int[] {0, 0, 0}, 1));
    }

    @Test
    @DisplayName("P5 [1,1,1,1,1] thr=5 -> 8 : exact threshold, B33 score(51->54), B13 present(40->50T), B27 best(43->53T)")
    void exactThreshold_streakFive() {
        assertEquals(8, DataFlowTargets.bonus(new int[] {1, 1, 1, 1, 1}, 5));
    }

    @Test
    @DisplayName("P6 [1,1,1,0,1,1,1,1,1,1] thr=9 -> 8 : long streak broken in the middle, B26 best(43->42F), B25 best(43->42T)")
    void longStreakBrokenInMiddle() {
        assertEquals(8, DataFlowTargets.bonus(new int[] {1, 1, 1, 0, 1, 1, 1, 1, 1, 1}, 9));
    }

    @Test
    @DisplayName("P7 [1,1,1,1,0,1,1,1,1] thr=8 -> 5 : two streaks of 4, best stays 4, B26 best(43->42F)")
    void twoStreaksOfFour_noStreakBonus() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {1, 1, 1, 1, 0, 1, 1, 1, 1}, 8));
    }
}