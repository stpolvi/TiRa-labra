
package suhteikot;

import java.awt.Color;
import relaatiot.Relaatio;

/**
 * TavallinenSuhteikko-olio kuvaa suhteikkoa (eli ns. suunnattua verkkoa),
 * joka pisteet ovat kokonaislukuja. Jos suhteikossa on n pistettä,
 * sen pisteet ovat 1,2,3,...,n. Suhteikko koostuu pisteistä ja niiden välisestä
 * Relaatiosta. Tämän suhteikon pisteille voi määrittää värin, jota voi muuttaa.
 * @author silja
 */

public class VaritettavaSuhteikko extends TavallinenSuhteikko {

    private Color[] varit;

    private final int INDEKSIKORJAUS = 1;
    
    /**
     * Parametriton konstruktori luo tyhjän suhteikon:
     * pisteitä ei ole, ja relaatio on null.
     */

    public VaritettavaSuhteikko() {
        super();
        varit = null;
    }

    /**
     * Luo suhteikon annetun relaation perusteella.
     * Pistemäärä päätellään relaatiosta.
     * Aluksi kaikkien pisteiden väri on null.
     * @param relaatio tulevan suhteikon pisteiden välinen relaatio
     */

    public VaritettavaSuhteikko(Relaatio relaatio) {
        super(relaatio);
        varit = new Color[relaatio.JOUKONKOKO];
    }

    /**
     * Luo suhteikon annetun relaation perusteella.
     * Pistemäärä päätellään relaatiosta.
     * Alustaa kaikkien pisteiden väriksi annetun värin.
     * Vaativuus ainakin O(n) ???
     * @param relaatio tulevan suhteikon pisteiden välinen relaatio
     */

    public VaritettavaSuhteikko(Relaatio relaatio, Color vari) {
        super(relaatio);
        varit = new Color[relaatio.JOUKONKOKO];
        alustaKaikkiPisteetVarilla(vari);
    }

    private void alustaKaikkiPisteetVarilla(Color vari) {
        varitaKaikki(vari);
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
     * Asettaa kaikille pisteille annetun värin.
     * Vaativuus O(n)
     * @param vari väri jolla pisteet väritetään
     */

    public void varitaKaikki(Color vari) {
        for (int i=1; i<=PISTEITA; i++) {
            varita(i, vari);
        }
    }

    /**
     * Asettaa kaikille pisteille väriksi null.
     * Vaativuus O(n)
     */

    public void alustaVarit() {
        for (int i=0; i<varit.length; i++) {
            varit[i] = null;
        }
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
