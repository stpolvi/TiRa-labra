
package tietorakenteet;

import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author silja
 */
public class VenyvaTaulukkoTehokasTest {

    VenyvaTaulukko taulukko;

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

    void lisaaTaulukkoonNLukua(int n) {
        for (int i=1; i<=n; i++) {
            taulukko.lisaa(i);
        }
    }

    /**
     * Apumetodi poistaa taulukosta luvut 1,...,n
     * @param n poistettavien lukumäärä
     */

    void poistaTaulukostaNLukua(int n) {
        for (int i=1; i<=n; i++) {
            taulukko.poista(i);
        }
    }

    /**
     * Apumetodi lisää taulukkoon n satunnaista int-lukua.
     * @param n lisättävien määrä
     */

    void lisaaTaulukkoonNSatunnaistaLukua(int n) {
        for (int i=1; i<=n; i++) {
            Random r = new Random();
            taulukko.lisaa(r.nextInt());
        }
    }

    /**
     * Apumetodi vertaa taulukon alkioiden määrää annettuun lukuun.
     * @param oikeaAlkioidenLkm kuinka monta alkioita pitäisi olla
     */

    void alkioitaPitaisiOlla(int oikeaAlkioidenLkm) {
        assertTrue("Alkioita pitäisi olla " + oikeaAlkioidenLkm +
                    " mutta niitä oli " + taulukko.alkioita(),
                    taulukko.alkioita() == oikeaAlkioidenLkm);
    }

    VenyvaTaulukko uusiParametriton() {
        return new VenyvaTaulukko();
    }

    VenyvaTaulukko uusiParametreilla(int kapasiteetti, int kasvatussuhde) {
        return new VenyvaTaulukko(kapasiteetti, kasvatussuhde);
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
    public void Uudessa0Alkiota() {
        alkioitaPitaisiOlla(0);
    }

    @Test
    public void AlkioidenLkmKasvaaKunLisataan() {
        alkioitaPitaisiOlla(0);

        int laskuri = 0;
        for (int i=8; i>-19; i--) {
            taulukko.lisaa(i);
            laskuri++;
            alkioitaPitaisiOlla(laskuri);
        }
    }

    /**
     * Etsi-metodi hyödyntää suoraan binhakua, minkä takia etsi-metodin 
     * testit kattavat myös binhaun testauksen.
     */

    @Test
    public void lisaaYksiKerranJaEtsi() {
        taulukko.lisaa(5);
        taulukko.jarjesta();
        assertTrue(taulukko.etsi(5));
    }

    @Test
    public void lisaaYksiKahdestiJaEtsi() {
        taulukko.lisaa(19);
        taulukko.lisaa(19);
        taulukko.jarjesta();
        assertTrue(taulukko.etsi(19));
    }

    @Test
    public void lisaa10PerakkaistaLukuaKerranJaEtsi() {
        lisaaTaulukkoonNLukua(10);
        taulukko.jarjesta();
        for (int i=1; i<=10; i++) {
            assertTrue("Luku " + i + " puuttui.", taulukko.etsi(i));
        }
    }

    @Test
    public void lisaaYksiKerranJaPerakkaishae() {
        taulukko.lisaa(251);
        assertTrue(taulukko.etsiPerakkaishaulla(251));
    }

    @Test
    public void lisaaYksiKahdestiJaPerakkaishae() {
        taulukko.lisaa(1699);
        taulukko.lisaa(1699);
        assertTrue(taulukko.etsiPerakkaishaulla(1699));
    }

    @Test
    public void lisaa10PerakkaistaLukuaKerranJaPerakkaishae() {
        lisaaTaulukkoonNLukua(10);
        for (int i=1; i<=10; i++) {
            assertTrue("Luku " + i + " puuttui.", taulukko.etsiPerakkaishaulla(i));
        }
    }

    @Test
    public void poistaminenTyhjastaEiKaadu() {
        poistaTaulukostaNLukua(14);
    }

    @Test
    public void lisaaJaPoistaYksiSamaAlkioJolloinAlkioita0() {
        taulukko.lisaa(4);
        alkioitaPitaisiOlla(1);

        taulukko.poista(4);
        alkioitaPitaisiOlla(0);
    }

    @Test
    public void lisaaJaPoista10SamaaAlkiotaJolloinAlkioita0() {
        lisaaTaulukkoonNLukua(10);
        alkioitaPitaisiOlla(10);

        poistaTaulukostaNLukua(10);
        alkioitaPitaisiOlla(0);
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

        taulukko.poista(900);
        alkioitaPitaisiOlla(laskuri);
    }

}