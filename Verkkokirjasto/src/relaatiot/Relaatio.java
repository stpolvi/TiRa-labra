
package relaatiot;

import tietorakenteet.IntSailio;

/**
 * Abstrakti relaatio.
 * @author silja
 */
public abstract class Relaatio {

    /**
     * Lisää yhteyden pisteiden välille.
     * @param alkupiste piste josta lisättävä yhteys
     * @param loppupiste piste johon lisättävä yhteys
     */

    public abstract void lisaaYhteys(int alkupiste, int loppupiste);

    /**
     * Pisteen seuraajat int-säiliössä.
     * @param piste piste jonka seuraajat
     * @return seuraajat jossakin int-säiliössä
     */

    public abstract IntSailio getSeuraajat(int piste);

    /**
     * Onko pisteiden välillä yhteys.
     * @param alkupiste piste josta yhteys olisi
     * @param loppupiste piste johon yhteys olisi
     * @return oliko yhteyttä alkupisteestä loppupisteeseen
     */

    public abstract boolean onYhteys(int alkupiste, int loppupiste);

    /**
     * Järjestää relaatiossa olevat seuraajasäiliöt.
     * Tämä operaatio on aina tehtävä ennen kuin relaation avulla luodaan suhteikko.
     * Mahdollistaa suhteikon tutkimisen nopeasti,
     * esimerkiksi binäärihakua käyttävät seuraajasäiliöiden etsimismetodit.
     */

    public abstract void sort();

}
