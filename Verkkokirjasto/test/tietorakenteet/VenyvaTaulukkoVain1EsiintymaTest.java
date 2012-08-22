
package tietorakenteet;

import tietorakenteetLuvuille.VenyvaTaulukkoVain1Esiintyma;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author silja
 */
public class VenyvaTaulukkoVain1EsiintymaTest {

    VenyvaTaulukkoVain1Esiintyma taulukko;

    @Before
    public void setUp() {
        taulukko = uusiParametriton();
    }


    /*
     * Apumetodit ---------------
     */

    /**
     * Apumetodi lisää taulukkoon luvut 1,...,n
     * @param n lisättävien määrä
     */

    private void lisaaTaulukkoonNLukua(int n) {
        for (int i=1; i<=n; i++) {
            taulukko.lisaa(i);
        }
    }

    /**
     * Apumetodi poistaa taulukosta luvut 1,...,n
     * @param n poistettavien lukumäärä
     */

    private void poistaTaulukostaNLukua(int n) {
        for (int i=1; i<=n; i++) {
            taulukko.poistaYksiEsiintyma(i);
        }
    }

    /**
     * Apumetodi lisää taulukkoon n satunnaista int-lukua.
     * @param n lisättävien määrä
     */

    private void lisaaTaulukkoonNSatunnaistaLukua(int n) {
        for (int i=1; i<=n; i++) {
            Random r = new Random();
            taulukko.lisaa(r.nextInt());
        }
    }

    /**
     * Apumetodi vertaa taulukon alkioiden määrää annettuun lukuun.
     * @param oikeaAlkioidenLkm kuinka monta alkioita pitäisi olla
     */

    private void alkioitaPitaisiOlla(int oikeaAlkioidenLkm) {
        assertTrue("Alkioita pitäisi olla " + oikeaAlkioidenLkm +
                    " mutta niitä oli " + taulukko.alkioita(),
                    taulukko.alkioita() == oikeaAlkioidenLkm);
    }

    VenyvaTaulukkoVain1Esiintyma uusiParametriton() {
        return new VenyvaTaulukkoVain1Esiintyma();
    }

    VenyvaTaulukkoVain1Esiintyma uusiParametreilla(int kapasiteetti, int kasvatussuhde) {
        return new VenyvaTaulukkoVain1Esiintyma(kapasiteetti, kasvatussuhde);
    }

    /*
     * TESTIT --------------------------------
     */

    @Test
    public void parametritonKonstruktoriLuoOlion() {
        assertNotNull(uusiParametriton());
    }

    @Test
    public void kaksiparametrinenKonstruktoriLuoOlionKunKasvatussuhde2TaiYli() {
        assertNotNull(uusiParametreilla(6, 2));
        assertNotNull(uusiParametreilla(8, 3));
        assertNotNull(uusiParametreilla(1, 90));
        assertNotNull(uusiParametreilla(0, 9));
    }

    @Test
    public void AlkioidenLkmKasvaaKunLisataanEriLukuja() {
        alkioitaPitaisiOlla(0);

        int laskuri = 0;
        for (int i=8; i>-19; i--) {
            taulukko.lisaa(i);
            laskuri++;
            alkioitaPitaisiOlla(laskuri);
        }
    }

    @Test
    public void AlkioidenLkmKasvaaYhdellaKunLisataanSamaaLukuaMonesti() {
        alkioitaPitaisiOlla(0);

        for (int i=8; i>-19; i--) {
            taulukko.lisaa(7);
        }

        alkioitaPitaisiOlla(1);
    }

    @Test
    public void lisaaminenJaPoistaminenMuuttavatAlkioidenMaaraaOikein() {
        int laskuri = 0;

        lisaaTaulukkoonNLukua(15);
        laskuri = laskuri + 15;
        alkioitaPitaisiOlla(laskuri);

        poistaTaulukostaNLukua(4);
        laskuri = laskuri - 4;
        alkioitaPitaisiOlla(laskuri);

        lisaaTaulukkoonNLukua(6);
        laskuri = laskuri + 4;
        alkioitaPitaisiOlla(laskuri);

        taulukko.poistaYksiEsiintyma(900);
        alkioitaPitaisiOlla(laskuri);
    }

}