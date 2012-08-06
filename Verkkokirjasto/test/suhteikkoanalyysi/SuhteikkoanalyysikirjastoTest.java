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
public class SuhteikkoanalyysikirjastoTest {

    Suhteikko s;

    public SuhteikkoanalyysikirjastoTest() {
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

    /**
     * Apumetodi luo täydellisen 4-pisteisen suhteikon.
     * Täydellisessä suhteikossa jokaisen pisteparin välillä on yhteys
     * vähintään yhteen suuntaan.
     * @return uusi suhteikko
     */
    Lukusuhteikko taydellinen4PisteinenJossaSilmukka() {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(4);
        r.lisaaYhteys(1, 2);
        r.lisaaYhteys(1, 3);
        r.lisaaYhteys(4, 1);

        r.lisaaYhteys(2, 4);
        r.lisaaYhteys(2, 3);
        r.lisaaYhteys(3, 2);
        r.lisaaYhteys(2, 2);

        r.lisaaYhteys(4, 3);

        return new Lukusuhteikko(r);
    }

    /**
     * Apumetodi luo täydellisen 5-pisteisen verkon.
     * Täydellisessä verkossa jokaisen pisteparin välillä on yhteys
     * molempiin suuntiin, eikä silmukoita ole.
     * @return uusi suhteikko
     */

    Lukusuhteikko taydellinen5PisteinenVerkko() {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(5);

        for (int i=1; i<=5; i++) {
            for (int j=1; j<=5; j++) {
                if (i != j) r.lisaaYhteys(i, j);
            }
        }

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
        assertTrue("Ei ollut silmukaton.", Suhteikkoanalyysikirjasto.silmukaton(s));
        assertTrue("Ei ollut symmetrinen.", Suhteikkoanalyysikirjasto.symmetrinen(s));
        assertTrue(Suhteikkoanalyysikirjasto.tayttaaVerkkoehdon(s));
    }

    @Test
    public void taydellinenVerkkoTayttaaVerkkoehdon() {
        s = taydellinen5PisteinenVerkko();
        assertTrue("Ei ollut silmukaton.", Suhteikkoanalyysikirjasto.silmukaton(s));
        assertTrue("Ei ollut symmetrinen.", Suhteikkoanalyysikirjasto.symmetrinen(s));
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

    @Test
    public void taydellisessa5PisteisessaVerkossaKaikkiAsteet4() {
        s = taydellinen5PisteinenVerkko();
        for (int i=1; i<=5; i++) {
            assertTrue(Suhteikkoanalyysikirjasto.tuloaste(s, i) == 4);
            assertTrue(Suhteikkoanalyysikirjasto.lahtoaste(s, i) == 4);
        }
    }

    @Test
    public void asteetOikein1PisteisessaJossaSilmukka() {
        s = pisteita1Yhteys1_1();
        assertTrue(Suhteikkoanalyysikirjasto.lahtoaste(s, 1) == 1);
        assertTrue(Suhteikkoanalyysikirjasto.tuloaste(s, 1) == 1);
    }

    @Test
    public void asteetOikein2PisteisessaJossaYhteys1_2() {
        s = pisteita2Yhteys1_2();
        assertTrue(Suhteikkoanalyysikirjasto.lahtoaste(s, 1) == 1);
        assertTrue(Suhteikkoanalyysikirjasto.tuloaste(s, 1) == 0);
        assertTrue(Suhteikkoanalyysikirjasto.lahtoaste(s, 2) == 0);
        assertTrue(Suhteikkoanalyysikirjasto.tuloaste(s, 2) == 1);
    }

}