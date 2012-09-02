
package kaaritietorakenteet;

/**
 * Kaarisolmulla on kaari, emo, vasen lapsi ja oikea lapsi.
 * Näistä mikä tahansa voi olla null, vaikka kaaren ei ole mielekästä olla null.
 * Kaarisolmu on suunniteltu molempiin suuntiin linkitetyn binääripuun solmuksi,
 * mutta sitä voi käyttää myös yhteen suuntaan linkitetyssä rakenteessa
 * jättämällä solmujen emot huomiotta.
 * Kaarisolmuun ei ole toteutettu tarkistuksia sen suhteen, onko tämä solmu
 * todella annetun emosolmun lapsi. Emon asettaminen oikein jätetään
 * käyttäjän vastuulle.
 * @author silja
 */

public class Kaarisolmu {

    private Kaari k;
    private Kaarisolmu emo, vasen, oikea;

    /**
     * Luo uuden solmun annetuilla parametreilla.
     *
     * Aikavaativuus O(1), tilavaativuus O(1).
     * @param k Kaari, joka talletetaan solmuun
     * @param emo Kaarisolmu, jonka lapsi tämä solmu on
     * @param vasenLapsi tämän solmun vasen lapsi
     * @param oikeaLapsi tämän solmun oikea lapsi
     */

    public Kaarisolmu(Kaari k, Kaarisolmu emo, Kaarisolmu vasenLapsi, Kaarisolmu oikeaLapsi) {
        this.k = k;
        this.emo = emo;
        this.vasen = vasenLapsi;
        this.oikea = oikeaLapsi;
    }

    /**
     * Palauttaa tähän solmuun talletetun kaaren.
     *
     * Aikavaativuus O(1)
     * @return tämän solmun kaari
     */

    public Kaari getKaari() {
        return this.k;
    }

    /**
     * Palauttaa tämän solmun emosolmun.
     *
     * Aikavativuus O(1)
     * @return solmu, joka on asetettu tämän solmun emoksi
     */

    public Kaarisolmu getEmo() {
        return this.emo;
    }

    /**
     * Palauttaa tämän solmun oikean lapsen.
     *
     * Aikavaativuus O(1)
     * @return solmu, joka on asetettu tämän oikeaksi lapseksi
     */

    public Kaarisolmu getOikeaLapsi() {
        return this.oikea;
    }

    /**
     * Palauttaa tämän solmun vasemman lapsen.
     *
     * Aikavaativuus O(1)
     * @return solmu, joka on asetettu tämän vasemmaksi lapseksi
     */

    public Kaarisolmu getVasenLapsi() {
        return this.vasen;
    }

    /**
     * Asettaa tämän solmun emosolmuksi annetun solmun.
     *
     * Aikavaativuus O(1)
     * @param s solmu joka asetetaan emoksi
     */

    public void setEmo(Kaarisolmu s) {
        this.emo = s;
    }

    /**
     * Asettaa tämän solmun vasemmaksi lapseksi annetun solmun.
     *
     * Aikavaativuus O(1)
     * @param s solmu joka asetetaan lapseksi
     */

    public void setVasenLapsi(Kaarisolmu s) {
        this.vasen = s;
    }

    /**
     * Asettaa tämän solmun oikeaksi lapseksi annetun solmun.
     *
     * Aikavaativuus O(1)
     * @param s solmu joka asetetaan lapseksi
     */

    public void setOikeaLapsi(Kaarisolmu s) {
        this.oikea = s;
    }
}
