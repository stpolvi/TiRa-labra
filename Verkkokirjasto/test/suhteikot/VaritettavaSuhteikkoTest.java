/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package suhteikot;

import relaatiot.RelaatioVenyvallaTaulukolla;
import java.awt.Color;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author silja
 */
public class VaritettavaSuhteikkoTest {

    VaritettavaSuhteikko s;

    /*
     * Apumetodit -------------------
     */

    /**
     * Apumetodi luo tyhjän suhteikon.
     * @return tyhjä suhteikko
     */

    public static VaritettavaSuhteikko uusiTyhjaSuhteikko() {
        return new VaritettavaSuhteikko();
    }

    /**
     * Apumetodi luo uuden n-pisteisen suhteikon,
     * jossa ei yhteyksiä.
     * @param n luotavan suhteikon pisteiden määrä
     * @return uusi suhteikko
     */

    public static VaritettavaSuhteikko pisteitaNEiYhteyksia(int n) {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(n);
        return new VaritettavaSuhteikko(r);
    }

    /**
     * Apumetodi luo uuden kaksipisteisen suhteikon,
     * jossa yhteys pisteestä 1 pisteeseen 2.
     * @return uusi suhteikko
     */

    public static VaritettavaSuhteikko pisteita2Yhteys1_2() {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(2);
        r.lisaaYhteys(1, 2);
        return new VaritettavaSuhteikko(r);
    }

    /**
     * Apumetodi luo uuden n-pisteisen suhteikon,
     * jossa ei yhteyksiä.
     * Käyttää kaikki pisteet värjäävää konstruktoria annetulla värillä.
     * @param n luotavan suhteikon pisteiden määrä
     * @return uusi suhteikko
     */

    public static VaritettavaSuhteikko pisteitaNEiYhteyksia(int n, Color vari) {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(n);
        return new VaritettavaSuhteikko(r, vari);
    }

        /**
     * Apumetodi luo täydellisen 4-pisteisen suhteikon.
     * Täydellisessä suhteikossa jokaisen pisteparin välillä on yhteys
     * vähintään yhteen suuntaan.
     * @return uusi suhteikko
     */
    public static VaritettavaSuhteikko taydellinen4PisteinenJossaSilmukka() {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(4);
        r.lisaaYhteys(1, 2);
        r.lisaaYhteys(1, 3);
        r.lisaaYhteys(4, 1);

        r.lisaaYhteys(2, 4);
        r.lisaaYhteys(2, 3);
        r.lisaaYhteys(3, 2);
        r.lisaaYhteys(2, 2);

        r.lisaaYhteys(4, 3);

        return new VaritettavaSuhteikko(r);
    }

    /**
     * Apumetodi luo täydellisen 5-pisteisen verkon.
     * Täydellisessä verkossa jokaisen pisteparin välillä on yhteys
     * molempiin suuntiin, eikä silmukoita ole.
     * @return uusi suhteikko
     */

    public static VaritettavaSuhteikko uusiSuhteikkoTaydellinenN(int n) {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(n);

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                r.lisaaYhteys(i, j);
            }
        }

        return new VaritettavaSuhteikko(r);
    }

    /**
     * Apumetodi luo täydellisen 5-pisteisen verkon.
     * @return
     */

    public static VaritettavaSuhteikko taydellinen5PisteinenVerkko() {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(5);

        for (int i=1; i<=5; i++) {
            for (int j=1; j<=5; j++) {
                if (i != j) r.lisaaYhteys(i, j);
            }
        }

        return new VaritettavaSuhteikko(r);
    }


    /*
     * Testit ---------------------------------------------
     */

    @Test
    public void varillinenKonstruktoriAsettaaVarinKaikille() {
        Color sini = Color.BLUE;
        s = pisteitaNEiYhteyksia(6, sini);
        for (int i=1; i<=6; i++) {
            assertEquals(s.getVari(i), sini);
        }
    }

    @Test
    public void varittomallaKonstruktorillaKaikkienVariksiTuleeNull() {
        s = pisteitaNEiYhteyksia(4);
        for (int i=1; i<=4; i++) {
            assertTrue(s.getVari(i) == null);
        }
    }

    @Test
    public void tyhjaKonstruktoriLuoOlion() {
        assertNotNull(new VaritettavaSuhteikko());
    }

    @Test
    public void alustaVaritAsettaaVarinNullKaikille() {
        Color sini = Color.BLUE;
        s = pisteitaNEiYhteyksia(6, sini);

        for (int i=1; i<=6; i++) {
            assertEquals(s.getVari(i), sini);
        }

        s.alustaVarit();

        for (int i=1; i<=6; i++) {
            assertTrue(s.getVari(i) == null);
        }
    }

    @Test
    public void getVariJaVaritaToimivat() {
        s = pisteitaNEiYhteyksia(5);
        
        Color puna = Color.RED;
        s.varita(3, puna);
        assertEquals(s.getVari(3), puna);

        Color viher = Color.GREEN;
        s.varita(3, viher);
        assertEquals(s.getVari(3), viher);
    }

}