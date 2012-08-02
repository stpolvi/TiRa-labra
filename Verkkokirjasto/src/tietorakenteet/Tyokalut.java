/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tietorakenteet;

/**
 * Yleishyödyllisiä työkaluja kaikkeen.
 * @author silja
 */
public class Tyokalut {

    /**
     * Kopioi ensimmäisen (lyhyemmän) taulukon sisällön toiseen (pitempään)
     * @param vanha kopioitava lyhyempi
     * @param uusi tavallisesti tyhjä pitempi taulukko
     */
    public static void kopioiOliotaulukkoToiseen(Object[] vanha, Object[] uusi) {
        int lyhyemmanPituus = Math.min(vanha.length, uusi.length);

        for (int i = 0; i < lyhyemmanPituus; i++) {
            uusi[i] = vanha[i];
        }
    }

    /**
     * Kahden kokonaisluvun keskiarvo katkaistuna kokonaislukuna
     * @param a ensimmäinen luku
     * @param b toinen luku
     * @return keskiarvo joka on katkaistu kokonaisluvuksi pyöristämättä
     */
    public static int keskiarvo(int a, int b) {
        return (int) 0.5 * (a+b);
    }

}
