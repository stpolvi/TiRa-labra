package tietorakenteet;



/**
 * Venyvä int-taulukko
 * @author silja
 */
public class VenyvaTaulukkoTuplaantuva implements IntSailio {

    private int oletuspituus = 100;
    private int oletuskasvatussuhde = 2;
    private int[] taulukko;
    private int alkioita;
    private final int INDEKSIKORJAUS = 1;

    /**
     * Venyvä lista oletuspituisella aloituskapasiteetilla.
     */
    public VenyvaTaulukkoTuplaantuva() {
        this.taulukko = new int[oletuspituus];
    }


    private void kasvata() {
        int[] vanha = this.taulukko;
        int[] uusi = new int[uusiPituus(vanha)];
        kopioiUuteen(vanha, uusi);
    }

    /**
     * KESKEN. ei toteutettu vielä
     * @param loppupiste
     * @return
     */

    public boolean etsi(int etsittava) {
        return binhae(etsittava) >= 0;
    }

    /**
     * KESKEN. ei toteutettu vielä
     */
    public void lisaa(int loppupiste) {

    }

    
    private int uusiPituus(int[] vanha) {
        return vanha.length * oletuskasvatussuhde;
    }

    private void kopioiUuteen(int[] vanha, int[] uusi) {
        throw new UnsupportedOperationException();
    }

    /**
     * Binäärihaku venyvästä taulukosta. Huomaa ettei metodi toimi
     * järjestämättömässä venyvässä taulukossa.
     * @param etsittava haettava
     * @return indeksi josta etsittävä löytyi, tai -1 mikäli sitä ei löytynyt
     */

    public int binhae(int etsittava) {
        int vasen = 0;
        int oikea = this.alkioita-INDEKSIKORJAUS;

        if (taulukko[vasen] == etsittava) return vasen;
        if (taulukko[oikea] == etsittava) return oikea;

        int keski;

        while (oikea > vasen) {
            keski = Tyokalut.keskiarvo(vasen, oikea);

            if (taulukko[keski] < etsittava) vasen = keski;
            else if (taulukko[keski] > etsittava) oikea = keski;
            else return keski;
        }

        return -1;

    }

    /**
     * IntSailio-rajapinnan metodin toteutus.
     * @return taulukossa olevien lukujen määrä
     */

    public int alkioita() {
        return this.alkioita;
    }

    /**
     * IntSailio-rajapinnan metodin toteutus.
     * @return alkiot taulukoituna
     */

    public int[] toIntArray() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
