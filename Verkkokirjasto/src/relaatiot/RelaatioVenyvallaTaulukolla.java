
package relaatiot;

import tietorakenteet.IntSailio;
import tietorakenteet.VenyvaTaulukko;

/**
 * Kuvaa suhteikon pisteiden välistä relaatiota.
 * Apurakenteena venyvä int-taulukko.
 * @author silja
 */
public class RelaatioVenyvallaTaulukolla extends Relaatio {

    private final int INDEKSIKORJAUS = 1;
    private VenyvaTaulukko[] yhteydet;

    /**
     * Relaatio annetun kokoisessa joukossa.
     * @param joukonKoko lähtö- ja samalla maalijoukon alkioiden lukumäärä
     */

    public RelaatioVenyvallaTaulukolla(int joukonKoko) {
        super(joukonKoko);
        yhteydet = new VenyvaTaulukko[this.JOUKONKOKO];
    }

    /**
     * Lisää yhteyden annettujen pisteiden välille.
     * @param alkupiste piste josta yhteys alkaa
     * @param loppupiste piste johon yhteys on
     */
    
    public void lisaaYhteys(int alkupiste, int loppupiste) {
        VenyvaTaulukko seuraajat
                = (VenyvaTaulukko) getSeuraajat(alkupiste);

        if (seuraajat == null) {
            seuraajat = new VenyvaTaulukko();
            setSeuraajat(alkupiste, seuraajat);
        }

        seuraajat.lisaa(loppupiste);
    }

    /**
     * Valmistaa relaation tulevaa käyttöä varten
     * järjestämällä seuraajataulukot.
     * Tämän jälkeen binäärihakua hyödyntävä etsi-metodi toimii niissä oikein.
     * Huomaa, että onYhteys-metodi toimii vain valmiissa relaatiossa.
     */
    
    public void jarjestaRakenteet() {
        VenyvaTaulukko seuraajat;
        for (int i=0; i<JOUKONKOKO; i++) {
            seuraajat = this.yhteydet[i];
            if (seuraajat != null) seuraajat.jarjesta();
        }
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
     * Huomaa, että jarjestaRakenteet-metodia on kutsuttava ennen tämän metodin käyttöä.
     * @param alkupiste piste josta yhteys on
     * @param loppupiste piste johon yhteys on
     * @return oliko pisteiden välillä tähän suuntaan yhteys
     */

    public boolean onYhteys(int alkupiste, int loppupiste) {
        IntSailio seuraajat = getSeuraajat(alkupiste);
        if (seuraajat == null) return false;
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

    private void setSeuraajat(int alkupiste, VenyvaTaulukko seuraajat) {
        yhteydet[alkupiste - INDEKSIKORJAUS] = seuraajat;
    }


}
