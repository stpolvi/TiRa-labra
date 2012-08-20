
package tietorakenteet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author silja
 */
public class KaaripuuTest {

    Kaaripuu k;

    @Before
    public void setUp() {
        k = new Kaaripuu();
    }

    private Kaari uusiKaari(int a, int l) {
        return new Kaari(a, l);
    }

    @Test
    public void konstruktoriLuoOlion() {
        assertNotNull(new Kaaripuu());
    }

    @Test
    public void etsiLoytaaKunLisattyYksi() {
        k.lisaa(uusiKaari(5, 7));
        assertTrue(k.etsi(uusiKaari(5, 7)));
    }

    @Test
    public void etsiLoytaaKunLisattyMonta() {
        for (int i=9; i<26; i++) {
            k.lisaa(uusiKaari(i, 20-i));
        }
        for (int i=9; i<26; i++) {
            assertTrue(k.etsi(uusiKaari(i, 20-i)));
        }
    }

    /**
     * Java heap space, java.lang.OutOfMemoryError
     */

    @Test
    public void puuEiHajoaKunLisataan300Alkiota() {
        for (int i=-100; i<200; i++) {
            k.lisaa(uusiKaari(i, 20-i));
        }
        for (int i=-100; i<200; i++) {
            assertTrue(k.etsi(uusiKaari(i, 20-i)));
        }
    }
    

}