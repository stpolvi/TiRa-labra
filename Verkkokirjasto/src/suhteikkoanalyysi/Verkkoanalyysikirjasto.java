/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package suhteikkoanalyysi;

import suhteikot.Suhteikko;
import tietorakenteet.IntSailio;

/**
 * Verkkoanalyysikirjastossa on algoritmeja, joilla voidaan tutkia verkkoja
 * eli verkkoehdon täyttäviä suhteikkoja. Useimmat algoritmit eivät toimi oikein,
 * jos syötteeksi annetaan sellaisia suhteikkoja, jotka eivät ole verkkoja.
 * Verkkoehdon tutkiminen jätetään käyttäjän vastuulle.
 * @author silja
 */
public class Verkkoanalyysikirjasto {

    /**
     * Pisteen aste on pisteestä lähtevien yhteyksien lukumäärä.
     * Aikavaativuus O(1)
     * @param v analysoitava verkko
     * @param piste verkon piste
     * @return pisteen aste verkossa
     */

    public static int pisteenAste(Suhteikko v, int piste) {
        IntSailio seuraajat = v.getSeuraajat(piste);    // O(1)
        if (seuraajat == null) return 0;
        return seuraajat.alkioita();                    // O(1)
    }

    /**
     * Onko verkko säännöllinen:
     * onko kaikkien pisteiden aste sama.
     * Aikavaativuus O(pisteidenLkm)
     * @param v analysoitava verkko
     */

    public static boolean saannollinen(Suhteikko v) {
        if (v.PISTEITA == 0) return true;

        if (!kaikkiAsteetSamatEpatyhjassa(v)) return false; // O(pisteidenLkm)
        return true;
    }

    /**
     * Privaatti. Aikavaativuus O(pisteidenLkm)
     */

    private static boolean kaikkiAsteetSamatEpatyhjassa(Suhteikko v) {
        int ekanAste = pisteenAste(v, 1); //O(1)
        for (int i=2; i<=v.PISTEITA; i++) { //O(pisteidenLkm)
            if (pisteenAste(v, i) != ekanAste) return false;
        }
        return true;
    }

    /**
     * Onko annettu verkko täydellinen:
     * onko jokaisesta pisteestä yhteys kaikkiin muihin pisteisiin.
     * Aikavaativuus O(pisteidenLkm^2 log pisteidenLkm)
     * Tilavaativuus O(1)
     * @param v analysoitava verkko
     * @return oliko täydellinen
     */

    public static boolean taydellinen(Suhteikko v) {
        for (int i=1; i<=v.PISTEITA; i++) {
            for (int j=i+1; j<=v.PISTEITA; j++) {
                if (!v.onYhteys(i, j)) return false; //O(log pisteidenLkm)
            }
        }

        return true;
    }

    /**
     * Sama kuin Taydellinen mutta toteutus eri.
     * Aikavaativuus O(pisteidenLkm^2 log pisteidenLkm)
     * @param v analysoitava verkko
     * @return oliko täydellinen verkko
     */

    public static boolean taydellinenVerkkoSeuraajienAvulla(Suhteikko v) {
        for (int i=1; i<=v.PISTEITA; i++) {
            if (v.seuraajienLkm(i) != v.PISTEITA -1) return false;
        }

        return true;
    }





}
