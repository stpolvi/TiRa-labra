
package suhteikot;

import org.junit.Test;
import relaatiot.RelaatioVenyvallaTaulukolla;
import tietorakenteetLuvuille.IntSailio;
import static org.junit.Assert.*;

/**
 * TavallinenSuhteikko-luokan testiluokka. Metodit on nimetty niin ettei
 * erillistä kommentointia tarvita.
 * @author silja
 */
public class TavallinenSuhteikkoTest {

    TavallinenSuhteikko s;

    /*
     * Apumetodit -----------------------------------------
     */

    /**
     * Apumetodi luo tyhjän suhteikon.
     * @return tyhjä suhteikko
     */

    public static TavallinenSuhteikko uusiTyhjaSuhteikko() {
        return new TavallinenSuhteikko();
    }

    /**
     * Apumetodi luo uuden kaksipisteisen suhteikon,
     * jossa yhteys pisteestä 1 pisteeseen 2.
     * @return uusi suhteikko
     */

    public static TavallinenSuhteikko pisteita2Yhteys1_2() {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(2);
        r.lisaaYhteys(1, 2);
        return new TavallinenSuhteikko(r);
    }

    /**
     * Apumetodi luo uuden kolmepisteisen suhteikon,
     * jossa yhteys pisteestä 2 pisteeseen 1 ja pisteestä 3 itseensä.
     * @return uusi suhteikko
     */

    public static TavallinenSuhteikko pisteita3Yhteys2_1ja3_3() {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(3);
        r.lisaaYhteys(2, 1);
        r.lisaaYhteys(3, 3);
        return new TavallinenSuhteikko(r);
    }

    /**
     * Apumetodi luo uuden n-pisteisen suhteikon,
     * jossa ei yhteyksiä.
     * @param n luotavan suhteikon pisteiden määrä
     * @return uusi suhteikko
     */

    public static TavallinenSuhteikko pisteitaNEiYhteyksia(int n) {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(n);
        return new TavallinenSuhteikko(r);
    }


    /**
     * Apumetodi luo yksipisteisen suhteikon jossa yhteys
     * pisteestä 1 itseensä.
     * @return uusi suhteikko
     */

    public static TavallinenSuhteikko pisteita1Yhteys1_1() {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(1);
        r.lisaaYhteys(1, 1);
        return new TavallinenSuhteikko(r);
    }

    /**
     * Apumetodi luo täydellisen 4-pisteisen suhteikon.
     * Täydellisessä suhteikossa jokaisen pisteparin välillä on yhteys
     * vähintään yhteen suuntaan.
     * @return uusi suhteikko
     */
    public static TavallinenSuhteikko taydellinen4PisteinenJossaSilmukka() {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(4);
        
        r.lisaaYhteys(1, 2);
        r.lisaaYhteys(1, 3);
        r.lisaaYhteys(4, 1);

        r.lisaaYhteys(2, 4);
        r.lisaaYhteys(2, 3);
        r.lisaaYhteys(3, 2);
        r.lisaaYhteys(2, 2);

        r.lisaaYhteys(4, 3);

        return new TavallinenSuhteikko(r);
    }

    /**
     * Apumetodi luo täydellisen 5-pisteisen verkon.
     * Täydellisessä verkossa jokaisen pisteparin välillä on yhteys
     * molempiin suuntiin, eikä silmukoita ole.
     * @return uusi suhteikko
     */
    
    public static TavallinenSuhteikko uusiSuhteikkoTaydellinenN(int n) {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(n);
        
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                r.lisaaYhteys(i, j);
            }
        }

        return new TavallinenSuhteikko(r);
    }

    /**
     * Apumetodi luo täydellisen 5-pisteisen verkon.
     * @return
     */

    public static TavallinenSuhteikko taydellinen5PisteinenVerkko() {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(5);

        for (int i=1; i<=5; i++) {
            for (int j=1; j<=5; j++) {
                if (i != j) r.lisaaYhteys(i, j);
            }
        }

        return new TavallinenSuhteikko(r);
    }




    /*
     * Testit ---------------------------------------------------
     */

    @Test
    public void parametritonKonstruktoriLuoOlion() {
        assertNotNull(new TavallinenSuhteikko());
    }

    @Test
    public void parametrillinenKonstruktoriLuoOlion() {
        assertNotNull(uusiSuhteikkoTaydellinenN(9));
    }

    @Test
    public void parametritonkonstruktoriAsettaaPisteidenLkmOikein() {
        s = uusiTyhjaSuhteikko();
        assertTrue("pisteitä piti olla 0, mutta oli " + s.PISTEITA,
                s.PISTEITA == 0);
    }

    @Test
    public void parametrillinenKonstruktoriAsettaaPisteidenLkmOikein() {
        s = pisteita1Yhteys1_1();
        assertTrue("pisteitä piti olla 1, mutta oli " + s.PISTEITA,
                s.PISTEITA == 1);

        s = pisteita2Yhteys1_2();
        assertTrue("pisteitä piti olla 2, mutta oli " + s.PISTEITA,
                s.PISTEITA == 2);

        s = taydellinen4PisteinenJossaSilmukka();
        assertTrue("pisteitä piti olla 4, mutta oli " + s.PISTEITA,
                s.PISTEITA == 4);

        s = taydellinen5PisteinenVerkko();
        assertTrue("pisteitä piti olla 5, mutta oli " + s.PISTEITA,
                s.PISTEITA == 5);
    }

    @Test
    public void onKaikkiYhteydetTaydellisellaVerkolla() {
        s = taydellinen5PisteinenVerkko();
        boolean yhteys;
        for (int i=1; i<s.PISTEITA; i++) {
            for (int j=1; j<s.PISTEITA; j++) {
                yhteys = s.onYhteys(i, j);
                if (i==j) assertFalse(yhteys);
                else assertTrue(yhteys);
            }
        }
    }

    @Test
    public void seuraajatTaydellisessaVerkossaOikein() {
        s = taydellinen5PisteinenVerkko();
        IntSailio ints;
        boolean etsi;
        for (int i=1; i<=5; i++) {
            ints = s.getSeuraajat(i);
            for (int j=1; j<=5; j++) {
                etsi = ints.etsi(j);
                if (i==j) assertFalse(etsi);
                else assertTrue(etsi);
            }
        }
    }


}