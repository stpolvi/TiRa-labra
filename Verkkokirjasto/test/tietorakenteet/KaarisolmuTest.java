
package tietorakenteet;

import tietorakenteetOlioille.Kaarisolmu;
import tietorakenteetOlioille.Kaari;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Kaaarisolmun testiluokka.
 * @author silja
 */
public class KaarisolmuTest {

    Kaarisolmu k;
    Kaarisolmu l;
    Kaarisolmu m;
    Kaarisolmu n;

    @Before
    public void setUp() {
        k = new Kaarisolmu(new Kaari(2, -19), null, null, null);
        l = new Kaarisolmu(new Kaari(6, 169), null, null, null);
        m = new Kaarisolmu(new Kaari(19, -1900), null, null, null);
        n = new Kaarisolmu(new Kaari(561, 0), null, null, null);
    }

    @Test
    public void konstruktoriLuoOlion() {
        Kaarisolmu uusi = new Kaarisolmu(new Kaari(2, -19), null, null, null);
        assertNotNull(uusi);
        assertNotNull(new Kaarisolmu(new Kaari(-2, 195), null, null, uusi));
        assertNotNull(new Kaarisolmu(new Kaari(890, 15), null, uusi, uusi));
        assertNotNull(new Kaarisolmu(new Kaari(890, 15), uusi, uusi, uusi));
        assertNotNull(new Kaarisolmu(new Kaari(890, 15), uusi, null, uusi));
    }

    @Test
    public void konstruktoriLuoOlion2() {
        assertNotNull(k);
        assertNotNull(l);
        assertNotNull(m);
        assertNotNull(n);
    }

    @Test
    public void lapsisetteritJaLapsigetteritToimivat() {
        assertFalse(k.getVasenLapsi() == n);
        k.setVasenLapsi(n);
        assertTrue(k.getVasenLapsi() == n);

        l.setVasenLapsi(m);
        assertTrue(l.getVasenLapsi() == m);

        k.setVasenLapsi(l);
        assertTrue(k.getVasenLapsi() == l);

        n.setOikeaLapsi(k);
        assertTrue(n.getOikeaLapsi() == k);

        m.setOikeaLapsi(n);
        assertTrue(m.getOikeaLapsi() == n);

        l.setOikeaLapsi(k);
        assertTrue(l.getOikeaLapsi() == k);
    }

    @Test
    public void emosetteriJaGetteriToimivat() {
        assertFalse(k.getEmo() == n);
        k.setEmo(n);
        assertTrue(k.getEmo() == n);

        k.setEmo(l);
        assertTrue(k.getEmo() == l);

        l.setEmo(n);
        assertTrue(l.getEmo() == n);

        n.setEmo(n);
        assertTrue(n.getEmo() == n);
    }

}