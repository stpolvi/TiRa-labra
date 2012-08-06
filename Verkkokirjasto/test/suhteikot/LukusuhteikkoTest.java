/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package suhteikot;

import relaatiot.RelaatioVenyvallaTaulukolla;
import suhteikot.Lukusuhteikko;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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

    public LukusuhteikkoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        RelaatioVenyvallaTaulukolla r = uusiRelaatioTaydellinenN(9);
        suhteikko = new Lukusuhteikko(r);
    }

    private RelaatioVenyvallaTaulukolla uusiRelaatioTaydellinenN(int n) {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(n);
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                r.lisaaYhteys(i, j);
            }
        }
        return r;
    }


    @After
    public void tearDown() {
    }

    @Test
    public void testOnYhteysTaydellisella() {
        for (int i=1; i<suhteikko.pisteidenLkm(); i++) {
            for (int j=1; j<suhteikko.pisteidenLkm(); j++) {
                assertTrue(suhteikko.onYhteys(i, j));
            }
        }
    }

}