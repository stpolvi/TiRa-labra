/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package suhteikkoanalyysi;


import suhteikot.Lukusuhteikko;
import relaatiot.RelaatioVenyvallaTaulukolla;
import suhteikot.Suhteikko;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import relaatiot.Relaatio;
import static org.junit.Assert.*;

/**
 * Suhteikkokirjaston testiluokka
 * @author silja
 */
public class SuhteikkokirjastoTest {

    Suhteikko s;

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

    /*
     * Apumetodit --------------------------------------------------------
     */

    /**
     * Apumetodi luo tyhjän suhteikon.
     * @return tyhjä suhteikko
     */
    Lukusuhteikko uusiTyhjaSuhteikko() {
        return new Lukusuhteikko();
    }

    /**
     * Apumetodi luo uuden kaksipisteisen suhteikon,
     * jossa yhteys pisteestä 1 pisteeseen 2.
     * @return uusi suhteikko
     */

    Lukusuhteikko pisteita2Yhteys1_2() {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(2);
        r.lisaaYhteys(1, 2);
        return new Lukusuhteikko(r);
    }

    /**
     * Apumetodi luo yksipisteisen suhteikon jossa yhteys
     * pisteestä 1 itseensä.
     * @return uusi suhteikko
     */

    Lukusuhteikko pisteita1Yhteys1_1() {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(1);
        r.lisaaYhteys(1, 1);
        return new Lukusuhteikko(r);
    }



    /*
     * Testit --------------------------------------------------------
     */

    /**
     * Tyhjän suhteikon tulee täyttää verkkoehto
     */
    @Test
    public void tyhjaSuhteikkoTayttaaVerkkoehdon() {
        s = uusiTyhjaSuhteikko();
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
        s = new Lukusuhteikko(r);
        assertFalse(Suhteikkoanalyysikirjasto.silmukaton(s));
        assertTrue(Suhteikkoanalyysikirjasto.symmetrinen(s));
        assertFalse(Suhteikkoanalyysikirjasto.tayttaaVerkkoehdon(s));
    }

    /**
     * Silmukaton suhteikko, joka ei ole symmetrinen, ei saa täyttää verkkoehtoa.
     */
    @Test
    public void silmukatonEisymmetrinenEiTaytaVerkkoehtoa() {
        s = pisteita2Yhteys1_2();
        assertTrue(Suhteikkoanalyysikirjasto.silmukaton(s));
        assertFalse(Suhteikkoanalyysikirjasto.symmetrinen(s));
        assertFalse(Suhteikkoanalyysikirjasto.tayttaaVerkkoehdon(s));
    }


}