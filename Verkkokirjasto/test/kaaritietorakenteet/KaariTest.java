
package kaaritietorakenteet;

import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Kaari-luokan testiluokka.
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
            assertTrue(k.equals(k2));
            assertTrue(k2.equals(k));
        }
    }

    @Test
    public void equalsToimiiKunPisteetEri() {
        Kaari k2;

        k = new Kaari(8, 9);
        k2 = new Kaari(-1, 9);
        assertFalse(k.equals(k2));

        k2 = new Kaari(9, 9);
        assertFalse(k.equals(k2));

        k = new Kaari(9, 8);
        assertFalse(k.equals(k2));
        
        k = new Kaari(9, 90);
        assertFalse(k.equals(k2));
    }

    @Test
    public void equalsPalauttaaFalseVerrattaessaNulliin() {
        k = new Kaari(6, 80);
        assertFalse(k.equals(null));

        k = new Kaari(-6, 0);
        assertFalse(k.equals(null));
    }

    @Test
    public void equalsPalauttaaFalseVerrattaessaMuuhunOlioon() {
        k = new Kaari(6, 80);
        assertFalse(k.equals("kaari"));
        assertFalse(k.equals(new int[5]));
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

    @Test
    public void hashCodePalauttaaSamanJosAlkuJaLoppupisteSamat() {
        Kaari k1 = new Kaari(4, 8);
        Kaari k2 = new Kaari(4, 8);
        assertTrue(k1.hashCode() == k2.hashCode());

        k1 = new Kaari(-400, 0);
        k2 = new Kaari(-400, 0);
        assertTrue(k1.hashCode() == k2.hashCode());

        k1 = new Kaari(0, 9000);
        k2 = new Kaari(0, 9000);
        assertTrue(k1.hashCode() == k2.hashCode());
    }

}