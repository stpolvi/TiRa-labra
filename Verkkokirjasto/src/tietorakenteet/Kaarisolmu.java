/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tietorakenteet;

/**
 *
 * @author silja
 */
public class Kaarisolmu {

    private Kaari k;
    private Kaarisolmu vasen, oikea;

    public Kaarisolmu(Kaari k, Kaarisolmu vasenLapsi, Kaarisolmu oikeaLapsi) {
        this.k = k;
        this.vasen = vasenLapsi;
        this.oikea = oikeaLapsi;
    }

    public Kaari getKaari() {
        return this.k;
    }

    public Kaarisolmu getOikeaLapsi() {
        return this.oikea;
    }

    public Kaarisolmu getVasenLapsi() {
        return this.vasen;
    }

}
