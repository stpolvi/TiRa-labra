/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tietorakenteet;

/**
 *
 * @author silja
 */
public class Tyokalut {

    public static void kopioiOliotaulukkoToiseen(Object[] vanha, Object[] uusi) {
        int lyhyemmanPituus = Math.min(vanha.length, uusi.length);

        for (int i = 0; i < lyhyemmanPituus; i++) {
            uusi[i] = vanha[i];
        }
    }


    public static int keskiarvo(int a, int b) {
        return (int) 0.5 * (a+b);
    }

}
