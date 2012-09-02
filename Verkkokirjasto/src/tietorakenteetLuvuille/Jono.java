
package tietorakenteetLuvuille;

import tyokalut.Tyokalut;

/**
 * Tietorakenne, johon voi tallettaa int-lukuja.
 * Alkiot lisätään aina jonon loppuun, ja
 * jonosta pois poimitaan pisimpään jonottanut alkio.
 * Toteutus on taulukkopohjainen, jolloin lisättäessä
 * saatetaan joutua kasvattamaan taulukkoa. Taulukon koko kuitenkin aina
 * kaksinkertaistuu, jolloin aikavaativuus pysyy kohtuullisena.
 * @author silja
 */

public class Jono {

    private int[] taulukko;
    private int eka; //ensimmäisen alkion indeksi
    private int vika; //viimeisen alkion indeksi
    private final int INDEKSIKORJAUS = 1;

    /**
     * Uuden taulukon oletuskapasiteetti ennen kasvatusta.
     */

    public final int OLETUSKAPASITEETTI = 51;

    /**
     * Luo uuden tyhjän jonon, jonka oletuskapasiteetti on 50 lukua.
     * Jos jonoon lisätään enemmän alkioita,
     * se kasvaa kapasiteetiltaan noin kaksinkertaiseksi.
     * Kasvatus O(n)
     */

    public Jono() {
        this.taulukko = new int[OLETUSKAPASITEETTI];
        this.eka = 1; //ensimmäisen alkion indeksi
        this.vika = 0; //viimeisen alkion indeksi alussa ekan "väärällä" puolella
    }

    /**
     * Lisää luvun jonon viimeiseksi.
     * @param lisattava luku joka lisätään
     */

    public void lisaa(int lisattava) {
        if (taulukkoTaynna())
            kasvataTaulukkoa();
        int uudenPaikka = seuraavaIndeksi(vika);
        this.taulukko[uudenPaikka] = lisattava;
        this.vika = uudenPaikka;
    }

    /**
     * Poistaa ja palauttaa jonon ensimmäisen luvun.
     * @return kauiten jonottanut alkio
     */

    public int ota() {
        if (alkioita() == 0)
            throw new Error("Ottaminen tyhjästä jonosta");
        int vastaus = this.taulukko[eka];
        this.eka = seuraavaIndeksi(eka);
        return vastaus;
    }

    /**
     * Jonossa olevien alkioiden lukumäärä.
     * @return jonon pituus
     */

    public int alkioita() {
        if (eka == vika+1) return 0;
        if (eka <= vika) return vika-eka+1;
        return this.taulukko.length - (eka-vika-1);
    }

    /*
     * Privaattimetodit ---------------------------------------
     */

    private boolean taulukkoTaynna() {
        return this.alkioita() == this.taulukko.length - 1;
    }

    private void kasvataTaulukkoa() {
        int[] uusi = new int[this.taulukko.length *2];
        
        if (eka <= vika)
            Tyokalut.kopioiTaulukkoToiseen(this.taulukko, uusi);
        else
            kopioiTaulukkoUuteenSiirtaenLoppupaata(uusi);

        this.taulukko = uusi;
    }

        private void kopioiTaulukkoUuteenSiirtaenLoppupaata(int[] uusi) {
            for (int i=0; i<=this.vika; i++)
                uusi[i] = this.taulukko[i];

            int siirto = uusi.length - this.taulukko.length;
            for (int i=this.eka; i<this.taulukko.length; i++) {
                uusi[i + siirto] = this.taulukko[i];
            }
        }

    private int seuraavaIndeksi(int indeksi) {
        if (indeksi == this.taulukko.length - INDEKSIKORJAUS)
            return 0;
        return indeksi + 1;
    }
    
}
