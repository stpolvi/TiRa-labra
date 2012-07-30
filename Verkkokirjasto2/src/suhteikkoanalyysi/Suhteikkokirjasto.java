/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package suhteikkoanalyysi;

import suhteikot.Suhteikko;

/**
 *
 * @author silja
 */
public class Suhteikkokirjasto {

    public static boolean tayttaaVerkkoehdon(Suhteikko suhteikko) {
        return silmukaton(suhteikko) && symmetrinen(suhteikko);
    }

    public static boolean silmukaton(Suhteikko suhteikko) {
        for (int i=1; i<=suhteikko.pisteidenLkm(); i++) {
            if (suhteikko.onYhteys(i, i))
                return false;
        }
        return true;
    }

    public static boolean symmetrinen(Suhteikko suhteikko) {
        for (int i=1; i<=suhteikko.pisteidenLkm(); i++) {
            for (int j=1; j<=suhteikko.pisteidenLkm(); j++) {
                if (!ekvivalentit(suhteikko.onYhteys(i, j), suhteikko.onYhteys(j, i)))
                    return false;
            }
        }
        return true;
    }

    private static boolean ekvivalentit(boolean a, boolean b) {
        return (a&&b) || ((!a)&&(!b));
    }
    
}
