/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lukusuhteikot;

import suhteikot.Suhteikko;

/**
 * Lukusuhteikko-olio kuvaa suhteikkoa (eli ns. suunnattua verkkoa),
 * joka pisteet ovat kokonaislukuja. Jos suhteikossa on n pistettä,
 * sen pisteet ovat 1,2,3,...,n. Suhteikko koostuu pisteistä ja niiden välisestä
 * relaatiosta, joka on Lukusuhteikkorelaatio-olio.
 * @author silja
 */
public class Lukusuhteikko extends Suhteikko {

    private final int PISTEIDENLKM;
    private Lukusuhteikkorelaatio relaatio;

    /**
     * Parametriton konstruktori luo tyhjän suhteikon:
     * pisteitä ei ole, ja niiden välinen relaatio on null.
     */
    public Lukusuhteikko() {
        this.PISTEIDENLKM = 0;
        this.relaatio = null;
    }

    /**
     * Luo suhteikon annetun relaation perusteella. Pistemäärä päätellään relaatiosta.
     *
     * @param relaatio suhteikon pisteiden välinen relaatio
     */

    public Lukusuhteikko(Lukusuhteikkorelaatio relaatio) {
        this.PISTEIDENLKM = relaatio.JOUKONKOKO;
        this.relaatio = relaatio;
    }

    /**
     * Yliluokan (Suhteikko) abstraktin metodin toteutus:
     * onko alkupisteestä yhteys loppupisteeseen.
     *
     * @param alkupiste piste josta yhteys mahdollisesti on
     * @param loppupiste piste johon yhteys mahdollisesti on
     * @return oliko annettujen pisteiden välillä yhteys
     */

    @Override
    public boolean onYhteys(int alkupiste, int loppupiste) {
        if (this.relaatio == null) return false;
        return relaatio.onYhteys(alkupiste,loppupiste);
    }

    /**
     * Suhteikon pisteiden lukumäärä
     *
     * @return pisteiden määrä
     */

    @Override
    public int pisteidenLkm() {
        return this.PISTEIDENLKM;
    }

    
}