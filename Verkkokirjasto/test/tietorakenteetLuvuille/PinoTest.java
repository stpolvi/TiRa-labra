
package tietorakenteetLuvuille;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pinon testiluokka
 * @author silja
 */

public class PinoTest {

    Pino p;

    @Before
    public void setUp() {
        p = new Pino();
    }

    @Test
    public void konstruktoriLuoOlion() {
        assertNotNull(p);
    }

    @Test
    public void lisaaminenKasvattaaAlkioidenMaaraaOikein() {
        assertTrue(p.alkioita() == 0);

        for (int i=1; i<=18; i++) {
            p.lisaa(i);
            assertTrue(p.alkioita() == i);
        }
    }

    @Test
    public void lisaaminenKasvattaaAlkioidenMaaraaOikeinVaikkaKapasiteettiTayttyy() {
        assertTrue(p.alkioita() == 0);

        for (int i=1; i<=190; i++) {
            p.lisaa(i);
            assertTrue(p.alkioita() == i);
        }
    }

    @Test
    public void ottaminenVahentaaAlkioidenMaaraaOikein() {
        for (int i=1; i<=18; i++) {
            p.lisaa(i);
        }

        for (int i=1; i<=18; i++) {
            p.ota();
            assertTrue(p.alkioita() == 18-i);
        }
    }

    @Test
    public void alkiotOtetaanOikeassaJarjestyksessa() {
        for (int i=5; i<=28; i++) {
            p.lisaa(i);
        }
        for (int i=28; i>=5; i--) {
            assertTrue(p.ota() == i);
        }
        assertTrue(p.alkioita() == 0);
    }

}