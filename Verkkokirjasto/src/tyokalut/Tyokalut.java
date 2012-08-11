
package tyokalut;

/**
 * Yleishyödyllisiä työkaluja kaikkeen.
 * @author silja
 */
public class Tyokalut {

    /**
     * Kahden kokonaisluvun keskiarvo katkaistuna kokonaislukuna
     * @param a ensimmäinen luku
     * @param b toinen luku
     * @return keskiarvo joka on katkaistu kokonaisluvuksi pyöristämättä
     */
    public static int keskiarvo(int a, int b) {
        double vastaus = 0.5 * (a+b);
        return (int) vastaus;
    }
    
    /**
     * Kahden kokonaisluvun minimi.
     * @param a ensimmäinen luku
     * @param b toinen luku
     * @return luvuista pienempi
     */

    public static int minimi(int a, int b) {
        if (a<b) return a;
        return b;
    }

    /**
     * Ovatko kaksi totuusmuuttujaa ekvivalentit:
     * joko molemmat true tai molemmat false.
     * @param a ensimmäinen totuusarvo
     * @param b toinen totuusarvo
     * @return olivatko ekvivalentit
     */

    public static boolean ekvivalentit(boolean a, boolean b) {
        return (a&&b) || ((!a)&&(!b));
    }


}
