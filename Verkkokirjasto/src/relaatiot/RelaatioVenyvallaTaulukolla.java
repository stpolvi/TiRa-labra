
package relaatiot;

import tietorakenteet.IntSailio;
import tietorakenteet.VenyvaTaulukkoTuplaantuva;

/**
 * Kuvaa suhteikon pisteiden välistä relaatiota.
 * Apurakenteena venyvä int-taulukko.
 * @author silja
 */
public class RelaatioVenyvallaTaulukolla extends Relaatio {

    public final int JOUKONKOKO;
    private final int INDEKSIKORJAUS = 1;

    private IntSailio[] yhteydet;

    /**
     * Relaatio annetun kokoisessa joukossa.
     * @param joukonKoko lähtö- ja maalijoukon alkioiden lukumäärä
     */

    public RelaatioVenyvallaTaulukolla(int joukonKoko) {
        this.JOUKONKOKO = joukonKoko;
        yhteydet = new VenyvaTaulukkoTuplaantuva[joukonKoko];
    }

    /**
     * Lisää yhteyden annettujen pisteiden välille.
     * @param alkupiste piste josta yhteys alkaa
     * @param loppupiste piste johon yhteys on
     */
    
    public void lisaaYhteys(int alkupiste, int loppupiste) {
        IntSailio seuraajat = getSeuraajat(alkupiste);

        if (seuraajat == null) {
            seuraajat = new VenyvaTaulukkoTuplaantuva();
            setSeuraajat(alkupiste, seuraajat);
        }

        seuraajat.lisaa(loppupiste);
    }

    /**
     * Pisteen seuraajat taulukossa.
     * @param piste piste jonka seuraajat halutaan
     * @return seuraajat taulukossa
     */

    public IntSailio getSeuraajat(int piste) {
        return yhteydet[piste - INDEKSIKORJAUS];
    }

    /**
     * Onko alkupisteestä loppupisteeseen yhteys.
     * @param alkupiste piste josta yhteys on
     * @param loppupiste piste johon yhteys on
     * @return oliko pisteiden välillä tähän suuntaan yhteys
     */

    public boolean onYhteys(int alkupiste, int loppupiste) {
        IntSailio seuraajat = getSeuraajat(alkupiste);

        if (seuraajat == null) {
            return false;
        }

        return seuraajat.etsi(loppupiste);
    }



    /*
     * PRIVAATTIMETODIT ALLA ------------------------
     */

    /**
     * Privaatti. Asettaa seuraajajoukon.
     * @param alkupiste piste jonka seuraajat
     * @param seuraajat seuraajat int-säiliössä
     */

    private void setSeuraajat(int alkupiste, IntSailio seuraajat) {
        yhteydet[alkupiste - INDEKSIKORJAUS] = seuraajat;
    }


}
