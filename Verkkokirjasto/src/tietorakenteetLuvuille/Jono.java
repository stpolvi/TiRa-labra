
package tietorakenteetLuvuille;

/**
 * Tietorakenne, johon voi tallettaa int-lukuja.
 * Alkiot lisätään aina jonon loppuun, ja
 * jonosta pois poimitaan pisimpään jonottanut alkio.
 * @author silja
 */

public class Jono {

    private int[] taulukko;
    private int head; //ensimmäisen alkion indeksi
    private int tail; //viimeisen alkion indeksi
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
        this.head = 1; //ensimmäisen alkion indeksi
        this.tail = 0; //viimeisen alkion indeksi
    }

    /**
     * Lisää luvun jonon viimeiseksi.
     * @param lisattava luku joka lisätään
     */

    public void lisaa(int lisattava) {
        if (taulukkoTaynna())
            kasvataTaulukkoa();
        int uudenPaikka = seuraavaIndeksi(tail);
        this.taulukko[uudenPaikka] = lisattava;
        this.tail = uudenPaikka;
    }

    /**
     * Poistaa ja palauttaa jonon ensimmäisen luvun.
     * @return kauiten jonottanut alkio
     */

    public int ota() {
        if (alkioita() == 0)
            throw new Error("Ottaminen tyhjästä jonosta");
        int vastaus = this.taulukko[head];
        this.head = seuraavaIndeksi(head);
        return vastaus;
    }

    /**
     * Jonossa olevien alkioiden lukumäärä.
     * @return jonon pituus
     */

    public int alkioita() {
        if (head == tail+1) return 0;
        if (head <= tail) return tail-head+1;
        return this.taulukko.length - (head-tail-1);
    }

    /*
     * Privaattimetodit ---------------------------------------
     */

    private boolean taulukkoTaynna() {
        return this.alkioita() == this.taulukko.length - 1;
    }

    private void kasvataTaulukkoa() {
        int[] uusi = new int[alkioita() * 2];
        tyokalut.Tyokalut.kopioiTaulukkoToiseen(this.taulukko, uusi);
        this.taulukko = uusi;
    }

    private int seuraavaIndeksi(int indeksi) {
        if (indeksi == this.taulukko.length - INDEKSIKORJAUS)
            return 0;
        else
            return indeksi + 1;
    }
    
}
