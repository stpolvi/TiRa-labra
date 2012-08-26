
package tietorakenteetLuvuille;

import tyokalut.Tyokalut;

/**
 * Tietorakenne, johon voi tallettaa int-lukuja.
 * Alkiot lisätään aina pinon päälle, ja
 * pinosta pois poimitaan päällimmäinen alkio.
 * @author silja
 */

public class Pino {

    private int[] taulukko;
    private int ylin; //päällimmäisen alkion indeksi
    private final int INDEKSIKORJAUS = 1;

    /**
     * Uuden taulukon oletuskapasiteetti ennen kasvatusta.
     */

    public final int OLETUSKAPASITEETTI = 50;

    /**
     * Luo uuden tyhjän pinon, jonka oletuskapasiteetti on 50 lukua.
     * Jos pinoon lisätään enemmän alkioita,
     * se kasvaa kapasiteetiltaan kaksinkertaiseksi.
     * Kasvatus O(n)
     */

    public Pino() {
        this.taulukko = new int[OLETUSKAPASITEETTI];
        this.ylin = -1; //päällimmäistä alkiota ei aluksi ole
    }

    /**
     * Lisää luvun pinon päälle.
     * @param lisattava luku joka lisätään
     */

    public void lisaa(int lisattava) {
        if (taulukkoTaynna())
            kasvataTaulukkoa();
        this.taulukko[++ylin] = lisattava;
    }

    /**
     * Poistaa ja palauttaa uusimman pinossa olevan luvun.
     * @return päällimmäinen alkio
     */

    public int ota() {
        if (alkioita() == 0)
            throw new Error("Ottaminen tyhjästä pinosta");
        return taulukko[ylin--];
    }

    /**
     * Pinossa olevien alkioiden lukumäärä.
     * @return pinon korkeus
     */

    public int alkioita() {
        return ylin + INDEKSIKORJAUS;
    }

    /*
     * Privaattimetodit ---------------------------------------
     */

    private boolean taulukkoTaynna() {
        return this.alkioita() == this.taulukko.length - 1;
    }

    private void kasvataTaulukkoa() {
        int[] uusi = new int[alkioita() * 2];
        Tyokalut.kopioiTaulukkoToiseen(this.taulukko, uusi);
        this.taulukko = uusi;
    }

}
