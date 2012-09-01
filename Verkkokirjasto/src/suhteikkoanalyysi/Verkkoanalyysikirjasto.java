
package suhteikkoanalyysi;

import suhteikot.Suhteikko;
import tietorakenteetLuvuille.IntSailio;
import tyokalut.Tyokalut;

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
     * Jos kaikkien n-pisteisen verkon pisteiden aste on k, niin 
     * verkon kaarien lukumäärä on (1/2)nk.
     *
     * Aikavaativuus O(pisteidenLkm)
     * @param v analysoitava verkko
     */

    public static boolean onSaannollinen(Suhteikko v) {
        if (v.PISTEITA == 0) return true;

        if (!kaikkiAsteetSamatEpatyhjassa(v)) return false; // O(pisteidenLkm)
        return true;
    }

    /**
     * Onko annettu verkko täydellinen:
     * onko jokaisesta pisteestä yhteys kaikkiin muihin pisteisiin.
     * Huomaa että täydellisen n-pisteisen verkon kaarien lukumäärä on
     * (1/2)n(n-1).
     *
     * Aikavaativuus O(pisteidenLkm^2 log pisteidenLkm)
     * Tilavaativuus O(1)
     * @param v analysoitava verkko
     * @return oliko täydellinen
     */

    public static boolean onTaydellinen(Suhteikko v) {
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

    public static boolean onTaydellinenSeuraajienAvulla(Suhteikko v) {
        for (int i=1; i<=v.PISTEITA; i++) {
            if (v.seuraajienLkm(i) != v.PISTEITA -1) return false;
        }

        return true;
    }

    /**
     * Sama kuin Taydellinen mutta toteutus eri.
     * Aikavaativuus O(pisteidenLkm)
     * @param v analysoitava verkko
     * @return oliko täydellinen verkko
     */

    public static boolean onTaydellinenSaannollisyydenAvulla(Suhteikko v) {
        if (v.PISTEITA == 0 || v.PISTEITA == 1) return true;
        return pisteenAste(v, 1) == v.PISTEITA-1 && onSaannollinen(v);
    }

    /**
     * TODO: renkaat
     * Rengas verkossa on vähintään kahden muun pisteen kautta kulkeva
     * kulku jostakin pisteestä itseensä.
     * Huom. kulun täytyy kulkea vähintään kolmen pisteen kautta.
     * @param s analysoitava suhteikko
     * @return onko suhteikossa rengas
     */

    public static boolean sisaltaaRenkaan(Suhteikko s) {


        throw new Error("kesken");
    }

    /**
     * Täyttääkö verkko puuehdon:
     * TODO määritelmä
     * @param v analysoitava verkko
     * @return oliko puu
     */

    public static boolean tayttaaPuuehdon(Suhteikko v) {
        if (sisaltaaRenkaan(v)) return false;
        if (!onYhtenainenKulkujenAvulla(v)) return false;
        return true;
    }

    public static boolean onYhtenainenJuurienAvulla(Suhteikko v) {
        if (v.PISTEITA == 0) return true;
        return Suhteikkoanalyysikirjasto.onJuuri(v, 1);
    }

    /**
     * Onko verkko yhtenäinen:
     * jokaisesta pisteestä löytyy reitti kaikkiin muihin pisteisiin,
     * mahdollisesti muiden pisteiden kautta.
     * Toisin sanoen, ovatko kaikki pisteet yhteydessä toisiinsa niin ettei
     * pisteitä tai pistejoukkoja ole eristyksissä.
     * Tämä metodi selvittää yhtenäisyyden tutkimalla,
     * onko kaikista pisteistä kulku pisteeseen 1.
     *
     * Huom verkoilla yhtenäisyys ja vahva yhtenäisyys ovat yhtäpitäviä.
     *
     * Aikavaativuus todella surkea
     * @param v analysoitava verkko
     * @return oliko yhtenäinen
     */

    public static boolean onYhtenainenKulkujenAvulla(Suhteikko v) {
        if (v.PISTEITA == 0 || v.PISTEITA == 1)
            return true;

        return kaikistaPisteistaOnKulkuPisteeseen(v, 1);
    }

    /**
     * TODO hallitseva
     * Onko annettu pistejoukko hallitseva annetussa verkossa:
     * onko jokainen verkon piste joko joukossa tai sitten joukossa
     * olevan naapuri. Joukon on oltava valmiiksi järjestetty.
     */

    public static boolean hallitseva(Suhteikko v, int[] pistejoukko) {
        for (int i=1; i<=v.PISTEITA; i++) {
            if (Tyokalut.etsiBinaarihaulla(i, 0, pistejoukko.length-1, pistejoukko)) continue;
            if (!onYhteysJoukkoon(v, i, pistejoukko)) return false;
        }
        return true;
    }
    
    /**
     * Onko annetusta pisteestä yhteys annettuun pistejoukkoon, eli johonkin
     * joukon pisteeseen.
     * Huomaa että metodi toimii ainoastaan järjestetylle taulukolle.
     * Aikavaativuus ??
     * @param v analysoitava suhteikko
     * @param piste yhteyden lähtöpiste
     * @param pistejoukko joukko, jonka pisteeseen yhteys olisi
     * @return oliko yhteyttä
     */

    public static boolean onYhteysJoukkoon
            (Suhteikko v, int piste, int[] jarjestettyPistejoukko) {
        int joukonKoko = jarjestettyPistejoukko.length;
        if (joukonKoko == 0) return false;

        if (v.seuraajienLkm(piste) == 0)
            return Tyokalut.etsiBinaarihaulla
                    (piste, 0, joukonKoko-1, jarjestettyPistejoukko);

        int[] seuraajat = v.getSeuraajat(piste).toIntArray();
        int s = 0, p = 0;
        while (true) {
            if (seuraajat[s] < jarjestettyPistejoukko[p]) {
                if (s == seuraajat.length - 1) return false;
                s++;
            } else if (seuraajat[s] > jarjestettyPistejoukko[p]) {
                if (p == jarjestettyPistejoukko.length - 1) return false;
                p++;
            } else
                return true;
        }
    }

/*
 * Privaattimetodit -----------------------------
 */

    private static boolean kaikistaPisteistaOnKulkuPisteeseen(Suhteikko v, int piste) {
        for (int i=1; i<=v.PISTEITA; i++) {
            if (!Suhteikkoanalyysikirjasto.onKulkuLeveyshaulla(v, piste, i)) {
                return false;
            }
        }
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

}
