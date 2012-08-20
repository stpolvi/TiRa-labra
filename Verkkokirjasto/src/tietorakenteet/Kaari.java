
package tietorakenteet;

/**
 *
 * @author silja
 */
public class Kaari implements Comparable<Kaari> {

    public final int ALKUPISTE, LOPPUPISTE;

    public Kaari(int alkupiste, int loppupiste) {
        this.ALKUPISTE = alkupiste;
        this.LOPPUPISTE = loppupiste;
    }

    public int compareTo(Kaari k) {
        if (this.ALKUPISTE < k.ALKUPISTE) return -1;
        if (this.ALKUPISTE > k.ALKUPISTE) return 1;
        return this.LOPPUPISTE - k.LOPPUPISTE;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;

        Kaari k = (Kaari) o;
        return this.ALKUPISTE == k.ALKUPISTE && this.LOPPUPISTE == k.LOPPUPISTE;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.ALKUPISTE;
        hash = 59 * hash + this.LOPPUPISTE;
        return hash;
    }

}
