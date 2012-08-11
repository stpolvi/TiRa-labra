
package suhteikot;

import java.awt.Color;
import relaatiot.Relaatio;
import tietorakenteet.IntSailio;

/**
 * TavallinenSuhteikko-olio kuvaa suhteikkoa (eli ns. suunnattua verkkoa),
 * joka pisteet ovat kokonaislukuja. Jos suhteikossa on n pistettä,
 * sen pisteet ovat 1,2,3,...,n. Suhteikko koostuu pisteistä ja niiden välisestä
 * Relaatiosta. Tämän suhteikon pisteille voi määrittää värin, jota voi muuttaa.
 * @author silja
 */
public class VaritettavaSuhteikko extends Suhteikko {

    private Color[] varit;

    private final int INDEKSIKORJAUS = 1;

    /**
     * Parametriton konstruktori luo tyhjän suhteikon:
     * pisteitä ei ole, ja niiden välinen relaatio on null.
     * Värien kokoelma null.
     */

    public VaritettavaSuhteikko() {
        super();
        varit = null;
    }

    /**
     * Luo suhteikon annetun relaation perusteella.
     * Pistemäärä päätellään relaatiosta.
     * Kaikkien pisteiden väri on aluksi null.
     * @param relaatio tulevan suhteikon pisteiden välinen relaatio
     */

    public VaritettavaSuhteikko(Relaatio relaatio) {
        super(relaatio);
        varit = new Color[relaatio.JOUKONKOKO];
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
    
    /**
     * Asettaa annetulle pisteelle annetun värin.
     * @param piste väritettävä piste
     * @param vari väri jolla väritetään
     */

    public void varita(int piste, Color vari) {
        varit[piste - INDEKSIKORJAUS] = vari;
    }
    
    /**
     * Minkä värinen piste on.
     * Mikäli väriä ei ole asetettu, palauttaa null.
     * @param piste kysytty piste
     * @return pisteen väri
     */

    public Color getVari(int piste) {
        return varit[piste - INDEKSIKORJAUS];
    }

    
}
