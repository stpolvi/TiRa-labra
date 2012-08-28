
package tietorakenteetLuvuille;

import tyokalut.Tyokalut;

/**
 * Venyvä int-taulukko, jonka pituus voi olla suurempi kuin alkioiden lukumäärä.
 * Kapseloi javan perustaulukkorakenteen int[], jota pidentää
 * lisättäessä kaksinkertaiseksi.
 * Kapseloitu taulukko voidaan katkaista oikean pituiseksi metodilla.
 *
 * Lisääminen keskimäärin ?????, poisto O(1)
 * järjestäminen toteutetaan O(n log n)
 * etsiminen: -jos järjestyksessä, binäärihaulla O(log n)
 * -muutoin peräkkäishaulla O(n)
 * @author silja
 */

public class VenyvaTaulukko implements IntSailio {

    private int[] taulukko;
    private final int INDEKSIKORJAUS = 1;
    private int alkioita;

    /**
     * Kerroin, jolla täyteen tulleen taulukon koko kasvaa lisättäessä.
     * Oletuksena 2, jolloin taulukon koko kaksinkertaistuu.
     */

    public final int KASVATUSSUHDE;

    /*
     * KONSTRUKTORIT -------------------------------
     */

    /**
     * Taulukko, jonka aloituskapasiteetti viisi. Kasvaa lisättäessa kertoimella 2.
     */

    public VenyvaTaulukko() {
        this(5, 2);
    }

    /**
     * Taulukko, jonka aloituskapasiteetti ja kasvatussuhde annetaan.
     * Kasvatussuhteeksi on annettava vähintään kaksi, muutoin olion luonti
     * epäonnistuu ja kaataa ohjelman.
     * @param kapasiteetti kuinka monta alkiota mahtuu ennen ensimmäistä kasvatusta
     * @param kasvatussuhde kerroin jolla taulukko kasvaa
     */
    
    public VenyvaTaulukko(int kapasiteetti, int kasvatussuhde) {
        tarkistaKasvatussuhteenKelpaavuus(kasvatussuhde);
        KASVATUSSUHDE = kasvatussuhde;
        this.taulukko = new int[kapasiteetti];
        this.alkioita = 0;
    }

    /*
     * IntSailion metodien toteutukset ------------------------------
     */

    /**
     * Alkioiden lukumäärä.
     * @return taulukossa olevien lukujen määrä
     */

    public int alkioita() {
        return this.alkioita;
    }

    /**
     * Lisää alkion taulukkoon.
     * Kasvattaa taulukkoa kaksinkertaiseksi jos tarvis: vähemmän kuin O(n)
     * Yhteensä: noin O(n) ??
     * @param lisattava lisättävä alkio
     */

    public void lisaa(int lisattava) {
        kasvataTaulukkoaJosTaynna();
        this.taulukko[viimeisenAlkionIndeksi() + 1] = lisattava;
        alkioita++;
    }

    /**
     * Katkaisee kapseloidun taulukon alkioiden lukumäärää vastaavaksi
     * ja pikajärjestää sen eli käyttää quicksortia.
     */

    public void jarjesta() {
        int[] uusi = taulukkoKatkaistuna();
        Tyokalut.pikajarjesta(uusi);
        this.taulukko = uusi;
    }

    /**
     * Tämän säiliön alkiot int-taulukkona.
     * O(1) jos taulukko on järjestetty (jarjesta),
     * O(n) muuten
     * @return
     */

    public int[] toIntArray() {
        return taulukkoKatkaistuna();
    }

    /**
     * Etsii alkiota taulukosta käyttäen binäärihakua.
     * Huomaa ettei metodi toimi järjestämättömässä taulukossa.
     * @param etsittava alkio jota etsitään
     * @return true jos alkio löytyi, false muuten
     */

    public boolean etsi(int etsittava) {
        return Tyokalut.etsiBinaarihaulla(etsittava, 0, this.alkioita-1, this.taulukko);
    }

    /**
     * Etsii alkiota taulukosta käyttäen peräkkäishakua.
     * @param etsittava alkio jota etsitään
     * @return true jos alkio löytyi, false muuten
     */

    public boolean etsiPerakkaishaulla(int etsittava) {
        return Tyokalut.etsiPerakkaishaulla(etsittava, 0, this.alkioita-1, this.taulukko);
    }
    
    /*
     * PUBLIC-METODIT ---------------------------------
     */

//    /**
//     * Poistaa taulukosta alkioitten ylimääräiset esiintymät niin että kaikkia
//     * alkioita on tasan yksi esiintymä taulukossa. Alkioiden lukumäärä
//     * pienenee vastaavasti.
//     */
//
//    public void poistaLiiatEsiintymat() {
//        jarjesta();
//        for (int i=0; i<alkioita-1; i++) {
//            if (taulukko[i] == taulukko[i+1]) {
//                if (i+2 < alkioita) taulukko[i+1] = taulukko[i+2];
//                alkioita--;
//            }
//        }
//    }
    
    /**
     * Poistaa alkion yhden esiintymän taulukosta. Taulukon koko ei muutu, mutta
     * alkioiden lukumäärä pienenee yhdellä.
     * O(1)
     * @param poistettava alkio joka poistetaan
     */

    public void poistaYksiEsiintyma(int poistettava) {
        int poistettavanIndeksi 
                = Tyokalut.perakkaishae(poistettava, 0, this.alkioita, this.taulukko);
        if (poistettavanIndeksi < 0) return;

        siirraAlkio(viimeisenAlkionIndeksi(), poistettavanIndeksi);
        this.alkioita--;
    }

    /*
     * PRIVAATTIMETODIT ---------------------------------
     */

    private void siirraAlkio(int siirrettavanIndeksi, int uusiPaikka) {
        taulukko[uusiPaikka] = taulukko[siirrettavanIndeksi];
    }

    private int viimeisenAlkionIndeksi() {
        return alkioita-INDEKSIKORJAUS;
    }

    private boolean taulukkoTaynna() {
        return alkioita == taulukonKapasiteetti();
    }

    private int taulukonKapasiteetti() {
        return this.taulukko.length;
    }

    private void kasvataTaulukkoaJosTaynna() {
        if (!taulukkoTaynna()) return;
        int[] uusi = new int[uudenPituus()];
        tyokalut.Tyokalut.kopioiTaulukkoToiseen(this.taulukko, uusi);
        this.taulukko = uusi;
    }

    private int uudenPituus() {
        if (taulukonKapasiteetti() == 0)
            return KASVATUSSUHDE;
        return KASVATUSSUHDE*taulukonKapasiteetti();
    }

    private void tarkistaKasvatussuhteenKelpaavuus(int kasvatussuhde) {
        if (kasvatussuhde < 2)
            throw new IllegalArgumentException("Kasvatussuhde ei voi olla " +
                    kasvatussuhde + ", sen on oltava 2 tai suurempi.");
    }

    private int[] taulukkoKatkaistuna() {
        if (taulukkoTaynna())
            return this.taulukko;
        int[] palautettava = new int[alkioita];
        Tyokalut.kopioiTaulukkoToiseen(this.taulukko, palautettava);
        return palautettava;
    }

}
