/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lukusuhteikot;

import suhteikot.Suhteikko;

/**
 *
 * @author silja
 */
public class Lukusuhteikko extends Suhteikko {

    private final int PISTEIDENLKM;
    private Lukusuhteikkorelaatio relaatio;

    public Lukusuhteikko() {
        this.PISTEIDENLKM = 0;
        this.relaatio = null;
    }

    public Lukusuhteikko(Lukusuhteikkorelaatio relaatio) {
        this.PISTEIDENLKM = relaatio.JOUKONKOKO;
        this.relaatio = relaatio;
    }

    @Override
    public boolean onYhteys(int alkupiste, int loppupiste) {
        if (this.relaatio == null) return false;
        return relaatio.onYhteys(alkupiste,loppupiste);
    }

    @Override
    public int pisteidenLkm() {
        return this.PISTEIDENLKM;
    }

    
}
