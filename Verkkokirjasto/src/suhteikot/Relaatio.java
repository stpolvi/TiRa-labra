/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package suhteikot;

import tietorakenteet.IntSailio;

/**
 * Abstrakti relaatio.
 * @author silja
 */
public abstract class Relaatio {

    /**
     * Lisää yhteyden pisteiden välille.
     * @param alkupiste piste josta lisättävä yhteys
     * @param loppupiste piste johon lisättävä yhteys
     */

    public abstract void lisaaYhteys(int alkupiste, int loppupiste);

    /**
     * Pisteen seuraajat int-säiliössä.
     * @param piste piste jonka seuraajat
     * @return seuraajat jossakin int-säiliössä
     */

    public abstract IntSailio getSeuraajat(int piste);

    /**
     * Onko pisteiden välillä yhteys.
     * @param alkupiste piste josta yhteys olisi
     * @param loppupiste piste johon yhteys olisi
     * @return oliko yhteyttä alkupisteestä loppupisteeseen
     */

    public abstract boolean onYhteys(int alkupiste, int loppupiste);

}
