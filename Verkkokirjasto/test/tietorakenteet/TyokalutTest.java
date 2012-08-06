/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tietorakenteet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author silja
 */
public class TyokalutTest {

    public TyokalutTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

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