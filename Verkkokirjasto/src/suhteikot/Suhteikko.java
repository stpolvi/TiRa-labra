
package suhteikot;

//import lataaja.Lataajakone;
import relaatiot.Relaatio;
import tietorakenteetLuvuille.IntSailio;

/**
 * Abstrakti suhteikko, jota voidaan tutkia suhteikkokirjaston algoritmeilla.
 * @author silja
 */

public abstract class Suhteikko {
    
    /**
     * Suhteikon pisteiden lukumäärä
     */

    public final int PISTEITA;

    /**
     * Pakkausnäkyvä relaatio, joka esittää suhteikon pisteiden välisiä suhteita.
     */

    final Relaatio relaatio;
    
//    /**
//     * Pakkausnäkyvä Lataajakone, jonka avulla suhteikon tietoja voi jäsennellä
//     * valmiiksi. Lataajaa ei ole pakko käyttää mutta se nopeuttaa
//     * suhteikon tutkiskelua.
//     */
//
//    Lataajakone lataaja;

    /**
     * Parametriton konstruktori luo tyhjän suhteikon.
     */

    public Suhteikko() {
        this.PISTEITA = 0;
        this.relaatio = null;
    }

    /**
     * Konstruktori, jolle annetaan relaatio.
     * @param relaatio luotavan suhteikon pisteiden välinen relaatio
     */

    public Suhteikko(Relaatio relaatio) {
        this.PISTEITA = relaatio.JOUKONKOKO;
        relaatio.jarjestaRakenteet();
        this.relaatio = relaatio;
    }

    /**
     * Onko alkupisteestä loppupisteeseen yhteys.
     * @param alkupiste piste josta yhteys
     * @param loppupiste piste johon yhteys
     * @return oliko yhteyttä
     */

    public abstract boolean onYhteys(int alkupiste, int loppupiste);
    
    /**
     * Annetun pisteen seuraajat int-säiliössä.
     * @param piste piste jonka seuraajat halutaan
     * @return seuraajat jossakin int-säiliössä
     */

    public abstract IntSailio getSeuraajat(int piste);

    /**
     * Kuinka monta seuraajaa pisteellä on.
     * @param piste piste jonka seuraajien määrä halutaan
     * @return seuraajien lukumäärä
     */

    public abstract int seuraajienLkm(int piste);

}
