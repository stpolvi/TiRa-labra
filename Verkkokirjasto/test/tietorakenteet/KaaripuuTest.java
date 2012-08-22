
package tietorakenteet;

import java.util.Random;
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

    @Test
    public void puuEiHajoaKunLisataan300Alkiota() {
        for (int i=-100; i<200; i++) {
            k.lisaa(uusiKaari(i, 20-i));
        }
        for (int i=-100; i<200; i++) {
            assertTrue(k.etsi(uusiKaari(i, 20-i)));
        }
    }

    @Test
    public void vaariaKaariaEiLoydy() {
        for (int i=9; i<26; i++) {
            assertFalse(k.etsi(uusiKaari(i, 20-i)));
        }
    }

    @Test
    public void olemattomanPoistaminenEiKaadaPuuta() {
        k.poista(null);
        k.poista(uusiKaari(4, 19));
    }

    @Test
    public void poistamisenJalkeenEiLoydyPerakkaisilla() {
        for (int i=12; i<28; i++) {
            k.lisaa(uusiKaari(i, 20+i));
        }
        for (int i=12; i<28; i++) {
            k.poista(uusiKaari(i, 20+i));
        }
        for (int i=12; i<28; i++) {
            assertFalse("puuhun jäi kaari " + i + "," + (20+i),
                    k.etsi(uusiKaari(i, 20+i)));
        }
    }

    @Test
    public void poistamisenJalkeenEiLoydySatunnaisilla() {
        Random r = new Random();
        
        Kaari k1 = uusiKaari(r.nextInt(), r.nextInt());
        k.lisaa(k1);
        assertTrue(k.etsi(k1));
        Kaari k2 = uusiKaari(r.nextInt(), r.nextInt());
        k.lisaa(k2);
        assertTrue(k.etsi(k2));
        Kaari k3 = uusiKaari(r.nextInt(), r.nextInt());
        k.lisaa(k3);
        assertTrue(k.etsi(k3));
        Kaari k4 = uusiKaari(r.nextInt(), r.nextInt());
        k.lisaa(k4);
        assertTrue(k.etsi(k4));
        Kaari k5 = uusiKaari(r.nextInt(), r.nextInt());
        k.lisaa(k5);
        assertTrue(k.etsi(k5));
        Kaari k6 = uusiKaari(r.nextInt(), r.nextInt());
        k.lisaa(k6);
        assertTrue(k.etsi(k6));
        Kaari k7 = uusiKaari(r.nextInt(), r.nextInt());
        k.lisaa(k7);
        assertTrue(k.etsi(k7));
        Kaari k8 = uusiKaari(r.nextInt(), r.nextInt());
        k.lisaa(k8);
        assertTrue(k.etsi(k8));

        k.poista(k4);
        k.poista(k6);
        k.poista(k8);
        k.poista(k3);
        k.poista(k5);
        k.poista(k7);
        k.poista(k1);
        k.poista(k2);

        assertFalse("Puuhun jäi kaari " + k1.ALKUPISTE + "," + k1.LOPPUPISTE,
                k.etsi(k1));
        assertFalse("Puuhun jäi kaari " + k2.ALKUPISTE + "," + k2.LOPPUPISTE,
                k.etsi(k2));
        assertFalse("Puuhun jäi kaari " + k3.ALKUPISTE + "," + k3.LOPPUPISTE,
                k.etsi(k3));
        assertFalse("Puuhun jäi kaari " + k4.ALKUPISTE + "," + k4.LOPPUPISTE,
                k.etsi(k4));
        assertFalse("Puuhun jäi kaari " + k5.ALKUPISTE + "," + k5.LOPPUPISTE,
                k.etsi(k5));
        assertFalse("Puuhun jäi kaari " + k6.ALKUPISTE + "," + k6.LOPPUPISTE,
                k.etsi(k6));
        assertFalse("Puuhun jäi kaari " + k7.ALKUPISTE + "," + k7.LOPPUPISTE,
                k.etsi(k7));
        assertFalse("Puuhun jäi kaari " + k8.ALKUPISTE + "," + k8.LOPPUPISTE,
                k.etsi(k8));
    }

}