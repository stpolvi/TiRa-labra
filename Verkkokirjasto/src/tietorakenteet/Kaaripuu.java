/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package tietorakenteet;

/**
 * Kaaripuu on binäärihakupuu, johon voi tallettaa Kaaria.
 * @author silja
 */
public class Kaaripuu {

    private Kaari[] taulu;
    int oletuskapasiteetti = 50;
    int kasvatussuhde = 2;

    /**
     *
     * @param oletuskapasiteetti
     */

    public Kaaripuu() {
        this.taulu = new Kaari[oletuskapasiteetti];
    }

    /**
     *
     * @param k
     */

    public void lisaa(Kaari k) {
        int paikka = haePaikka(k);
        if (taulu[paikka] != null) return;
        taulu[paikka] = k;
    }

    private void kasvataTaulukkoa() {

    }

    public boolean etsi(Kaari k) {
        return taulu[haePaikka(k)] != null;
    }

    /**
     * privaatti.
     * hakee annetulle kaarelle paikan puusta.
     * mikäli vastaava kaari oli jo puussa, palauttaa sen indeksin.
     * mikäli puussa ei ole jo vastaavaa kaarta, palauttaa paikan
     * johon se voidaan lisätä.
     * @param k
     * @return
     */

    private int haePaikka(Kaari k) {
        return haeRekursiolla(k, 1);
    }

    /**
     * TODO: ei vielä binäärihakupuu
     * @param k
     * @param aloituskohta
     * @return
     */

    private int haeRekursiolla(Kaari k, int aloituskohta) {
        Kaari tassaPaikassa = taulu[aloituskohta];
        if (tassaPaikassa == null || tassaPaikassa.equals(k))
            return aloituskohta;
        int seuraavaAloituskohta;
        if (tassaPaikassa.compareTo(k) > 0) 
            seuraavaAloituskohta = vasemmanLapsenIndeksi(aloituskohta);
        else
            seuraavaAloituskohta = oikeanLapsenIndeksi(aloituskohta);
        
        kasvataTaulukkoaJosIndeksiMeneeYli(seuraavaAloituskohta);
        return haeRekursiolla(k, seuraavaAloituskohta);
    }

    private int vasemmanLapsenIndeksi(int indeksi) {
        return 2*indeksi;
    }

    private int oikeanLapsenIndeksi(int indeksi) {
        return 2*indeksi + 1;
    }

    private void kasvataTaulukkoaJosIndeksiMeneeYli(int indeksi) {
        if (indeksi+1 < this.taulu.length) return;

        Kaari[] uusi = new Kaari[this.taulu.length * kasvatussuhde];
        tyokalut.Tyokalut.kopioiOlioTaulukkoToiseen(this.taulu, uusi);
        this.taulu = uusi;
    }
    
}
