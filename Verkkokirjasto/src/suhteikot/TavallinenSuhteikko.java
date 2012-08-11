
package suhteikot;

import relaatiot.Relaatio;
import tietorakenteet.IntSailio;

/**
 * TavallinenSuhteikko-olio kuvaa suhteikkoa (eli ns. suunnattua verkkoa),
 * joka pisteet ovat kokonaislukuja. Jos suhteikossa on n pistettä,
 * sen pisteet ovat 1,2,3,...,n. Suhteikko koostuu pisteistä ja niiden välisestä
 * Relaatiosta.
 * @author silja
 */
public class TavallinenSuhteikko extends Suhteikko {

    /**
     * Parametriton konstruktori luo tyhjän suhteikon:
     * pisteitä ei ole, ja niiden välinen relaatio on null.
     */

    public TavallinenSuhteikko() {
        super();
    }

    /**
     * Luo suhteikon annetun relaation perusteella.
     * Pistemäärä päätellään relaatiosta.
     * @param relaatio tulevan suhteikon pisteiden välinen relaatio
     */

    public TavallinenSuhteikko(Relaatio relaatio) {
        super(relaatio);
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
    public IntSailio getSeuraajat(int piste) {
        return this.relaatio.getSeuraajat(piste);
    }

    /**
     * Yliluokan Suhteikko metodin toteutus.
     * Kuinka monta seuraajaa pisteellä on.
     * @param piste analysoitava piste
     * @return seuraajien lukumäärä
     */

    public int seuraajienLkm(int piste) {
        IntSailio seuraajat = getSeuraajat(piste);
        if (seuraajat == null) return 0;
        return seuraajat.alkioita();
    }

    
}
