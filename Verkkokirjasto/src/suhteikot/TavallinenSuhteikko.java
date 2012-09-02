
package suhteikot;

import relaatiot.Relaatio;
import tietorakenteetLuvuille.IntSailio;

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
     * pisteitä ei ole, ja relaatio on null.
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

    /*
     * Yliluokka Suhteikon metodien toteutukset ------------------------------
     */

    /**
     * Onko alkupisteestä yhteys loppupisteeseen.
     *
     * @param alkupiste piste josta yhteys mahdollisesti on
     * @param loppupiste piste johon yhteys mahdollisesti on
     * @return oliko annettujen pisteiden välillä yhteys
     */

    @Override
    public boolean onYhteys(int alkupiste, int loppupiste) {
        if (this.relaatio == null)
            return false;
        return relaatio.onYhteys(alkupiste,loppupiste);
    }
    
    /**
     * Annetun pisteen seuraajat int-säiliössä.
     * Aikavaativuus O(1), tilavaativuus O(1)
     * @param alkupiste piste jonka seuraajat halutaan
     * @return seuraajat jossakin int-säiliössä
     */

    @Override
    public IntSailio getSeuraajat(int piste) {
        return this.relaatio.getSeuraajat(piste);
    }

    /**
     * Kuinka monta seuraajaa pisteellä on.
     * Aikavaativuus O(1), tilavaativuus O(yhteyksienLkm)
     * @param piste analysoitava piste
     * @return seuraajien lukumäärä
     */

    public int seuraajienLkm(int piste) {
        IntSailio seuraajat = getSeuraajat(piste);
        if (seuraajat == null) return 0;
        return seuraajat.alkioita(); //O(1)
    }
    
}
