
package suhteikot;

import tietorakenteet.IntSailio;

/**
 * Abstrakti suhteikko, jota voidaan tutkia suhteikkokirjaston algoritmeilla.
 * @author silja
 */
public abstract class Suhteikko {

    /**
     * Suhteikon pisteiden lukumäärä
     * @return montako pistettä suhteikossa on
     */
    public abstract int pisteidenLkm();
    /**
     * Onko alkupisteestä loppupisteeseen yhteys.
     * @param alkupiste piste josta yhteys
     * @param loppupiste piste johon yhteys
     * @return oliko yhteyttä
     */

    public abstract boolean onYhteys(int alkupiste, int loppupiste);
    
    /**
     * Annetun pisteen seuraajat int-säiliössä.
     * @param alkupiste piste jonka seuraajat halutaan
     * @return seuraajat jossakin int-säiliössä
     */

    public abstract IntSailio getSeuraajat(int alkupiste);
    
}
