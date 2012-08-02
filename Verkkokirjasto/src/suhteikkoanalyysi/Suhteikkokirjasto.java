/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package suhteikkoanalyysi;

import suhteikot.Suhteikko;

/**
 * Kirjasto, joka tarjoaa suhteikkojen rakenteen tutkimiseen sopivia algoritmeja
 * @author silja
 */
public class Suhteikkokirjasto {

    /**
     * Täyttääkö suhteikko verkkoehdon: onko se silmukaton ja symmetrinen
     * @param suhteikko analysoitava suhteikko
     * @return täyttääkö verkkoehdon
     */

    public static boolean tayttaaVerkkoehdon(Suhteikko suhteikko) {
        return silmukaton(suhteikko) && symmetrinen(suhteikko);
    }
    /**
     * Onko suhteikko silmukaton:
     * mistään pisteestä ei ole itseensä yhteyttä.
     *
     * @param suhteikko analysoitava suhteikko
     * @return oliko silmukaton
     */

    public static boolean silmukaton(Suhteikko suhteikko) {
        for (int i=1; i<=suhteikko.pisteidenLkm(); i++) {
            if (suhteikko.onYhteys(i, i))
                return false;
        }
        return true;
    }

    /**
     * Onko suhteikko symmetrinen:
     * suhteikon kahden pisteen välillä on joko yhteys molempiin suuntiin
     * tai sitten ei lainkaan yhteyttä.
     * @param suhteikko analysoitava suhteikko
     * @return oliko symmetrinen
     */
    public static boolean symmetrinen(Suhteikko suhteikko) {
        for (int i=1; i<=suhteikko.pisteidenLkm(); i++) {
            for (int j=1; j<=suhteikko.pisteidenLkm(); j++) {
                if (!ekvivalentit(suhteikko.onYhteys(i, j), suhteikko.onYhteys(j, i)))
                    return false;
            }
        }
        return true;
    }

    /**
     * Ovatko kaksi totuusmuuttujaa ekvivalentit:
     * joko molemmat true tai molemmat false.
     * --Tämä siirrettäneen yleisempään metodikirjastoon.--
     *
     * @param a ensimmäinen totuusarvo
     * @param b toinen totuusarvo
     * @return olivatko ekvivalentit
     */

    private static boolean ekvivalentit(boolean a, boolean b) {
        return (a&&b) || ((!a)&&(!b));
    }
    
}