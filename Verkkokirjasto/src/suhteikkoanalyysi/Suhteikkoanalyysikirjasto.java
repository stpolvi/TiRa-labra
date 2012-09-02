
package suhteikkoanalyysi;

import java.awt.Color;
import suhteikot.*;
import tietorakenteetLuvuille.*;

/**
 * Kirjasto, joka tarjoaa suhteikkojen rakenteen tutkimiseen sopivia algoritmeja.
 * O-analyysiä javadocissa metodien kohdalla.
 * @author silja
 */

public class Suhteikkoanalyysikirjasto {

    private Suhteikkoanalyysikirjasto() {}

    /**
     * Eristetty piste on piste, josta ei ole yhteyksiä muihin pisteisiin
     * kumpaankaan suuntaan.
     * Aikavaativuus O(pisteidenLkm * yhteyksienLkm),
     * tilavaativuus O(1)
     * @param s analysoitava suhteikko
     * @return eristetyt pisteet järjestyksessä
     */

    public static int[] eristetytPisteet(Suhteikko s) {
        VenyvaTaulukkoVain1Esiintyma eristetyt = new VenyvaTaulukkoVain1Esiintyma();

        for (int i=1; i<=s.PISTEITA; i++)
            if (s.seuraajienLkm(i) == 0 || ainutSeuraajaPisteItse(s, i))
                eristetyt.lisaa(i);

        for (int i=1; i<=s.PISTEITA; i++)
            if (s.seuraajienLkm(i) != 0)
                for (int j : s.getSeuraajat(i).toIntArray())
                    if (i != j)
                        eristetyt.poistaYksiEsiintyma(j);
                
        return eristetyt.toIntArray();
    }

        /**
         * Privaatti.
         * Onko pisteellä täsmälleen yksi seuraaja joka on piste itse.
         */

        private static boolean ainutSeuraajaPisteItse(Suhteikko s, int piste) {
            if (s.seuraajienLkm(piste) != 1) return false;
            return s.onYhteys(piste, piste);
        }

    /**
     * Onko annettu piste väritettävän suhteikon juuri:
     * onko siitä kulku kaikkiin muihin pisteisiin.
     * Aikavaativuus O((pisteidenLkm + yhteyksienLkm) *pisteidenLkm),
     * tilavaativuus O(pisteidenLkm).
     * @param c analysoitava suhteikko
     * @param piste onko juuri
     * @return oliko juuri
     */

    public static boolean onJuuri(VaritettavaSuhteikko c, int piste) {
        for (int i=1; i<=c.PISTEITA; i++) {
            if (!onKulkuLeveyshaulla(c, piste, i)) return false;
        }
        return true;
    }

    /**
     * Onko annetussa väritettävässä suhteikossa kulku pisteestä toiseen:
     * onko alkupisteestä reitti, mahdollisesti muiden pisteiden kautta,
     * yhteyksiä pitkin loppupisteeseen.
     * Aikavaativuus O(1) jos alkupiste ja loppupiste ovat samat,
     * O(log n) jos alkupisteestä on suoraan yhteys loppupisteeseen.
     * Näissä tapauksissa tilavaativuus O(1).
     * Muussa tapauksessa aikavaativuus O(pisteidenLkm + yhteyksienLkm),
     * tilavaativuus O(pisteidenLkm).
     * @param c analysoitava suhteikko
     * @param alkupiste piste josta lähdetään
     * @param loppupiste piste johon yritetään kulkea
     * @return onko kulkua olemassa
     */

    public static boolean onKulkuLeveyshaulla
            (VaritettavaSuhteikko c, int alkupiste, int loppupiste) {

        if (alkupiste == loppupiste) return true;
        if (c.onYhteys(alkupiste, loppupiste)) return true;

        c.varitaKaikki(Color.WHITE);
        Jono jono = new Jono();
        jono.lisaa(alkupiste);
        int vuorossa;
        IntSailio vuorossaOlevanSeuraajat;

        while (jono.alkioita() > 0) {
            vuorossa = jono.ota();
            c.varita(vuorossa, Color.BLACK);
            vuorossaOlevanSeuraajat = c.getSeuraajat(vuorossa);
            if (vuorossaOlevanSeuraajat == null) continue;

            for (int seuraaja : vuorossaOlevanSeuraajat.toIntArray()) {
                if (c.getVari(seuraaja).equals(Color.BLACK)) continue;
                if (seuraaja == loppupiste) return true;
                jono.lisaa(seuraaja);
            }
        }
        return false;
    }

    /**
     * Onko suhteikko silmukaton:
     * mistään pisteestä ei ole itseensä yhteyttä.
     * Aikavaativuus O(pisteidenLkm log pisteidenLkm),
     * Tilavaativuus O(1)
     * @param suhteikko analysoitava suhteikko
     * @return oliko silmukaton
     */

