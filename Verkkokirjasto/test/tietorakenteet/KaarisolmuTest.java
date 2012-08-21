
package tietorakenteet;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author silja
 */
public class KaarisolmuTest {

    @Test
    public void konstruktoriLuoOlion() {
        Kaarisolmu uusi = new Kaarisolmu(new Kaari(2, -19), null, null);
        assertNotNull(uusi);
        assertNotNull(new Kaarisolmu(new Kaari(-2, 195), null, uusi));
        assertNotNull(new Kaarisolmu(new Kaari(890, 15), uusi, uusi));
    }

    @Test
    public void setteritJaGetteritToimivat() {
        Kaarisolmu k = new Kaarisolmu(new Kaari(2, -19), null, null);
        Kaarisolmu l = new Kaarisolmu(new Kaari(2, -19), null, null);
        Kaarisolmu m = new Kaarisolmu(new Kaari(2, -19), null, null);
        Kaarisolmu n = new Kaarisolmu(new Kaari(2, -19), null, null);

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

}