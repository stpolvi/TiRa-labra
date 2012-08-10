
package suhteikkoanalyysi;

import suhteikot.Suhteikko;
import tietorakenteet.IntSailio;

/**
 * Kirjasto, joka tarjoaa suhteikkojen rakenteen tutkimiseen sopivia algoritmeja.
 * TODO: kaikkien metodien javadocciin O-vaativuusanalyysi
 * TODO: code coverage -plugari
 * @author silja
 */
public class Suhteikkoanalyysikirjasto {

    private Suhteikkoanalyysikirjasto() {}
    
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
        for (int i=1; i<=suhteikko.PISTEITA; i++) {
            if (suhteikko.onYhteys(i, i))
                return false;
        }
        return true;
    }

    /**
     * MUUTETAAN TEHOKKAAMMAKSI
     * Onko suhteikko symmetrinen:
     * suhteikon kahden pisteen välillä on joko yhteys molempiin suuntiin
     * tai sitten ei lainkaan yhteyttä.
     * @param suhteikko analysoitava suhteikko
     * @return oliko symmetrinen
     */
    
    public static boolean symmetrinen(Suhteikko suhteikko) {
        for (int i=1; i<=suhteikko.PISTEITA; i++) {
            for (int j=1; j<=suhteikko.PISTEITA; j++) {
                if (!ekvivalentit(suhteikko.onYhteys(i, j), suhteikko.onYhteys(j, i)))
                    return false;
            }
        }
        return true;
    }

    /**
     * Lähtöaste on pisteestä lähtevien yhteyksien lukumäärä.
     * @param s suhteikko
     * @param piste suhteikon piste
     * @return pisteen lähtöaste suhteikossa
     */

    public static int lahtoaste(Suhteikko s, int piste) {
        IntSailio seuraajat = s.getSeuraajat(piste);
        if (seuraajat == null) return 0;
        return seuraajat.alkioita();
    }

    /**
     * Tuloaste on pisteeseen saapuvien yhteyksien lukumäärä.
     * @param s suhteikko
     * @param piste suhteikon piste
     * @return pisteen tuloaste suhteikossa
     */

    public static int tuloaste(Suhteikko s, int piste) {
        int vastaus = 0;
        for (int i=1; i<=s.PISTEITA; i++) {
            if (s.onYhteys(i, piste)) vastaus++;
        }
        return vastaus;
    }

    /**
     * Onko suhteikko säännöllinen verkko:
     * täyttääkö se verkkoehdon ja onko lisäksi kaikkien pisteiden aste
     * (eli lähtöaste) sama.
     * @param s analysoitava suhteikko
     */

    public static boolean saannollinenVerkko(Suhteikko s) {
        if (!tayttaaVerkkoehdon(s)) return false;
        if (s.PISTEITA == 0) return true;

        if (!kaikkiAsteetSamatEpatyhjassa(s)) return false;
        return true;
    }

    /**
     * Onko suhteikko täydellinen:
     * onko jokaisen kahden eri pisteen välillä yhteys ainakin toiseen suuntaan.
     * Pisteestä ei tarvitse olla itseensä yhteyttä.
     * @param s analysoitava suhteikko
     * @return oliko täydellinen
     */

    public static boolean taydellinen(Suhteikko s) {
        for (int i=1; i<=s.PISTEITA; i++) {
            for (int j=i+1; j<=s.PISTEITA; j++) {
                if (!s.onYhteys(i, j) && !s.onYhteys(j, i)) return false;
            }
        }
        return true;
    }
    
    /**
     * Onko annettu suhteikko täydellinen verkko:
     * onko jokaisesta pisteestä yhteys kaikkiin muihin pisteisiin.
     * @param s analysoitava suhteikko
     * @return oliko täydellinen verkko
     */

    public static boolean taydellinenVerkko(Suhteikko s) {
        if (!tayttaaVerkkoehdon(s)) return false;

        for (int i=1; i<=s.PISTEITA; i++) {
            for (int j=i+1; j<=s.PISTEITA; j++) {
                if (!s.onYhteys(i, j)) return false;
            }
        }

        return true;
    }

    /**
     * Sama kuin TaydellinenVerkko mutta toteutus eri.
     * @param s analysoitava suhteikko
     * @return oliko täydellinen verkko
     */
    
    public static boolean taydellinenVerkkoSeuraajienAvulla(Suhteikko s) {
        if (!tayttaaVerkkoehdon(s)) return false;

        for (int i=1; i<=s.PISTEITA; i++) {
            if (s.seuraajienLkm(i) != s.PISTEITA -1) return false;
        }

        return true;
    }

    /*
     * Privaattimetodit ---------------------------------------------
     */

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

    /**
     * Ovatko kaikkien pisteiden asteet samat.
     * Toimii samoin kuin saannollinenVerkko, mutta
     * mikäli metodille annetaan tyhjä verkko, virhe kaataa ohjelman.
     * @param s
     * @return
     */

    private static boolean kaikkiAsteetSamatEpatyhjassa(Suhteikko s) {
        int ekanAste = lahtoaste(s, 1);
        for (int i=2; i<=s.PISTEITA; i++) {
            if (lahtoaste(s, i) != ekanAste) return false;
        }
        return true;
    }
}
