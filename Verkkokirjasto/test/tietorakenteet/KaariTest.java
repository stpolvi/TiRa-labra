
package tietorakenteet;

import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author silja
 */
public class KaariTest {

    Kaari k;
    
    /*
     * Privaattimetodit -----------------------
     */

    private Kaari[] luoSatunnainenKaaritaulukkoPituudella(int pituus) {
        Kaari[] taulu = new Kaari[pituus];
        Random r = new Random();
        int a;
        int l;

        for (int i=0; i<taulu.length; i++) {
            a = r.nextInt();
            l = r.nextInt();
            taulu[i] = new Kaari(a, l);
        }

        return taulu;
    }

    /*
     * Testit -----------------------
     */

    @Test
    public void konstruktoriLuoOlion() {
        assertNotNull(new Kaari(3, 6));
        assertNotNull(new Kaari(-3, 6));
        assertNotNull(new Kaari(0, 0));
        assertNotNull(new Kaari(-100, 9000));
    }

    @Test
    public void konstruktoriAsettaaAlkuJaLoppupisteenOikein() {
        Random r = new Random();
        int a;
        int l;

        for (int i=0; i<14; i++) {
            a = r.nextInt();
            l = r.nextInt();
            k = new Kaari(a, l);
            assertTrue(k.ALKUPISTE == a);
            assertTrue(k.LOPPUPISTE == l);
        }
    }

    @Test
    public void equalsToimiiKunPisteetSamat() {
        Random r = new Random();
        int a;
        int l;
        Kaari k2;

        for (int i=0; i<14; i++) {
            a = r.nextInt();
            l = r.nextInt();
            k = new Kaari(a, l);
            k2 = new Kaari(a, l);
            assertEquals(k, k2);
        }
    }

    @Test
    public void equalsToimiiKunPisteetEri() {
        Kaari k2;

        k = new Kaari(8, 9);
        k2 = new Kaari(-1, 9);
        assertNotSame(k, k2);

        k2 = new Kaari(9, 9);
        assertNotSame(k, k2);

        k = new Kaari(9, 8);
        assertNotSame(k, k2);
        
        k = new Kaari(9, 90);
        assertNotSame(k, k2);
    }

    @Test
    public void compareToToimiiOikein() {
        assertTrue(new Kaari(2, 7).compareTo(new Kaari(3, 1)) < 0);
        assertTrue(new Kaari(2, 7).compareTo(new Kaari(3, 9)) < 0);

        assertTrue(new Kaari(-2, 7).compareTo(new Kaari(-3, 1)) > 0);
        assertTrue(new Kaari(-2, 7).compareTo(new Kaari(-3, 9)) > 0);

        assertTrue(new Kaari(2, 7).compareTo(new Kaari(2, 7)) == 0);
    }

    @Test
    public void compareToToimiiKunJarjestetaanTaulukkoArraysSortilla() {
        Kaari[] taulu = luoSatunnainenKaaritaulukkoPituudella(15);
        java.util.Arrays.sort(taulu);

        for (int i=0; i<taulu.length-1; i++) {
            assertTrue(taulu[i].ALKUPISTE <= taulu[i+1].ALKUPISTE);
            if (taulu[i].ALKUPISTE == taulu[i+1].ALKUPISTE)
                assertTrue(taulu[i].LOPPUPISTE <= taulu[i+1].LOPPUPISTE);
        }
    }

}