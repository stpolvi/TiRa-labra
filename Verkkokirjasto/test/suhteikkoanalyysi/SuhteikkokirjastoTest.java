/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package suhteikkoanalyysi;

import suhteikkoanalyysi.Suhteikkokirjasto;
import lukusuhteikot.Lukusuhteikko;
import lukusuhteikot.Lukusuhteikkorelaatio;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author silja
 */
public class SuhteikkokirjastoTest {

    public SuhteikkokirjastoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void tyhjaSuhteikkoTayttaaVerkkoehdon() {
        Lukusuhteikko s = new Lukusuhteikko();
        assertTrue(Suhteikkokirjasto.silmukaton(s));
        assertTrue(Suhteikkokirjasto.symmetrinen(s));
        assertTrue(Suhteikkokirjasto.tayttaaVerkkoehdon(s));
    }

    @Test
    public void yksisilmukkainenSymmetrinenEiTaytaVerkkoehtoa() {
        Lukusuhteikkorelaatio r = new Lukusuhteikkorelaatio(1);
        r.lisaaYhteys(1, 1);
        Lukusuhteikko s = new Lukusuhteikko(r);
        assertFalse(Suhteikkokirjasto.silmukaton(s));
        assertTrue(Suhteikkokirjasto.symmetrinen(s));
        assertFalse(Suhteikkokirjasto.tayttaaVerkkoehdon(s));
    }

    @Test
    public void silmukatonEisymmetrinenEiTaytaVerkkoehtoa() {
        Lukusuhteikkorelaatio r = new Lukusuhteikkorelaatio(2);
        r.lisaaYhteys(1, 2);
        Lukusuhteikko s = new Lukusuhteikko(r);
        assertTrue(Suhteikkokirjasto.silmukaton(s));
        assertFalse(Suhteikkokirjasto.symmetrinen(s));
        assertFalse(Suhteikkokirjasto.tayttaaVerkkoehdon(s));
    }

}