
package relaatiot;

import tietorakenteetLuvuille.IntSailio;
import tietorakenteetLuvuille.VenyvaTaulukkoVain1Esiintyma;

/**
 * Kuvaa suhteikon pisteiden välistä relaatiota.
 * Apurakenteena venyvä int-taulukko.
 * @author silja
 */
public class RelaatioVenyvallaTaulukolla extends Relaatio {

    private final int INDEKSIKORJAUS = 1;
    private VenyvaTaulukkoVain1Esiintyma[] yhteydet;

    /**
     * Relaatio annetun kokoisessa joukossa.
     * @param joukonKoko lähtö- ja samalla maalijoukon alkioiden lukumäärä
     */

    public RelaatioVenyvallaTaulukolla(int joukonKoko) {
        super(joukonKoko);
        yhteydet = new VenyvaTaulukkoVain1Esiintyma[this.JOUKONKOKO];
    }

    /**
     * Lisää yhteyden annettujen pisteiden välille.
     * Aikavaativuus O(yhteyksienLkm),
     * tilavaativuus O(1)
     * @param alkupiste piste josta yhteys alkaa
     * @param loppupiste piste johon yhteys on
     */
    
    public void lisaaYhteys(int alkupiste, int loppupiste) {
        VenyvaTaulukkoVain1Esiintyma vanhatSeuraajat
                = (VenyvaTaulukkoVain1Esiintyma) getSeuraajat(alkupiste);

        if (vanhatSeuraajat == null) {
            vanhatSeuraajat = new VenyvaTaulukkoVain1Esiintyma();
            setSeuraajat(alkupiste, vanhatSeuraajat);
        }

        vanhatSeuraajat.lisaa(loppupiste); //Aika O(n) taulukon koon suhteen
    }

    /**
     * Valmistaa relaation tulevaa käyttöä varten
     * järjestämällä seuraajataulukot.
     * Tämän jälkeen binäärihakua hyödyntävä etsi-metodi toimii niissä oikein.
     * Huomaa, että onYhteys-metodi toimii vain relaatiossa, jonka rakenteet on
     * järjestetty.
     * Aikavaativuus O(pisteidenLkm * yhteyksienLkm),
     * tilavaativuus O(1)
     */
    
    public void jarjestaRakenteet() {
        VenyvaTaulukkoVain1Esiintyma seuraajat;
        for (int i=0; i<JOUKONKOKO; i++) { //O(pisteidenLkm)
            seuraajat = this.yhteydet[i];
            if (seuraajat != null) 
                seuraajat.jarjesta(); //järjestäminen O(n log n) yhteyksien suhteen
        }
    }

    /**
     * Pisteen seuraajat int-säiliössä.
     * Aikavaativuus O(1),
     * Tilavaativuus O(1)
     * @param piste piste jonka seuraajat halutaan
     * @return seuraajat jossakin int-säiliössä
     */

    public IntSailio getSeuraajat(int piste) {
        return yhteydet[piste - INDEKSIKORJAUS];
    }

    /**
     * Onko alkupisteestä loppupisteeseen yhteys.
     * Huomaa, että jarjestaRakenteet-metodia on kutsuttava ennen tämän metodin käyttöä.
     * Aikavaativuus: O(log yhteyksienLkm),
     * Tilavaativuus: O(1)
     * @param alkupiste piste josta yhteys on
     * @param loppupiste piste johon yhteys on
     * @return oliko pisteiden välillä tähän suuntaan yhteys
     */

    public boolean onYhteys(int alkupiste, int loppupiste) {
        IntSailio seuraajat = getSeuraajat(alkupiste);  // O(1)
        if (seuraajat == null)                          // O(1)
            return false;
        return seuraajat.etsi(loppupiste);   // O(log n) missä n seuraajien lkm
    }                                        //      -> O(log yhteyksienLkm)


    /*
     * PRIVAATTIMETODIT ALLA ------------------------
     */

    /**
     * Privaatti. Asettaa seuraajajoukon.
     * Aikavaativuus O(1), tilavaativuus O(1)
     * @param alkupiste piste jonka seuraajat
     * @param seuraajat seuraajat int-säiliössä
     */

    private void setSeuraajat(int alkupiste, VenyvaTaulukkoVain1Esiintyma seuraajat) {
        yhteydet[alkupiste - INDEKSIKORJAUS] = seuraajat;
    }

}
