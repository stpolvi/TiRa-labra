
package tietorakenteet;

import tietorakenteetLuvuille.Jono;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Jonon testiluokka
 * @author silja
 */
public class JonoTest {

    Jono j;

    @Before
    public void setUp() {
        j = new Jono();
    }

    @Test
    public void konstruktoriLuoOlion() {
        assertNotNull(j);
    }

    @Test
    public void lisaaminenKasvattaaAlkioidenMaaraaOikein() {
        assertTrue(j.alkioita() == 0);

        for (int i=1; i<=18; i++) {
            j.lisaa(i);
            assertTrue(j.alkioita() == i);
        }
    }

    @Test
    public void lisaaminenKasvattaaAlkioidenMaaraaOikeinVaikkaKapasiteettiTayttyy() {
        assertTrue(j.alkioita() == 0);

        for (int i=1; i<=190; i++) {
            j.lisaa(i);
            assertTrue(j.alkioita() == i);
        }
    }

    @Test
    public void ottaminenVahentaaAlkioidenMaaraaOikein() {
        for (int i=1; i<=18; i++) {
            j.lisaa(i);
        }

        for (int i=1; i<=18; i++) {
            j.ota();
            assertTrue(j.alkioita() == 18-i);
        }
    }

    @Test
    public void alkiotOtetaanOikeassaJarjestyksessa() {
        for (int i=5; i<=28; i++) {
            j.lisaa(i);
        }
        for (int i=5; i<=28; i++) {
            assertTrue(j.ota() == i);
        }
        assertTrue(j.alkioita() == 0);
    }

}