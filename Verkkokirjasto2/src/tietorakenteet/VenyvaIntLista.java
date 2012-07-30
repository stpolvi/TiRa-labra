package tietorakenteet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author silja
 */
public class VenyvaIntLista {

    private int oletuspituus = 100;
    private int oletuskasvatussuhde = 2;
    private int[] taulukko;

    public VenyvaIntLista() {
        this.taulukko = new int[oletuspituus];
    }

    private void kasvata() {
        int[] vanha = this.taulukko;
        int[] uusi = new int[uusiPituus(vanha)];
        kopioiUuteen(vanha, uusi);
    }

    boolean sisaltaa(int loppupiste) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    void lisaa(int loppupiste) {

    }

    private int uusiPituus(int[] vanha) {
        return vanha.length * oletuskasvatussuhde;
    }

    private void kopioiUuteen(int[] vanha, int[] uusi) {

    }

}
