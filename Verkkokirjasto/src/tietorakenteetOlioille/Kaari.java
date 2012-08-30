
package tietorakenteetOlioille;

/**
 * Pistepari, jonka pisteitä kutsutaan alku- ja loppupisteeksi.
 * @author silja
 */
public class Kaari implements Comparable<Kaari> {

    /**
     * Kaaren alkupiste.
     */

    public final int ALKUPISTE;
    
    /**
     * Kaaren loppupiste.
     */
    
    public final int LOPPUPISTE;

    /**
     * Uusi kaari annetuilla pisteillä.
     * @param alkupiste kaaren alkupiste
     * @param loppupiste kaaren loppupiste
     */

    public Kaari(int alkupiste, int loppupiste) {
        this.ALKUPISTE = alkupiste;
        this.LOPPUPISTE = loppupiste;
    }

    /**
     * Vertailee kaaria ensisijaisesti alkupisteen ja toissijaisesti
     * loppupisteen perusteella. Null on kaikkia kaaria pienempi.
     * @param k verrattava kaari
     * @return negatiivinen jos tämä on pienempi kuin verrattava,
     * positiivinen jos suurempi, nolla jos samankokoiset.
     */

    public int compareTo(Kaari k) {
        if (k == null) return 1;
        if (this.ALKUPISTE < k.ALKUPISTE) return -1;
        if (this.ALKUPISTE > k.ALKUPISTE) return 1;
        return this.LOPPUPISTE - k.LOPPUPISTE;
    }

    /**
     * Ovatko kaaret samat: alku- ja loppupisteet samat.
     * @param o verrattava olio
     * @return true jos verrattava on kaari, jonka alkupiste sama kuin
     * tällä ja loppupiste sama kuin tällä. Muuten false.
     */

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;

        Kaari k = (Kaari) o;
        return this.ALKUPISTE == k.ALKUPISTE && this.LOPPUPISTE == k.LOPPUPISTE;
    }

    /**
     * Kaaren hashCode.
     * @return tämän kaaren hajautusarvo
     */

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.ALKUPISTE;
        hash = 29 * hash + this.LOPPUPISTE;
        return hash;
    }

}
