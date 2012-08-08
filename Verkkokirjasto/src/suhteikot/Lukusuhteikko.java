
package suhteikot;

import relaatiot.Relaatio;
import tietorakenteet.IntSailio;

/**
 * Lukusuhteikko-olio kuvaa suhteikkoa (eli ns. suunnattua verkkoa),
 * joka pisteet ovat kokonaislukuja. Jos suhteikossa on n pistettä,
 * sen pisteet ovat 1,2,3,...,n. Suhteikko koostuu pisteistä ja niiden välisestä
 * Relaatiosta.
 * @author silja
 */
public class Lukusuhteikko extends Suhteikko {

    /**
     * Parametriton konstruktori luo tyhjän suhteikon:
     * pisteitä ei ole, ja niiden välinen relaatio on null.
     */

    public Lukusuhteikko() {
        super();
    }

    /**
     * Luo suhteikon annetun relaation perusteella.
     * Pistemäärä päätellään relaatiosta.
     * @param relaatio tulevan suhteikon pisteiden välinen relaatio
     */

    public Lukusuhteikko(Relaatio relaatio) {
        super(relaatio);
        relaatio.teeValmiiksi();
    }

    /**
     * Yliluokan Suhteikko abstraktin metodin toteutus:
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
     * Yliluokan Suhteikko metodin toteutus.
     * Annetun pisteen seuraajat int-säiliössä
     *
     * @param alkupiste piste jonka seuraajat halutaan
     * @return seuraajat jossakin int-säiliössä
     */
    @Override
    public IntSailio getSeuraajat(int alkupiste) {
        return this.relaatio.getSeuraajat(alkupiste);
    }

    
}
