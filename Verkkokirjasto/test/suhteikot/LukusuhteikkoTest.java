
package suhteikot;

import org.junit.Before;
import org.junit.Test;
import relaatiot.RelaatioVenyvallaTaulukolla;
import static org.junit.Assert.*;

/**
 * Lukusuhteikko-luokan testiluokka. Metodit on nimetty niin ettei
 * erillistä kommentointia tarvita.
 * @author silja
 */
public class LukusuhteikkoTest {

    Lukusuhteikko suhteikko;

    @Before
    public void setUp() {
        RelaatioVenyvallaTaulukolla r = uusiRelaatioTaydellinenN(9);
        suhteikko = new Lukusuhteikko(r);
    }

    /*
     * Apumetodit -----------------------------------------
     */

    private RelaatioVenyvallaTaulukolla uusiRelaatioTaydellinenN(int n) {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(n);
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                r.lisaaYhteys(i, j);
            }
        }
        return r;
    }

    /*
     * Testit ---------------------------------------------------
     */

    @Test
    public void testOnYhteysTaydellisella() {
        for (int i=1; i<suhteikko.pisteidenLkm(); i++) {
            for (int j=1; j<suhteikko.pisteidenLkm(); j++) {
                assertTrue(suhteikko.onYhteys(i, j));
            }
        }
    }

}