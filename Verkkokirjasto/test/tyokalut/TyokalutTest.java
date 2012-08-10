/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tyokalut;

import tyokalut.Tyokalut;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author silja
 */
public class TyokalutTest {

    @Test
    public void keskiarvoValmiiksiKokonaisluku() {
        assertTrue("Keskiarvon tulee olla katkaistu kokonaisluku.",
                Tyokalut.keskiarvo(0, 8) == 4);
    }

    @Test
    public void keskiarvoKatkaistava() {
        assertTrue("Keskiarvon tulee olla katkaistu kokonaisluku.",
                Tyokalut.keskiarvo(0, 9) == 4);
    }

    @Test
    public void keskiarvoValmiiksiNegatiivinenKokonaisluku() {
        assertTrue("Keskiarvon tulee olla katkaistu kokonaisluku.",
                Tyokalut.keskiarvo(0, -8) == -4);
    }

    @Test
    public void keskiarvoKatkaistavaNegatiivinen() {
        assertTrue("Keskiarvon tulee olla katkaistu kokonaisluku.",
                Tyokalut.keskiarvo(0, -9) == -4);
    }

    @Test
    public void minimiToimii() {
        assertTrue(Tyokalut.minimi(5, 900) == 5);
        assertTrue(Tyokalut.minimi(5, -900) == -900);
        assertTrue(Tyokalut.minimi(-5, -900) == -900);
        assertTrue(Tyokalut.minimi(-5, 900) == -5);
        assertTrue(Tyokalut.minimi(5, 5) == 5);
    }

}