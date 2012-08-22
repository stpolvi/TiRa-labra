/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tyokalut;

import tietorakenteetOlioille.Kaari;
import tyokalut.Tyokalut;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author silja
 */
public class TyokalutTest {

    @Test
    public void keskiarvoValmiiksiKokonaisluku() {
        assertTrue("Keskiarvon tulee olla katkaistu kokonaisluku.",
                Tyokalut.keskiarvo(0, 8) == 4);
    }

    @Test
    public void keskiarvoKatkaistava() {
        assertTrue("Keskiarvon tulee olla katkaistu kokonaisluku.",
                Tyokalut.keskiarvo(0, 9) == 4);
    }

    @Test
    public void keskiarvoValmiiksiNegatiivinenKokonaisluku() {
        assertTrue("Keskiarvon tulee olla katkaistu kokonaisluku.",
                Tyokalut.keskiarvo(0, -8) == -4);
    }

    @Test
    public void keskiarvoKatkaistavaNegatiivinen() {
        assertTrue("Keskiarvon tulee olla katkaistu kokonaisluku.",
                Tyokalut.keskiarvo(0, -9) == -4);
    }

    @Test
    public void minimiToimii() {
        assertTrue(Tyokalut.minimi(5, 900) == 5);
        assertTrue(Tyokalut.minimi(5, -900) == -900);
        assertTrue(Tyokalut.minimi(-5, -900) == -900);
        assertTrue(Tyokalut.minimi(-5, 900) == -5);
        assertTrue(Tyokalut.minimi(5, 5) == 5);
    }

    @Test
    public void vaihdaPaittainToimiiKaarilla() {
        Kaari[] kaaret = new Kaari[15];

        for (int i=0; i<kaaret.length; i++) {
            kaaret[i] = new Kaari(i,i*2);
        }

        assertTrue(kaaret[5].equals(new Kaari(5, 10)));
        assertTrue(kaaret[2].equals(new Kaari(2, 4)));
        Tyokalut.vaihdaPaittain(kaaret, 2, 5);
        assertTrue(kaaret[2].equals(new Kaari(5, 10)));
        assertTrue(kaaret[5].equals(new Kaari(2, 4)));
    
        assertTrue(kaaret[4].equals(new Kaari(4, 8)));
        assertTrue(kaaret[0].equals(new Kaari(0, 0)));
        Tyokalut.vaihdaPaittain(kaaret, 4, 0);
        assertTrue(kaaret[0].equals(new Kaari(4, 8)));
        assertTrue(kaaret[4].equals(new Kaari(0, 0)));
    }

    @Test
    public void kopioiIntTaulukkoToiseenToimiiKunKopioitavaLyhyempi() {
        int[] lyhyt = new int[18];
        int[] pitka = new int[30];

        for (int i=0; i<lyhyt.length; i++) {
            lyhyt[i] = i * 2 + 3;
        }

        Tyokalut.kopioiTaulukkoToiseen(lyhyt, pitka);

        for (int i=0; i<lyhyt.length; i++) {
            assertTrue(pitka[i] == i * 2 + 3);
        }
    }

    @Test
    public void kopioiIntTaulukkoToiseenToimiiKunKopioitavaPidempi() {
        int[] lyhyt = new int[18];
        int[] pitka = new int[30];

        for (int i=0; i<pitka.length; i++) {
            pitka[i] = i * 2 + 3;
        }

        Tyokalut.kopioiTaulukkoToiseen(pitka, lyhyt);

        for (int i=0; i<lyhyt.length; i++) {
            assertTrue(lyhyt[i] == i * 2 + 3);
        }
    }

    @Test
    public void kopioiOliotaulukkoToiseenToimiiKaarillaKunKopioitavaLyhyempi() {
        Kaari[] lyhyt = new Kaari[15];
        Kaari[] pitka = new Kaari[28];

        for (int i=0; i<lyhyt.length; i++) {
            lyhyt[i] = new Kaari(i,i*2);
        }

        Tyokalut.kopioiTaulukkoToiseen(lyhyt, pitka);

        for (int i=0; i<lyhyt.length; i++) {
            assertTrue(pitka[i].equals(new Kaari(i,i*2)));
        }
    }

    @Test
    public void kopioiOliotaulukkoToiseenToimiiKaarillaKunKopioitavaPidempi() {
        Kaari[] lyhyt = new Kaari[15];
        Kaari[] pitka = new Kaari[28];

        for (int i=0; i<pitka.length; i++) {
            pitka[i] = new Kaari(i,i*2);
        }

        Tyokalut.kopioiTaulukkoToiseen(pitka, lyhyt);

        for (int i=0; i<lyhyt.length; i++) {
            assertTrue(lyhyt[i].equals(new Kaari(i,i*2)));
        }
    }
}