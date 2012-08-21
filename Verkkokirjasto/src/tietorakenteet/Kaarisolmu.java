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
    private Kaarisolmu emo, vasen, oikea;

    public Kaarisolmu(Kaari k, Kaarisolmu emo, Kaarisolmu vasenLapsi, Kaarisolmu oikeaLapsi) {
        this.k = k;
        this.emo = emo;
        this.vasen = vasenLapsi;
        this.oikea = oikeaLapsi;
    }

    public Kaari getKaari() {
        return this.k;
    }

    public Kaarisolmu getEmo() {
        return this.emo;
    }

    public Kaarisolmu getOikeaLapsi() {
        return this.oikea;
    }

    public Kaarisolmu getVasenLapsi() {
        return this.vasen;
    }

    public void setEmo(Kaarisolmu s) {
        this.emo = s;
    }

    public void setVasenLapsi(Kaarisolmu s) {
        this.vasen = s;
    }

    public void setOikeaLapsi(Kaarisolmu s) {
        this.oikea = s;
    }
}
