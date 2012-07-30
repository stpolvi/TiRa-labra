/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tietorakenteet;

/**
 *
 * @author silja
 */
public class Lukujoukko {

    private int koko;
    private int kasvatussuhde = 2;
    private int[] joukko;

    private final int INDEKSIKORJAUS = 1;

    public Lukujoukko() {
        joukko = new int[100];
        koko = 0;
    }

    public int getKoko() {
        return this.koko;
    }

    public boolean lisaa(int alkio) {
        int etsiPaikka;

        

        return false;
    }

    public boolean onAlkio(int alkio) {
        return binhae(alkio) >= 0;
    }

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
