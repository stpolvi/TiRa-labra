
package relaatiot;

import tietorakenteetLuvuille.IntSailio;

/**
 * Abstrakti relaatio.
 * @author silja
 */

public abstract class Relaatio {

    /**
     * Sen joukon koko, jonka relaatio tämä on.
     */

    public final int JOUKONKOKO;

    /**
     * Konstruktorissa on annettava lähtö- eli samalla maalijoukon koko.
     * @param joukonKoko sen joukon alkioiden lukumäärä,
     * jonka relaatio luodaan
     */

    public Relaatio(int joukonKoko) {
        this.JOUKONKOKO = joukonKoko;
    }

    /**
     * Lisää yhteyden pisteiden välille.
     * @param alkupiste piste josta lisättävä yhteys
     * @param loppupiste piste johon lisättävä yhteys
     */

    public abstract void lisaaYhteys(int alkupiste, int loppupiste);

    /**
     * Pisteen seuraajat {@link IntSailio}ssa.
     * @param piste piste jonka seuraajat
     * @return seuraajat jossakin int-säiliössä
     */

    public abstract IntSailio getSeuraajat(int piste);

    /**
     * Onko pisteiden välillä yhteys.
     * Huomaa, että tämä metodi ei toimi, mikäli relaation
     * rakenteita ei ole järjestetty ({@link jarjestaRakenteet}).
     * @param alkupiste piste josta yhteys olisi
     * @param loppupiste piste johon yhteys olisi
     * @return oliko yhteyttä alkupisteestä loppupisteeseen
     */

    public abstract boolean onYhteys(int alkupiste, int loppupiste);

    /**
     * Metodin kutsun jälkeen seuraajasäiliöt ovat järjestyksessä,
     * jotta esimerkiksi binäärihaku niissä mahdollistuu.
     * Metodin suorittaminen voi viedä aikaa,
     * mutta säästää aikaa myöhemmin relaatiota tutkittaessa.
     */

    public abstract void jarjestaRakenteet();

}
