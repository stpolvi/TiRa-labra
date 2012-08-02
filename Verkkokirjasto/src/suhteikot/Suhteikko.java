/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package suhteikot;

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
    
}