    public static boolean onSilmukaton(Suhteikko suhteikko) {
        for (int i=1; i<=suhteikko.PISTEITA; i++) {     // O(pisteidenLkm)
            if (suhteikko.onYhteys(i, i))               // O(log pisteidenLkm)
                return false;
        }
        return true;
    }
    
    /**
     * Onko suhteikko symmetrinen:
     * suhteikon kahden pisteen välillä on joko yhteys molempiin suuntiin
     * tai sitten ei lainkaan yhteyttä.
     * Aikavaativuus O(pisteidenLkm^2 log pisteidenLkm),
     * tilavaativuus O(1)
     * @param suhteikko analysoitava suhteikko
     * @return oliko symmetrinen
     */

    public static boolean onSymmetrinen(Suhteikko suhteikko) {
        for (int i=1; i<=suhteikko.PISTEITA; i++) {
            for (int j=1; j<=suhteikko.PISTEITA; j++) {
                if (!tyokalut.Tyokalut.ekvivalentit(suhteikko.onYhteys(i, j), suhteikko.onYhteys(j, i)))
                    return false; // O(log pisteidenLkm)
            }
        }
        return true;
    }

    /**
     * Onko suhteikko täydellinen:
     * onko jokaisen kahden eri pisteen välillä yhteys ainakin toiseen suuntaan.
     * Pisteestä ei tarvitse olla itseensä yhteyttä.
     * Aikavaativuus: O(pisteidenLkm^2 log pisteidenLkm),
     * tilavaativuus O(1)
     * @param s analysoitava suhteikko
     * @return oliko täydellinen
     */

    public static boolean onTaydellinen(Suhteikko s) {
        for (int i=1; i<=s.PISTEITA; i++) {
            for (int j=i+1; j<=s.PISTEITA; j++) {
                if (!s.onYhteys(i, j) && !s.onYhteys(j, i)) return false;
            }
        }
        return true;
    }

    /**
     * Palauttaa väritetyn suhteikon sen juuren nimen, joka on pienin luku.
     * Jos juuria ei ole, palauttaa -1.
     * Juuri on piste, josta on kulku suhteikon jokaiseen muuhun pisteeseen.
     * Aikavaativuus O(pisteidenLkm^2 * (pisteidenLkm + yhteyksienLkm)),
     * tilavaativuus O(pisteidenLkm)
     * @param c analysoitava suhteikko
     * @return pienin juuri
     */

    public static int pieninJuuri(VaritettavaSuhteikko c) {
        for (int i=1; i<=c.PISTEITA; i++) { // O(pisteidenLkm)
            if (onJuuri(c, i)) return i;    // Aikavaativuus O((pisteidenLkm + yhteyksienLkm) *pisteidenLkm), tilavaativuus O(pisteidenLkm).
        }
        return -1;
    }

    /**
     * Lähtöaste on pisteestä lähtevien yhteyksien lukumäärä.
     * Aikavaativuus O(1), tilavaativuus O(1)
     * @param s suhteikko
     * @param piste suhteikon piste
     * @return pisteen lähtöaste suhteikossa
     */

    public static int pisteenLahtoaste(Suhteikko s, int piste) {
        IntSailio seuraajat = s.getSeuraajat(piste);    // O(1)
        if (seuraajat == null) return 0;
        return seuraajat.alkioita();                    // O(1)
    }

    /**
     * Tuloaste on pisteeseen saapuvien yhteyksien lukumäärä.
     * Aikavaativuus O(pisteidenlkm log pisteidenLkm)
     * Tilavaativuus O(1)
     * @param s suhteikko
     * @param piste suhteikon piste
     * @return pisteen tuloaste suhteikossa
     */

    public static int pisteenTuloaste(Suhteikko s, int piste) {
        int vastaus = 0;
        for (int i=1; i<=s.PISTEITA; i++) {
            if (s.onYhteys(i, piste)) vastaus++;
        }
        return vastaus;
    }

    /**
     * Täyttääkö suhteikko verkkoehdon: onko se silmukaton ja symmetrinen
     * Aikavaativuus O(pisteidenLkm^2 log pisteidenLkm),
     * tilavaativuus O(1)
     * @param suhteikko analysoitava suhteikko
     * @return täyttääkö verkkoehdon
     */

    public static boolean tayttaaVerkkoehdon(Suhteikko suhteikko) {
        return onSilmukaton(suhteikko) && onSymmetrinen(suhteikko);
    }   // O(pisteidenLkm log pisteidenLkm) + O(pisteidenLkm^2 log pisteidenLkm)

}
