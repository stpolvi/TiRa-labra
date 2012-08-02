/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lukusuhteikot;

import tietorakenteet.LukujoukkoTehoton;

/**
 * Kuvaa sellaisen suhteikon pisteiden välistä relaatiota,
 * jossa on pisteinä kokonaislukuja ykkösestä alkaen.
 * @author silja
 */
public class Lukusuhteikkorelaatio {

    public final int JOUKONKOKO;
    private final int INDEKSIKORJAUS = 1;

    private LukujoukkoTehoton[] yhteydet;

    /**
     * Relaatio annetun kokoisessa joukossa.
     * @param joukonKoko lähtö- ja maalijoukon alkioiden lukumäärä
     */

    public Lukusuhteikkorelaatio(int joukonKoko) {
        this.JOUKONKOKO = joukonKoko;
        yhteydet = new LukujoukkoTehoton[joukonKoko];
    }

    /**
     * Lisää yhteyden annettujen pisteiden välille.
     * @param alkupiste piste josta yhteys alkaa
     * @param loppupiste piste johon yhteys on
     * @return true jos yhteys lisättiin, false jos yhteys jo oli olemassa
     * tai ei muuten voitu lisätä
     */
    
    public boolean lisaaYhteys(int alkupiste, int loppupiste) {
        LukujoukkoTehoton seuraajat = getSeuraajat(alkupiste);

        if (seuraajat == null) {
            seuraajat = new LukujoukkoTehoton();
            setSeuraajat(alkupiste, seuraajat);
        }

        return seuraajat.lisaa(loppupiste);
    }

    /**
     * Pisteen seuraajat taulukossa.
     * @param piste piste jonka seuraajat halutaan
     * @return seuraajat taulukossa
     */

    public LukujoukkoTehoton getSeuraajat(int piste) {
        return yhteydet[piste - INDEKSIKORJAUS];
    }

    /**
     * Onko alkupisteestä loppupisteeseen yhteys.
     * @param alkupiste piste josta yhteys on
     * @param loppupiste piste johon yhteys on
     * @return oliko pisteiden välillä tähän suuntaan yhteys
     */

    public boolean onYhteys(int alkupiste, int loppupiste) {
        LukujoukkoTehoton seuraajat = getSeuraajat(alkupiste);

        if (seuraajat == null) {
            return false;
        }

        return seuraajat.onAlkio(loppupiste);
    }


    /**
     * Asettaa seuraajajoukon
     * @param alkupiste piste jonka seuraajat
     * @param seuraajat seuraajat joukossa
     */


    private void setSeuraajat(int alkupiste, LukujoukkoTehoton seuraajat) {
        yhteydet[alkupiste - INDEKSIKORJAUS] = seuraajat;
    }

}
