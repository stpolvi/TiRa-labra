
package suhteikkoanalyysi;

import java.awt.Color;
import suhteikot.Suhteikko;
import suhteikot.VaritettavaSuhteikko;
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
     * @param s
     * @return
     */

    public static int[] eristetytPisteet(Suhteikko s) {
        VenyvaTaulukkoVain1Esiintyma eristetyt = new VenyvaTaulukkoVain1Esiintyma();

        for (int i=1; i<=s.PISTEITA; i++)
            if (s.seuraajienLkm(i) == 0)
                eristetyt.lisaa(i);

        for (int i=1; i<=s.PISTEITA; i++)
            if (s.seuraajienLkm(i) != 0)
                for (int j : s.getSeuraajat(i).toIntArray())
                    eristetyt.poistaYksiEsiintyma(j);
                
        return eristetyt.toIntArray();
    }

    /**
     * Onko annettu piste suhteikon juuri:
     * onko siitä kulku kaikkiin muihin pisteisiin.
     * Aikavaativuus riippuu leveyshausta, jonka analyysi kesken
     * @param s analysoitava suhteikko
     * @param piste onko juuri
     * @return oliko juuri
     */

    public static boolean onJuuri(Suhteikko s, int piste) {
        for (int i=1; i<=s.PISTEITA; i++) {
            if (!onKulkuLeveyshaulla(s, piste, i)) return false;
        }
        return true;
    }

    /**
     * Onko suhteikossa kulku pisteestä toiseen:
     * onko alkupisteestä reitti, mahdollisesti muiden pisteiden kautta,
     * yhteyksiä pitkin loppupisteeseen.
     * Aikavaativuus O(1) jos alkupiste ja lopuppiste ovat samat,
     * O(log n) jos alkupisteestä on suoraan yhteys loppupisteeseen,
     * TODO O(???) muuten.
     * @param s analysoitava suhteikko
     * @param alkupiste piste josta lähdetään
     * @param loppupiste piste johon yritetään kulkea
     * @return onko kulkua olemassa
     */

    public static boolean onKulkuLeveyshaulla(Suhteikko s, int alkupiste, int loppupiste) {
        if (alkupiste == loppupiste) return true;
        if (s.onYhteys(alkupiste, loppupiste)) return true;
        VaritettavaSuhteikko v;
        try {
            v = (VaritettavaSuhteikko) s;
            return onKulkuLeveyshaullaVaritettavalle(v, alkupiste, loppupiste);
        } catch (ClassCastException e) {
            throw new Error("Kulun tutkimista ei toteutettu muulle kuin "
                    + "väritettävälle suhteikolle.");
        }
    }
    
        private static boolean onKulkuLeveyshaullaVaritettavalle
                (VaritettavaSuhteikko v, int alkupiste, int loppupiste) {

            v.varitaKaikki(Color.WHITE);
            Jono jono = new Jono();
            jono.lisaa(alkupiste);
            int vuorossa;
            IntSailio vuorossaOlevanSeuraajat;

            while (jono.alkioita() > 0) {
                vuorossa = jono.ota();
                v.varita(vuorossa, Color.BLACK);
                vuorossaOlevanSeuraajat = v.getSeuraajat(vuorossa);
                if (vuorossaOlevanSeuraajat == null) continue;

                for (int seuraaja : vuorossaOlevanSeuraajat.toIntArray()) {
                    if (v.getVari(seuraaja).equals(Color.BLACK)) continue;
                    if (seuraaja == loppupiste) return true;
                    jono.lisaa(seuraaja);
                }
            }
            return false;
        }


    /**
     * Onko suhteikko silmukaton:
     * mistään pisteestä ei ole itseensä yhteyttä.
     * Aikavaativuus O(pisteidenLkm log pisteidenLkm)
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
     * MUUTETAAN TEHOKKAAMMAKSI
     * Nykyisellään aikavaativuus O(pisteidenLkm^2 log pisteidenLkm)
     * Onko suhteikko symmetrinen:
     * suhteikon kahden pisteen välillä on joko yhteys molempiin suuntiin
     * tai sitten ei lainkaan yhteyttä.
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
     * Aikavaativuus: O(pisteidenLkm^2 log pisteidenLkm)
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
     * TODO yhtenäisyys:
     * Määritelmä: suhteikko on yhtenäinen, jos sen pisteiden joukon
     * jokaisella aidolla epätyhjällä osajoukolla P,
     * suhteikossa on nuoli P:stä tai nuoli P:hen.
     * @param s analysoitava suhteikko
     * @return oliko yhtenäinen
     */

    public static boolean onYhtenainen(Suhteikko s) {
        if (s.PISTEITA == 0 || s.PISTEITA == 1)
            return true; //tyhjä ja yksipisteinen automaattisesti yhtenäisiä
        VaritettavaSuhteikko t;

        try {
            t = (VaritettavaSuhteikko) s;
            return onYhtenainenVaritettavalle(t);
        } catch (ClassCastException e) {
            throw new Error("kesken");
        }
    }
    
        /**
         * Tutkii leveyshaulla, onko annettu väritettävä suhteikko yhtenäinen.
         * Yhtenäisyys, määritelmä: suhteikko on yhtenäinen, jos sen pisteiden joukon
         * jokaisella aidolla epätyhjällä osajoukolla P,
         * suhteikossa on nuoli P:stä tai nuoli P:hen.
         */

        private static boolean onYhtenainenVaritettavalle(VaritettavaSuhteikko c) {
            throw new Error("kesken");
        }

    /**
     * Palauttaa suhteikon sen juuren nimen, jonka nimi on pienin luku.
     * Jos juuria ei ole, palauttaa -1.
     * Juuri on piste, josta on kulku suhteikon jokaiseen muuhun pisteeseen.
     * Aikavaativuus riippuu leveyshaun analyysistä, joka kesken
     * @param s analysoitava suhteikko
     * @return pienin juuri
     */

    public static int pieninJuuriBruteForce(Suhteikko s) {
        for (int i=1; i<=s.PISTEITA; i++) { // O(pisteidenLkm)
            if (onJuuri(s, i)) return i;    // O-analyysi kesken
        }
        return -1;
    }

    /**
     * Lähtöaste on pisteestä lähtevien yhteyksien lukumäärä.
     * Aikavaativuus O(1)
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
     * Aikavaativuus O(pisteidenLkm^2 log pisteidenLkm)
     * @param suhteikko analysoitava suhteikko
     * @return täyttääkö verkkoehdon
     */

    public static boolean tayttaaVerkkoehdon(Suhteikko suhteikko) {
        return onSilmukaton(suhteikko) && onSymmetrinen(suhteikko);
    }   // O(pisteidenLkm log pisteidenLkm) + O(pisteidenLkm^2 log pisteidenLkm)

}
