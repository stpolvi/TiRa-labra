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