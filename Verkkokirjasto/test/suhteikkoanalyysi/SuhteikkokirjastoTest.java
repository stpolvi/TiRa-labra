/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package suhteikkoanalyysi;


import lukusuhteikot.Lukusuhteikko;
import lukusuhteikot.RelaatioVenyvallaTaulukolla;
import suhteikot.Suhteikko;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import suhteikot.Relaatio;
import static org.junit.Assert.*;

/**
 * Suhteikkokirjaston testiluokka
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

    /**
     * Tyhjän suhteikon tulee täyttää verkkoehto
     */
    @Test
    public void tyhjaSuhteikkoTayttaaVerkkoehdon() {
        Suhteikko s = new Lukusuhteikko();
        assertTrue(Suhteikkoanalyysikirjasto.silmukaton(s));
        assertTrue(Suhteikkoanalyysikirjasto.symmetrinen(s));
        assertTrue(Suhteikkoanalyysikirjasto.tayttaaVerkkoehdon(s));
    }

    /**
     * Yksipisteinen suhteikko, jossa on silmukka, ei saa täyttää verkkoehtoa
     * vaikka onkin symmetrinen.
     */
    @Test
    public void yksisilmukkainenSymmetrinenEiTaytaVerkkoehtoa() {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(1);
        r.lisaaYhteys(1, 1);
        Lukusuhteikko s = new Lukusuhteikko(r);
        assertFalse(Suhteikkoanalyysikirjasto.silmukaton(s));
        assertTrue(Suhteikkoanalyysikirjasto.symmetrinen(s));
        assertFalse(Suhteikkoanalyysikirjasto.tayttaaVerkkoehdon(s));
    }

    /**
     * Silmukaton suhteikko, joka ei ole symmetrinen, ei saa täyttää verkkoehtoa.
     */
    @Test
    public void silmukatonEisymmetrinenEiTaytaVerkkoehtoa() {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(2);
        r.lisaaYhteys(1, 2);
        Lukusuhteikko s = new Lukusuhteikko(r);
        assertTrue(Suhteikkoanalyysikirjasto.silmukaton(s));
        assertFalse(Suhteikkoanalyysikirjasto.symmetrinen(s));
        assertFalse(Suhteikkoanalyysikirjasto.tayttaaVerkkoehdon(s));
    }

}