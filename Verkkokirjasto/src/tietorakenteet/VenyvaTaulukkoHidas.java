package tietorakenteet;

import tyokalut.Tyokalut;
import java.util.Arrays;

/**
 * Venyvä int-taulukko, jonka pituus on aina alkioiden lukumäärä.
 * Kapseloi javan perustaulukkorakenteen int[], jota pidentää
 * lisättäessä yhdellä ja lyhentää poistettaessa yhdellä.
 * Tietorakenne on suunniteltu hitaasti rakennettavaksi,
 * mutta tehokkaammin tutkittavaksi.
 *
 * Lisääminen O(n), poisto O(n)
 * järjestäminen toteutetaan quicksortilla
 * etsiminen: -jos järjestyksessä, binäärihaulla O(log n)
 * -muutoin peräkkäishaulla O(n)
 * @author silja
 */
public class VenyvaTaulukkoHidas implements IntSailio {

    private int[] taulukko;
    private final int INDEKSIKORJAUS = 1;


    /**
     * Taulukko, jonka aloituskapasiteetti nolla. Kasvaa lisättäessa.
     */

    public VenyvaTaulukkoHidas() {
        this.taulukko = new int[0];
    }

    /**
     * Int-säiliön metodin toteutus.
     * Lisää alkion taulukkoon, mikäli se ei jo ollut siellä.
     * Tarkistaa peräkkäishaulla oliko alkio jo taulukossa: O(n)
     * Kasvattaa taulukkoa: O(n)
     * Yhteensä: O(n)
     * @param lisattava lisättävä alkio
     */

    public void lisaa(int lisattava) {
        if (etsiPerakkaishaulla(lisattava)) return;
        muutaTaulukonPituutta(1);
        this.taulukko[viimeisenIndeksi()] = lisattava;
    }

    public int[] toIntArray() {
        return this.taulukko;
    }
    
    /**
     * Poistaa alkion taulukosta.
     * Etsii poistettavan indeksin peräkkäishaulla: O(n)
     * Lyhentää taulukkoa: O(n)
     * Yhteensä: O(n)
     * @param poistettava alkio joka poistetaan
     */

    public void poistaLyhentaen(int poistettava) {
        int poistettavanIndeksi = perakkaishae(poistettava);
        if (poistettavanIndeksi < 0) return;
        
        int tilalleTulevanIndeksi = viimeisenIndeksi();
        siirraAlkio(tilalleTulevanIndeksi, poistettavanIndeksi);
        muutaTaulukonPituutta(-1);
    }

    /**
     * Int-säiliön metodin toteutus.
     * Etsii alkiota taulukosta käyttäen binäärihakua.
     * Huomaa ettei metodi toimi järjestämättömässä taulukossa.
     * @param etsittava alkio jota etsitään
     * @return true jos alkio löytyi, false muuten
     */
    
    public boolean etsi(int etsittava) {
        return binhae(etsittava) >= 0;
    }
    
    /**
     * Binäärihaku venyvästä taulukosta. Huomaa ettei metodi toimi
     * järjestämättömässä venyvässä taulukossa.
     * @param etsittava haettava
     * @return indeksi josta etsittävä löytyi, tai -1 mikäli sitä ei löytynyt
     */

    public int binhae(int etsittava) {
        int vasen = 0;
        int oikea = alkioita()-INDEKSIKORJAUS;
        int keski;

        while (vasen <= oikea) {
            keski = Tyokalut.keskiarvo(vasen, oikea);

            if (taulukko[keski] == etsittava)
                return keski;
            else if (taulukko[keski] > etsittava)
                oikea = keski-1;
            else
                vasen = keski+1;
        }

        return -1;
    }

    /**
     * Etsii alkiota peräkkäishaulla.
     * Tämä haku ei vaadi että taulukko olisi järjestyksessä.
     * @param etsittava alkio jota etsitään
     * @return true jos löytyi, false muuten
     */

    public boolean etsiPerakkaishaulla(int etsittava) {
        return perakkaishae(etsittava) >= 0;
    }

    /**
     * Peräkkäishaku. Ei vaadi että taulukko olisi järjestyksessä.
     * @param haettava alkio jota haetaan
     * @return haettavan indeksi jos löytyi, muutoin -1
     */

    public int perakkaishae(int haettava) {
        for (int i=0; i<alkioita(); i++) {
            if (this.taulukko[i] == haettava) return i;
        }
        return -1;
    }

    /**
     * IntSailio-rajapinnan metodin toteutus.
     * @return taulukossa olevien lukujen määrä
     */

    public int alkioita() {
        return this.taulukko.length;
    }
    
    /**
     * jarjesta toteutetaan myöhemmin itse,
     * käytetty kirjastometodia binäärihaun testausta varten
     */

    public void jarjesta() {
        Arrays.sort(this.taulukko);
    }
    
    /*
     * PRIVAATTIMETODIT ALLA ---------------------------------
     */

    private void muutaTaulukonPituutta(int pituudenMuutos) {
        int[] uusi = new int[alkioita() + pituudenMuutos];
        kopioiUuteen(this.taulukko, uusi);
        this.taulukko = uusi;
    }

    private void kopioiUuteen(int[] vanha, int[] uusi) {
        int lyhemmanPituus = Tyokalut.minimi(vanha.length, uusi.length);
        for (int i=0; i<lyhemmanPituus; i++) {
            uusi[i] = vanha[i];
        }
    }

    private void siirraAlkio(int siirrettavanIndeksi, int uusiPaikka) {
        taulukko[uusiPaikka] = taulukko[siirrettavanIndeksi];
    }

    private int viimeisenIndeksi() {
        return alkioita()-INDEKSIKORJAUS;
    }

}
