/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tietorakenteet;

/**
 *
 * @author silja
 */
public class LukujoukkoTehoton {

    private int koko;
    private int kasvatussuhde = 2;
    private int[] joukko;

    private final int INDEKSIKORJAUS = 1;

    public LukujoukkoTehoton() {
        this.joukko = new int[100];
        this.koko = 0;
    }

    public int getKoko() {
        return this.koko;
    }

    public boolean lisaa(int alkio) {
        if (onAlkio(alkio)) {
            return false;
        }

        if (taulukkoTaynna())
            kasvataTaulukkoa();

        joukko[koko] = alkio;
        koko++;
        return true;
    }

    public boolean poista(int alkio) {
        for (int i=0; i<this.koko; i++) {
            if (joukko[i] == alkio) {
                joukko[i] = joukko[koko - INDEKSIKORJAUS];
                koko--;
                return true;
            }
        }

        return false;
    }

    public boolean onAlkio(int alkio) {
        for (int i = 0; i < koko; i++) {
            if (joukko[i] == alkio) {
                return true;
            }
        }

        return false;
    }

    //privaattimetodit:

    private boolean taulukkoTaynna() {
        return this.koko == this.joukko.length;
    }

    private void kasvataTaulukkoa() {
        int[] vanha = this.joukko;
        int[] uusi = new int[uusiPituus(vanha)];
        kopioiUuteen(vanha, uusi);
        this.joukko = uusi;
    }

    private int uusiPituus(int[] vanha) {
        return vanha.length * kasvatussuhde;
    }

    private void kopioiUuteen(int[] vanha, int[] uusi) {
        for (int i=0; i<vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }
}
