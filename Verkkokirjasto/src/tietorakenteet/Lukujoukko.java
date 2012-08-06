
package tietorakenteet;

/**
 * Kuvaa kokonaislukujoukkoa. Joukosta voi poistaa, sinne voi lisätä,
 * ja kysyä onko jokin luku joukon alkio. Luku joko on alkio tai ei,
 * mutta se ei voi esiintyä joukossa useampaa kertaa.
 * @author silja
 */
public class Lukujoukko {

    private int koko;
    private int kasvatussuhde = 2;
    private int[] joukko;

    private final int INDEKSIKORJAUS = 1;

    /**
     * Tyhjä joukko, johon mahtuu aluksi sata lukua.
     */
    public Lukujoukko() {
        joukko = new int[100];
        koko = 0;
    }

    /**
     * Kuinka monta alkiota joukossa on
     * @return alkioiden lukumäärä
     */
    public int getKoko() {
        return this.koko;
    }

    /**
     * Lisää luvun joukkoon. --kesken--
     * @param alkio lisättävä
     * @return onnistuiko lisäys: false jos lisättävä oli jo joukossa
     */
    public boolean lisaa(int alkio) {
        int i = this.koko-INDEKSIKORJAUS;
        while (joukko[i] > alkio) {
            i--;
        }
        if (joukko[i] == alkio) return false;
        joukko[i+1] = alkio;
        this.koko++;
        return true;
    }

    /**
     * Onko annettu luku joukon alkio
     * @param alkio kysymyksessä oleva luku
     * @return löytyikö luku joukosta
     */

    public boolean onAlkio(int alkio) {
        return binhae(alkio) >= 0;
    }

    /**
     * Privaatti binäärihaku joukosta
     * @param alkio
     * @return
     */

    private int binhae(int alkio) {
        int vasen = 0;
        int oikea = this.koko-INDEKSIKORJAUS;

        if (joukko[vasen] == alkio) return vasen;
        if (joukko[oikea] == alkio) return oikea;

        int keski;

        while (oikea > vasen) {
            keski = Tyokalut.keskiarvo(vasen, oikea);

            if (joukko[keski] < alkio) vasen = keski;
            else if (joukko[keski] > alkio) oikea = keski;
            else return keski;
        }
        
        return -1;
    }


}
